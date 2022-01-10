package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Mapper.UserMapper;
import com.zcq.travelweb.Service.ModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyServiceImpl  extends ServiceImpl<UserMapper, User> implements ModifyService {



    @Override
    public boolean UpdateUser(User user) {
        System.out.println(user);
        try {
            saveOrUpdate(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
