package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.JSONResult;
import com.zcq.travelweb.Data.User;

import java.util.Date;

public interface RegisterService extends IService<User> {
    String UserRegister(User user);
    Date strToDateLong(String strDate);
    String checkcode(String code, String Rcode); //验证码验证
    boolean sendEmail(String email); //发送邮件
//    boolean activeEmail(String uuid, User user); //激活
    JSONResult<Void> checkUuid(String uuid);
}
