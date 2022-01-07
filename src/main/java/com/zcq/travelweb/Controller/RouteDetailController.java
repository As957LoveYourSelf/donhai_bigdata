package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Data.User;
import com.zcq.travelweb.Service.RouteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RouteDetailController {

    @Autowired(required = false)
    RouteDetailService routeDetailService;


    //TODO:添加判断登录用户是否已收藏该旅游线路的功能
    @RequestMapping("/toroutedetail")
    public String toRouteDetail(Integer routeid,
                                Model model,
                                HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user != null){
            if (routeDetailService.hadAddFavorite(routeid, user.getUid())){
                model.addAttribute("hadAddFavorite", true);
            }else {
                model.addAttribute("hadAddFavorite", false);
            }
        }
        TravelRoute routeDetail = routeDetailService.getRouteDetail(routeid);
        Integer sid = routeDetail.getSid();
        Integer cid = routeDetail.getCid();
        model.addAttribute("routeInfo", routeDetail);
        model.addAttribute("routeImgs", routeDetailService.getImgsFromRImgTabByRid(routeid));
        model.addAttribute("category", routeDetailService.getCategoryByCid(cid));
        model.addAttribute("seller", routeDetailService.getSellerBySid(sid));
        return "route_detail";
    }


}
