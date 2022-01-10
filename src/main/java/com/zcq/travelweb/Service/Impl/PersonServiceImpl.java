package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Mapper.UserMapper;
import com.zcq.travelweb.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl extends ServiceImpl<UserMapper, User> implements PersonService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public User getUserInformation(String uid) {
        return userMapper.selectById(uid);
    }
}
