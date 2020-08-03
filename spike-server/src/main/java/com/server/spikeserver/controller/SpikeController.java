package com.server.spikeserver.controller;

import com.server.common.exception.BizException;
import com.server.common.vo.Result;
import com.server.spikeserver.entity.Item;
import com.server.spikeserver.service.SpikeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/item")
public class SpikeController {


    @Resource
    private SpikeService spikeService;

    /**
     * @return
     */
    @PostMapping("/shopping/{itemId}")
    public Result spikeItem(Integer number, @PathVariable Long itemId) {
        try {
            Integer store = spikeService.spikeItem(itemId, number);
            return Objects.nonNull(store) ? Result.SUCCESS("恭喜！抢购成功", "剩余商品数：" + store) : Result.FAILED("抱歉！抢购失败,库存不足");
        } catch (Exception e) {
            if(e instanceof BizException){
                return ((BizException) e).getResult();
            }
            return Result.FAILED();
        }
    }

    @PutMapping
    public Result addItem(@RequestBody Item item) {
        spikeService.addItem(item);
        return Result.SUCCESS();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.SUCCESS(spikeService.getItems());
    }

    @DeleteMapping
    public Result remove() {
        return Result.SUCCESS(spikeService.remove());
    }
}

