package com.item.manager.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.item.manager.entity.sys.User;

/**
 * @author zcm
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名，查找用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
