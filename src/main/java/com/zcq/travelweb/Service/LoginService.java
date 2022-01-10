package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface LoginService extends IService<User>{
    Map<String,Object> Login(String username, String password, Map<String,Object> map) throws Exception;
    String checkcode(String code, String Rcode);
}
