package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import static com.sky.constant.RedisConstant.KEY;

/**
 * ShopController
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/9/11 18:22
 */

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "管理端店铺相关接口")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result setShopStatus(@PathVariable Integer status) {
        redisTemplate.opsForValue().set(KEY, status);
        log.info("设置店铺的营业状态为：{}", status == 1 ? "营业中" : "打烊中");
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getShopStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("店铺的营业状态为：{}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
