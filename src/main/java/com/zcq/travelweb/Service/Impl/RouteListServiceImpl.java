package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Mapper.RouteMapper;
import com.zcq.travelweb.Service.RouteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteListServiceImpl extends ServiceImpl<RouteMapper, TravelRoute> implements RouteListService {
    //具体业务
    //获取所有的旅游路线信息，并返回该列表
    //用于显示RouteList的html页面

    @Autowired(required = false)
    private RouteMapper routeMapper;

    @Override
    public List<TravelRoute> getRouteList() {

        QueryWrapper<TravelRoute> queryWrapperRoute = new QueryWrapper<>();
        return routeMapper.selectList(queryWrapperRoute);
    }
}
