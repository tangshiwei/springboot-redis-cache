package com.demo.redis.service;

import com.demo.redis.entity.User;

public interface UserService {
    /**
     * 保存或修改用户
     */
    User saveOrUpdate(User user);

    /**
     * 获取用户
     */
    User get(Long id);

    /**
     * 删除
     */
    void delete(Long id);
}
