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
public class MyFavoriteController {

    @Autowired(required = false)
    MyFavoriteService myFavoriteService;
    @Autowired(required = false)
    List<TravelRoute> routeList;

    @RequestMapping("/addFavorite")
    public String addFavorite(Integer rid,
                              Integer uid,
                              Model model){
        if (null == uid){
            model.addAttribute("msg", "请先登录！");
            return "route_detail";
        }
        myFavoriteService.addFavorite(uid, rid);
        model.addAttribute("add_ok", "收藏成功！");
        return "route_detail";
    }

    @RequestMapping("/GetMyFavorite")
    public String getMyFavorite(String userid,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                Model model){
        System.out.println(pageNum);
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageNum <= 0){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 12);
        List<TravelRoute> favorites = myFavoriteService.getMyFavorite(userid, routeList);
        PageInfo<TravelRoute> pageInfo = new PageInfo<>(favorites);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("favorites",favorites);
        return "myfavorite";
    }

}
