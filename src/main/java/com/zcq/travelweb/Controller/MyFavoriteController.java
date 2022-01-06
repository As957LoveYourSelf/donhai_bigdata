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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/favorite")
public class MyFavoriteController {

    @Autowired(required = false)
    MyFavoriteService myFavoriteService;
    @Autowired(required = false)
    List<TravelRoute> routeList;
    @Autowired(required = false)
    RouteDetailController routeDetailController;

    @RequestMapping("/addFavorite")
    public String addFavorite(Integer rid,
                              String uid,
                              Model model){
        if (null == uid || uid.equals("nouser")){
            model.addAttribute("msg", "请先登录！");
            return routeDetailController.toRouteDetail(rid,model);
        }
        myFavoriteService.addFavorite(Integer.parseInt(uid), rid);
        System.out.println("Add_OK");
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
        List<TravelRoute> favorites = null;
        try{
            favorites = myFavoriteService.getMyFavorite(userid, routeList);
            PageInfo<TravelRoute> pageInfo = new PageInfo<>(favorites);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("favorites",favorites);
        }catch (NullPointerException e){
            model.addAttribute("favorites",null);
        }
        return "myfavorite";
    }

}
