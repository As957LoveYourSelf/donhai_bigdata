package com.zcq.travelweb.Controller;

import com.github.pagehelper.PageHelper;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Service.RouteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired(required = false)
    RouteListService routeListService;

    @RequestMapping("/toIndex")
    public String firsttoIndex(HttpServletRequest request,String selectType,
                               @RequestParam(defaultValue = "") String cid,
                               Model model){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null){
            session.setAttribute("user", null);
        }
        try {
            if (selectType.equals("selectHot") && !cid.equals("4")){
                PageHelper.startPage(1,6);
                List<TravelRoute> routeList = routeListService.getHotRoutesBy(Integer.parseInt(cid));
                model.addAttribute("jw_routes",routeList);
            }
            if (selectType.equals("selectHot") && !cid.equals("5")){
                PageHelper.startPage(1,6);
                List<TravelRoute> routeList = routeListService.getHotRoutesBy(Integer.parseInt(cid));
                model.addAttribute("gn_routes",routeList);
            }
            if(selectType.equals("selectTheme")){
                PageHelper.startPage(1,4);
                List<TravelRoute> routeList = routeListService.getThemeRoutes();
                model.addAttribute("themeroutes",routeList);
            }
            if(selectType.equals("selectNewest")){
                PageHelper.startPage(1,4);
                List<TravelRoute> routeList = routeListService.getNewestRoutes();
                model.addAttribute("newestroutes",routeList);
            }
            if (selectType.equals("selectHot")){
                PageHelper.startPage(1,4);
                List<TravelRoute> routeList = routeListService.getHotRoutes();
                model.addAttribute("proproutes",routeList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            PageHelper.clearPage();
        }
        return "index";
    }
}
