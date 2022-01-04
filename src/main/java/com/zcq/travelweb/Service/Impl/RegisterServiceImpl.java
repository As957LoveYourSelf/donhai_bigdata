package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Mapper.RegisterMapper;
import com.zcq.travelweb.Service.RegisterService;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, User> implements RegisterService {

    @Override
    public String UserRegister(User user) {
        return checkRegisterAccount(user);
    }

    private String checkRegisterAccount(User user){
        //具体业务
        //验证输入的用户信息是否正确，正确后调用下面的Register2DB()方法
        //这里User类只有部分属性，记得视情况加上code、status
        //信息验证包括用户存在验证
        if (existUserByUsername(user.getUsername())){
            return "user_exist";
        }
        if (Register2DB(user))
        {
            return "register_ojbk";
        }
        return "update db error";
    }
    private boolean existUserByUsername(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = getOne(queryWrapper);
        return user != null;
    }

    private boolean Register2DB(User user) {
        //具体业务
        //将验证通过的用户插入数据库
        System.out.println(user);
        user.setStatus('0');
        return saveOrUpdate(user);
    }

    public Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }
}
