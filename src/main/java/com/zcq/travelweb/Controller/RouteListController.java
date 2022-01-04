package com.zcq.travelweb.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Service.RouteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RouteListController {

    @Autowired(required = false)
    RouteListService routeListService;

    @RequestMapping({"/toroutelist"})
    public String toRouteList(Model model,
                              @RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println(pageNum);
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageNum <= 0){
            pageNum = 1;
        }

        try{
            PageHelper.startPage(pageNum,9);
            List<TravelRoute> routeList = routeListService.getRouteList();
            PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("routes",routeList);
        }finally {
            PageHelper.clearPage();
        }
        return "route_list";
    }
}
