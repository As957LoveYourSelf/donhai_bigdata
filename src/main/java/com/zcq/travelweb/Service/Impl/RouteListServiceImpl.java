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
    //搜索特定线路名称，进行模糊查询，返回旅游线路列表
    //根据类别搜索旅游线路
    //用于显示RouteList的html页面

    @Autowired(required = false)
    private RouteMapper routeMapper;

    @Override
    public List<TravelRoute> getRouteList() {

        QueryWrapper<TravelRoute> queryWrapperRoute = new QueryWrapper<>();
        return routeMapper.selectList(queryWrapperRoute);
    }

    //通过旅游类别查找
    @Override
    public List<TravelRoute> getRoutesByCategoryId(Integer cid) {
        QueryWrapper<TravelRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        return routeMapper.selectList(queryWrapper);
    }

    //通过
    @Override
    public List<TravelRoute> getRoutesByKeyValue(String value) {
        QueryWrapper<TravelRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("rname", value);
        return routeMapper.selectList(queryWrapper);
    }

    @Override
    public List<TravelRoute> getHotRoutes() {
        QueryWrapper<TravelRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("count");
        return routeMapper.selectList(queryWrapper);
    }


}
