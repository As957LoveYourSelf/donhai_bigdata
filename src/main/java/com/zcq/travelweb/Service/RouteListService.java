package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.TravelRoute;

import java.util.List;

public interface RouteListService extends IService<TravelRoute> {
    List<TravelRoute> getRouteList();
    List<TravelRoute> getRoutesByCategoryId(Integer cid);
    List<TravelRoute> getRoutesByKeyValue(String value);
    List<TravelRoute> getHotRoutes();
    List<TravelRoute> getHotRoutesBy(Integer cid);
    List<TravelRoute> getThemeRoutes();
    List<TravelRoute> getNewestRoutes();
}
