package com.zcq.travelweb.Controller;

import com.github.pagehelper.PageHelper;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Service.RouteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired(required = false)
    RouteListService routeListService;

    @RequestMapping("/toIndex")
    public String toIndex(HttpServletRequest request,
                          Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", null);
        }
        try {
            PageHelper.startPage(1, 6);
            List<TravelRoute> jw_routes = routeListService.getHotRoutesBy(4);
            model.addAttribute("jw_routes", jw_routes);
            PageHelper.clearPage();

            PageHelper.startPage(1, 6);
            List<TravelRoute> gn_routes = routeListService.getHotRoutesBy(5);
            model.addAttribute("gn_routes", gn_routes);
            PageHelper.clearPage();

            PageHelper.startPage(1, 4);
            List<TravelRoute> themeroutes = routeListService.getThemeRoutes();
            model.addAttribute("themeroutes", themeroutes);
            PageHelper.clearPage();

            PageHelper.startPage(1, 4);
            List<TravelRoute> newestroutes = routeListService.getNewestRoutes();
            model.addAttribute("newestroutes", newestroutes);
            PageHelper.clearPage();

            PageHelper.startPage(1, 4);
            List<TravelRoute> proproutes = routeListService.getHotRoutes();
            model.addAttribute("proproutes", proproutes);
            PageHelper.clearPage();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
