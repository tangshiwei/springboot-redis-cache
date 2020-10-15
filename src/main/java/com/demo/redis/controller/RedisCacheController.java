package com.demo.redis.controller;

import com.demo.redis.entity.User;
import com.demo.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Slf4j
@RestController
public class RedisCacheController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getUser")
    public User getUser(Long id) {
        // 模拟查询id为1的用户
        User user = userService.get(id);
        return user;
    }


    @RequestMapping(value = "/updateUser")
    public User updateUser(Long id,String name) {
        userService.saveOrUpdate(new User(id, name));

        User user = userService.get(id);
        return user;
    }

    /**
     * 测试删除，查看redis是否存在缓存数据
     */
    @RequestMapping(value = "/deleteUser")
    public void deleteUser() {
        // 查询一次，使redis中存在缓存数据
        userService.get(1L);
        // 删除，查看redis是否存在缓存数据
        userService.delete(1L);
    }
}
