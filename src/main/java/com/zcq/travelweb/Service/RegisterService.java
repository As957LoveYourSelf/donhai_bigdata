package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.User;
import org.springframework.ui.Model;

import java.util.Date;

public interface RegisterService extends IService<User> {
    String UserRegister(User user);
    Date strToDateLong(String strDate);
    String checkcode(String code, String Rcode);
}
