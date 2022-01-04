package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired(required = false)
    private RegisterService registerService;

    @Autowired(required = false)
    private User user;

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/activeEmail")
    public String activeEmail(){
        return "login";
    }

    @RequestMapping("/UserRegister")
    public String UserRegister(String username,
                               String password,
                               String email,
                               String name,
                               String telephone,
                               String birthday,
                               String sex,
                               Model model) {
        user.setName(name).setPassword(password).setUsername(username).
                setBirthday(registerService.strToDateLong(birthday)).
                setEmail(email).setSex(sex).setTelephone(telephone);
        String status = registerService.UserRegister(user);
        if (status.equals("register_ojbk")) {
            return "register_ok";
        }
        if (status.equals("user_exist")) {
            //后期可优化为显示具体错误项（可通过js验证）
            model.addAttribute("msg", "用户已存在");
            return "register";
        }
        return "../public/error/500";
    }
}
