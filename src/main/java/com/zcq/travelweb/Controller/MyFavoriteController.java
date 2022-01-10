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

import javax.servlet.http.HttpServletRequest;
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
                              Model model,
                              HttpServletRequest request){
        if (null == uid || uid.equals("nouser")){
            model.addAttribute("msg", "请先登录！");
            return routeDetailController.toRouteDetail(rid,model, request);
        }
        myFavoriteService.addFavorite(uid, rid);
        System.out.println("Add_OK");
        model.addAttribute("add_ok", "收藏成功！");
        return routeDetailController.toRouteDetail(rid,model,request);
    }

    //TODO:可改进的地方：利用session替代uid获取用户登录信息
    @RequestMapping("/cancelFavorite")
    public String cancelFavorite(Integer rid,
                                 String uid,
                                 Model model,
                                 HttpServletRequest request){
        if (null == uid || uid.equals("nouser")){
            model.addAttribute("msg", "请先登录！");
            return routeDetailController.toRouteDetail(rid,model, request);
        }
        myFavoriteService.cancelFavorite(uid, rid);
        System.out.println("Cancel_OK");
        model.addAttribute("Cancel_OK", "已取消收藏！");
        return routeDetailController.toRouteDetail(rid,model,request);
    }

    //TODO:增加对不规范输入的处理
    @RequestMapping("/getFavoriteRank")
    public String getFavoriteRank(String rname,
                                  String priceF,
                                  String priceL,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  Model model){
        try {
            Double priceF_ = priceF.equals("null")?null:Double.valueOf(priceF.trim());
            Double priceL_ = priceL.equals("null")?null:Double.valueOf(priceL.trim());
            PageHelper.startPage(pageNum,10);
            List<TravelRoute> favoriteRankInfo = myFavoriteService.getFavoriteRankInfo(rname, priceF_, priceL_);
            PageInfo<TravelRoute> pageInfo = new PageInfo<>(favoriteRankInfo);
            model.addAttribute("routes", favoriteRankInfo);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("pageurl",
                    "/favorite/getFavoriteRank?rname="
                            +rname+"&priceF="+priceF+"&priceL="+priceL+"&pageNum=");
            return "/favoriterank";
        }
        catch (Exception e){
            model.addAttribute("routes", null);
            e.printStackTrace();
        }
        finally {
            PageHelper.clearPage();
        }
        return "/favoriterank";
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
            return "/myfavorite";
        }catch (NullPointerException e){
            e.printStackTrace();
            model.addAttribute("favorites",null);
            return "/myfavorite";
        }finally {
            PageHelper.clearPage();
        }
    }
}
