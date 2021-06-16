package com.item.manager.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.manager.entity.sys.User;
import com.item.manager.mapper.sys.UserMapper;
import com.item.manager.service.sys.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcm
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        //调用构造器，查询
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.eq("username",username);
        List<User> users = userMapper.selectList(query);
        if (users != null && users.size()>0){
            return users.get(0);
        }
        return null;
    }
}
