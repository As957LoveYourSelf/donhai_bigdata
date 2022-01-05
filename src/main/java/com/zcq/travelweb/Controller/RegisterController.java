package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Mapper.UserMapper;
import com.zcq.travelweb.Service.RegisterService;
import com.zcq.travelweb.Utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
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
                               Model model,
                               @RequestParam(value = "check") String code,
                               HttpSession session) {
       user.setName(name).setPassword(password).setUsername(username).
                setBirthday(
                        registerService.strToDateLong(birthday)).
                setEmail(email).setSex(sex).setTelephone(telephone);
        String Code = (String) session.getAttribute("loginCode");
        System.out.println(Code);
        String code_status = registerService.checkcode(code,Code);
        if (code_status.equals("check_ok")) {
            String status = registerService.UserRegister(this.user);
            if (status.equals("register_ojbk"))
                return "register_ok";
            if (status.equals("user_exist")) {
                //后期可优化为显示具体错误项（可通过js验证）
                model.addAttribute("msg", "用户已存在");
                return "register";
            }
        }
        else{
            model.addAttribute("msg","验证码错误！请重新输入验证码");
            return "register";
        }
        return "../public/error/500";
    }
}
