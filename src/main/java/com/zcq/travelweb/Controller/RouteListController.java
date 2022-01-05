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
                              @RequestParam(defaultValue = "") String value,
                              @RequestParam(defaultValue = "selectbyValue") String selectType,
                              @RequestParam(defaultValue = "") String cid,
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
            if (selectType.equals("selectbyValue") && value.equals("")){
                List<TravelRoute> routeList = routeListService.getRouteList();
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?pageNum=");
                return "route_list";
            }
            if (selectType.equals("selectbyCid") && !cid.equals("")){
                List<TravelRoute> routeList = routeListService.getRoutesByCategoryId(Integer.parseInt(cid));
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?selectType="+selectType+"pageNum=");
                return "route_list";
            }
            if (selectType.equals("selectbyValue")){
                List<TravelRoute> routeList = routeListService.getRoutesByKeyValue(value);
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?value="+value+"&pageNum=");
                return "route_list";
            }
            if (selectType.equals("selecthot")){
                List<TravelRoute> routeList = routeListService.getHotRoutes();
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?selectType="+selectType+"pageNum=");
                return "route_list";
            }

        }catch (NullPointerException e){
            model.addAttribute("pageInfo", null);
            model.addAttribute("routes",null);
        }
        finally {
            PageHelper.clearPage();
        }
        return "route_list";
    }
}
