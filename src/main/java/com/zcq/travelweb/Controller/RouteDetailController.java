package com.zcq.travelweb.Controller;

import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Service.RouteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RouteDetailController {

    @Autowired(required = false)
    RouteDetailService routeDetailService;

    @RequestMapping("/toroutedetail")
    public String toRouteDetail(Integer routeid,
                                Model model){

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
