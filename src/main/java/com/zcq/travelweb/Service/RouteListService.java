package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.TravelRoute;

import java.util.List;

public interface RouteListService extends IService<TravelRoute> {
    public List<TravelRoute> getRouteList();
}
