package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.JSONResult;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Service.RegisterService;
import com.zcq.travelweb.Utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired(required = false)
    private RegisterService registerService;

    @Autowired(required = false)
    private User user;

    private String Rcode = null;

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }


    //TODO:思考应加何种拦截器
    @RequestMapping("/activeEmail")
    public String activeEmail(){
        return "activeEmail";
    }

    @GetMapping("/getRcode")
    public void getcode(HttpServletResponse response, HttpServletRequest request){
        CodeUtils.getvalidateCode(request, response);
        HttpSession session = request.getSession();
        this.Rcode = (String) session.getAttribute("loginCode");
    }

    //TODO:除开用户名外，应规定邮箱和手机号也唯一确定一个用户
    @RequestMapping("/UserRegister")
    public String UserRegister(String username,
                               String password,
                               String email,
                               String name,
                               String telephone,
                               String birthday,
                               String sex,
                               Model model,
                               @RequestParam(value = "check") String code) {
       user.setName(name).setPassword(password).setUsername(username).
                setBirthday(
                        registerService.strToDateLong(birthday)).
                setEmail(email).setSex(sex).setTelephone(telephone);
        System.out.println(code);
        String code_status = registerService.checkcode(code,this.Rcode);
        if (code_status.equals("check_ok")) {
            //验证码正确
            String status = registerService.UserRegister(this.user); //插入用户
            if (status.equals("register_ojbk")){
                //用户插入成功，发送激活邮件
                boolean flag = registerService.sendEmail(email); //邮件发送成功
                if (flag) return "register_ok";
                else {
                    model.addAttribute("msg", "邮箱不存在");
                    return "register";
                }
            }
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

    @PostMapping("/active")
    @ResponseBody
    public JSONResult<Void> active(String uuid){
        return registerService.checkUuid(uuid);  //错误
    }
}
