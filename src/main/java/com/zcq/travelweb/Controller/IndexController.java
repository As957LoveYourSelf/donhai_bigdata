package com.zcq.travelweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/toIndex")
    public String toIndex(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return "index";
    }
}
