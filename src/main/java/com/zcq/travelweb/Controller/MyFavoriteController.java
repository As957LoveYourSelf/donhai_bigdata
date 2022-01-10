package com.zcq.travelweb.Controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Service.MyFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/favorite")
public class MyFavoriteController {

    @Autowired(required = false)
    MyFavoriteService myFavoriteService;

    @Autowired(required = false)
    RouteDetailController routeDetailController;

    @RequestMapping("/addFavorite")
    public String addFavorite(Integer rid,
                              String uid,
                              Model model){
        if (null == uid || uid.equals("nouser")){
            model.addAttribute("msg", "请先登录！");
            return "redirect:/toroutedetail?routeid="+rid;
        }
        myFavoriteService.addFavorite(uid, rid);
        System.out.println("Add_OK");
        model.addAttribute("add_ok", "收藏成功！");
        return "redirect:/toroutedetail?routeid="+rid;
    }

    //TODO:完善取消收藏功能，并在前端页面提供响应式按钮显示
    @RequestMapping("/cancelFavorite")
    public String cancelFavorite(Integer rid,
                                 String uid,
                                 Model model){
        if (null == uid || uid.equals("nouser")){
            model.addAttribute("msg", "请先登录！");
            return "redirect:/toroutedetail?routeid="+rid;
        }
        return "redirect:/toroutedetail?routeid="+rid;
    }

    @RequestMapping("/GetMyFavorite")
    public String getMyFavorite(String uid,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                Model model){
        System.out.println(uid);
        if(pageNum == null){
            pageNum=1;
        }
        if (pageNum <= 0){
            pageNum = 1;
        }
        try{
            PageHelper.startPage(pageNum, 12);
            List<TravelRoute> favorites = myFavoriteService.getMyFavorite(uid);
            PageInfo<TravelRoute> pageInfo = new PageInfo<>(favorites);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("favorites",favorites);
            model.addAttribute("pageurl", "/favorite/GetMyFavorite?pageNum=");
            return "myfavorite";
        }catch (NullPointerException e){
            e.printStackTrace();
            model.addAttribute("favorites",null);
            return "myfavorite";
        }
    }
}
