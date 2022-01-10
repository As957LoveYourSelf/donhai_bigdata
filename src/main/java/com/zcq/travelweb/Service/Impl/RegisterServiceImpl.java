package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.JSONResult;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Mapper.RegisterMapper;
import com.zcq.travelweb.Mapper.UserMapper;
import com.zcq.travelweb.Service.RegisterService;
import com.zcq.travelweb.Utils.MailUtils;
import com.zcq.travelweb.Utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, User> implements RegisterService {
    public String Ruuid;

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
        //获取激活码
        Ruuid = UuidUtil.getUuid();
        user.setCode(Ruuid);
        return saveOrUpdate(user);
    }

    public Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    @Override
    public String checkcode(String code, String Rcode) {
        System.out.println("CheckCode: "+code.toUpperCase());
        System.out.println("ResourceCode: "+Rcode);
        if (code.toUpperCase().equals(Rcode)){
            return "check_ok";
        }
        return "check_error";
    }



    @Override
    public boolean sendEmail(String email) {
        String text = "<p>你好，欢迎注册网站!</p><p>请点击<a href='http://localhost:8080/register/activeEmail'>激活</a></p>"+"激活码："+Ruuid;
        return MailUtils.sendMail(email,text,"注册激活"); //邮件发送成功
    }

    @Autowired(required = false)
    private UserMapper userMapper ;

    @Override
    public JSONResult<Void> checkUuid(String uuid) {
        System.out.println("R激活码"+Ruuid);
        System.out.println("用户输入："+uuid);

        if ("".equals(uuid) || uuid==null)
            return new JSONResult<>(201,"激活码为空");

        if (Ruuid.equals(uuid)) {
            //激活码正确
            //修改用户激活状态为1
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code",Ruuid);
            User user = userMapper.selectOne(queryWrapper); //通过激活码查找用户

            //判断用户是否已激活
            if (user!=null){
                //未激活
                System.out.println(user.toString());
                user.setStatus('1');
                user.setCode("已过期");  //更改用户激活状态同时将激活码从数据库中删除，以免造成重复激活
                saveOrUpdate(user); //更新用户激活状态   错误
                return new JSONResult<>(200,"已激活请登录");
            }else {
                //已激活
                return new JSONResult<>(203,"激活已过期");
            }
        }
        return new JSONResult<>(202,"激活码有误");
    }
}


