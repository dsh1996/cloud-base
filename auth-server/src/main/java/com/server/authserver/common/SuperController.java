package com.server.authserver.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.server.common.vo.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * web 控制层公共接口抽取， 利用spring set注入方式抽取crud公共操作接口
 *
 * @param <T> 具体的Service
 * @author dengshuihong
 */
@Slf4j
public class SuperController<T extends SuperEntity> {

    protected IService<T> service;

    @PostMapping("/add")
    @ApiOperation(value = "新增",notes = "新增数据")
    public Result save(@RequestBody @Validated T permission) {
        return Result.SUCCESS(service.save(permission));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新",notes = "更新数据")
    public Result update(@RequestBody @Validated(SuperEntity.Update.class) T permission) {
        return Result.SUCCESS(service.updateById(permission));
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "删除",notes = "通过数据Id删除数据")
    public Result remove(@PathVariable Long id) {
        return Result.SUCCESS(service.removeById(id));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询",notes = "分页查询数据")
    public Result page(IPage<T> page) {
        return Result.SUCCESS(service.page(page));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "详情",notes = "通过数据id查询数据详情")
    public Result get(@PathVariable Long id) {
        return Result.SUCCESS(service.getById(id));
    }
}
