package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcq.travelweb.Service.LoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Mapper.UserMapper;
import com.zcq.travelweb.Utils.Md5Util;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {



    @Override
    public Map<String, Object> Login(String username, String password, Map<String,Object> map){
        //具体业务
        //从数据库查询该用户，如果没有该用户，返回错误，报告无用户
        //查询到用户，并查询对应密码，密码正确则返回true，否则返回错误，报告密码错误
        //查询用户状态status，为1才可通过验证
        //提供验证码验证方法，输入正确验证码即可登录
        //用户忘记密码，可以通过邮箱验证找回密码

        User user = this.selectUserByUid(username);
        if (user == null){
            map.put("status", "no_user");
            return map;
        }
        if (user.getStatus() == '0'){
            map.put("status", "no_register");
            return map;
        }
        String md5Password = null;
        try {
            md5Password = Md5Util.encodeByMd5(user.getPassword());
        }catch (Exception e){
            System.out.println("EncodeError!");
        }
        if (password.equals(md5Password)){
            map.put("status", "login_ok");
            map.put("user", user);
            return map;
        }else {
            map.put("status","password_error");
            return map;
        }
    }

    @Override
    public String checkcode(String code, String Rcode) {
        System.out.println(Rcode);
        System.out.println(code);
        if (code.toUpperCase().equals(Rcode)){
            return "check_ok";
        }
        return "check_error";
    }

    private User selectUserByUid(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }
}
