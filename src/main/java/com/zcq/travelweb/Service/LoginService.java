package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.User;

import javax.servlet.http.HttpServletRequest;


public interface LoginService extends IService<User>{
    String Login(String username, String password, HttpServletRequest request) throws Exception;
    String checkcode(String code, String Rcode);
}
