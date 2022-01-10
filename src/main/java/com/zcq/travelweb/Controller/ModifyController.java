package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Service.Impl.LoginServiceImpl;
import com.zcq.travelweb.Service.Impl.ModifyServiceImpl;
import com.zcq.travelweb.Service.Impl.PersonServiceImpl;
import com.zcq.travelweb.Service.Impl.RegisterServiceImpl;
import com.zcq.travelweb.Service.ModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ModifyController {

    @Autowired(required = false)
    ModifyServiceImpl modifyService;
    @Autowired(required = false)
    RegisterServiceImpl registerService;
//    @Autowired(required = false)
//    User user;
    @RequestMapping("/toModifyPage")
    public String toModifyPage(){
        return "/modify";
    }

    @RequestMapping("/toModify")
    public String toModify(String username,
                           String password,
                           String email,
                           String name,
                           String telephone,
                           String birthday,
                           String sex,
                           HttpServletRequest request,
                           Model model){
        System.out.println(birthday);
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        user.setUsername(username).setPassword(password).setEmail(email).setName(name).setTelephone(telephone)
                .setBirthday(registerService.strToDateLong(birthday)).setSex(sex);
        System.out.println(user);
//        this.user.setUid(user1.getUid()).setStatus(user1.getStatus()).setCode(user1.getCode());
//        this.user.setName(name).setPassword(password).setUsername(username).
//                setBirthday(registerService.strToDateLong(birthday)).
//                setEmail(email).setSex(sex).setTelephone(telephone);
//        System.out.println(this.user);
//        boolean status = modifyService.UpdateUser(this.user);
        boolean status = modifyService.UpdateUser(user);
        if (status) {
                model.addAttribute("msg", "修改成功");
                return "/modify";
        }
        else{
            model.addAttribute("msg","修改失败");
            return "/modify";
        }
    }
}
