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
            if (selectType.equals("selectbyValue") && value.equals("")){
                PageHelper.startPage(pageNum,9);
                List<TravelRoute> routeList = routeListService.getRouteList();
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?pageNum=");
            }
            if (selectType.equals("selectbyCid") && !cid.equals("")){
                PageHelper.startPage(pageNum,9);
                List<TravelRoute> routeList = routeListService.getRoutesByCategoryId(Integer.parseInt(cid));
                List<TravelRoute> hotRoutes = routeListService.getHotRoutes(4);
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?selectType="+selectType+"&cid="+cid+"&pageNum=");
            }
            if (selectType.equals("selectbyValue")){
                PageHelper.startPage(pageNum,9);
                List<TravelRoute> routeList = routeListService.getRoutesByKeyValue(value);
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?value="+value+"&pageNum=");
            }
            if (selectType.equals("selecthot")){
                PageHelper.startPage(pageNum,10);
                List<TravelRoute> routeList = routeListService.getHotRoutes();
                PageInfo<TravelRoute> pageInfo = new PageInfo<>(routeList);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("routes",routeList);
                model.addAttribute("pageurl", "/toroutelist?selectType="+selectType+"&pageNum=");
                return "favoriterank";
            }

        }catch (NullPointerException e){
            model.addAttribute("pageInfo", null);
            model.addAttribute("routes",null);
        }
        finally {
            PageHelper.clearPage();
        }
        List<TravelRoute> hotRoutes = routeListService.getHotRoutes(5);
        model.addAttribute("hotRoutes",hotRoutes);
        return "route_list";
    }
}
