package com.server.spikeserver.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.server.common.exception.BizException;
import com.server.common.vo.Result;
import com.server.spikeserver.entity.Item;
import com.server.spikeserver.service.SpikeService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 秒杀
 */
@Slf4j
@Service
public class SpikeServiceImpl implements SpikeService {

    private static final String LOCK_PREFIX = "LOCK_";

    @Resource
    private Redisson redisson;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"), //超时降级
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //错误率
    })
    public Integer spikeItem(Long itemId, Integer number) {
        //lock is LOCK_xxx
        RLock lock = redisson.getLock(LOCK_PREFIX + itemId);
        try {
            lock.lock();
            //todo ...
            Item item = (Item) redisTemplate.opsForHash().get("spike_items", itemId);
            LocalDateTime now = LocalDateTime.now();
           /* if(!now.isAfter(item.getStartTime()) || !now.isBefore(item.getEndTime())){
                throw new BizException("秒杀暂未开始，请耐心等待！", Result.FAILED("秒杀暂未开始，请耐心等待!"));
            }*/
            Assert.isTrue(number > 0, "参数异常");
            Assert.notNull(item, "数据异常！！");
            if (item.getStore() <= 0) {
                log.warn("spike is finish!");
                return null;
            }
            int lastStore = item.getStore() - number;
            item.setStore(lastStore);
            redisTemplate.opsForHash().put("spike_items", itemId, item);
            log.info("current spike has number is {} of this item", lastStore);
            return lastStore;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void addItem(Item item) {
        long id = System.nanoTime();
        item.setId(id);
        redisTemplate.opsForHash().put("spike_items", id, item);
        log.info("add item {} store is {}", id, item);
    }

    public List<Item> getItems() {
        List<Item> items = (List<Item>) redisTemplate.opsForHash().values("spike_items");
        return items;
    }

    @Override
    public Boolean remove() {
        redisTemplate.delete("spike_items");
        return true;
    }

    private Object fallback(Long itemId, Integer number) {
        return Result.FAILED("当前排队人数较多，服务繁忙");
    }
}
