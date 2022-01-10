package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Service.LoginService;
import com.zcq.travelweb.Utils.CodeUtils;
import com.zcq.travelweb.Utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired(required = false)
    LoginService loginService;
    private String Rcode = null;
    private String info = null;

    @Autowired
    private Map<String, Object> map;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/toIndex";
    }


    @GetMapping("/getcode")
    public void getcode(HttpServletResponse response, HttpServletRequest request){
        CodeUtils.getvalidateCode(request, response);
        HttpSession session = request.getSession();
        this.Rcode = (String) session.getAttribute("loginCode");
    }

    //获取日志信息
    @RequestMapping(value = "/getLogInfo", method = RequestMethod.GET)
    @ResponseBody
    public void Info(String IP,String address,String accessTime,String browser) {
        this.info = IP + "\t" + address + "\t" + accessTime + "\t" + browser;
    }

    @RequestMapping("/checkAccount")
    public String Login(String username, String password,
                        @RequestParam(value = "check") String code,
                        HttpServletRequest request, Model model) throws Exception {
        System.out.println(username+" "+password+" "+code);
        String pd = Md5Util.encodeByMd5(password);
        Map<String, Object> loginInfo = loginService.Login(username, pd, map);
        String status = (String) loginInfo.get("status");
        String code_status = loginService.checkcode(code,this.Rcode);
        System.out.println(status+" "+code_status);
        if (status.equals("login_ok") && code_status.equals("check_ok")){
            WriteInfo(username);
            User user = (User) loginInfo.get("user");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/toIndex";
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

    //将日志信息写入文件
    public void WriteInfo(String username) throws IOException {
        //创建单日日志
        this.info = this.info + "\t" +username + "\n";
        String date = LocalDate.now().toString();
        String filePath = "D:\\JavaProjects\\donhai_bigdata\\src\\main\\InfoTXT\\" + date + ".txt";
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(this.info);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null){
                bufferedWriter.close();
                fileWriter.close();
            }
        }
    }
}
