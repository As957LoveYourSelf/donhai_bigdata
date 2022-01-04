package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Service.LoginService;
import com.zcq.travelweb.Utils.CodeUtils;
import com.zcq.travelweb.Utils.Md5Util;
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
@RequestMapping("/user")
public class LoginController {

    @Autowired(required = false)
    LoginService loginService;
    private String Rcode = null;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/getcode")
    public void getcode(HttpServletResponse response, HttpServletRequest request){
        CodeUtils.getvalidateCode(request, response);
        HttpSession session = request.getSession();
        this.Rcode = (String) session.getAttribute("loginCode");
        System.out.println(this.Rcode);
    }

    @RequestMapping("/checkAccount")
    public String Login(String username, String password,
                        @RequestParam(value = "check") String code,
                        HttpServletRequest request, Model model) throws Exception {
        System.out.println(username+" "+password+" "+code);
        String pd = Md5Util.encodeByMd5(password);
        String status = loginService.Login(username,pd,request);
        String code_status = loginService.checkcode(code,this.Rcode);
        System.out.println(status+" "+code_status);
        if (status.equals("login_ok") && code_status.equals("check_ok")){
            return "index";
        }
        if (status.equals("login_ok") && code_status.equals("check_error")){
            model.addAttribute("msg","验证码错误！请重新输入验证码");
            return "login";
        }
        if (status.equals("no_user")){
            model.addAttribute("msg","没有该用户！请确认用户名正确");
            return "login";
        }
        if (status.equals("password_error")){
            model.addAttribute("msg", "密码错误，请重新输入密码！");
            return "login";
        }
        if (status.equals("no_register")){
            model.addAttribute("msg","您的用户已注册，但尚未激活，请前往邮箱验证激活");
            return "login";
        }
        return "../public/error/500";
    }
}
