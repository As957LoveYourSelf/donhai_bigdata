package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.Seller;
import com.zcq.travelweb.Data.TravelImg;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Data.TravelRouteCategory;
import com.zcq.travelweb.Mapper.CategoryMapper;
import com.zcq.travelweb.Mapper.RouteImgMapper;
import com.zcq.travelweb.Mapper.RouteMapper;
import com.zcq.travelweb.Mapper.SellerMapper;
import com.zcq.travelweb.Service.RouteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RouteDetailServiceImpl extends ServiceImpl<RouteMapper, TravelRoute> implements RouteDetailService {

    //具体业务
    //当点击一个旅游路线时，从数据库中获取详细信息，并显示至详细页面
    //通过rid,cid寻找，内容如下：
    //bigPic,smallPic(tab_route_img),tab_category(cname)

    @Autowired(required = false)
    private RouteImgMapper routeImgMapper;
    @Autowired(required = false)
    private CategoryMapper categoryMapper;
    @Autowired(required = false)
    private SellerMapper sellerMapper;

    @Override
    public TravelRoute getRouteDetail(Integer routeid) {
        return selectRouteByRid(routeid);
    }

    private TravelRoute selectRouteByRid(Integer routeid){
        return getById(routeid);
    }

    @Override
    public List<TravelImg> getImgsFromRImgTabByRid(Integer rid){
        QueryWrapper<TravelImg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",rid);
        return routeImgMapper.selectList(queryWrapper);
    }

    @Override
    public Seller getSellerBySid(Integer sid){
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid", sid);
        return sellerMapper.selectOne(queryWrapper);
    }

    @Override
    public TravelRouteCategory getCategoryByCid(Integer cid){
        QueryWrapper<TravelRouteCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        return categoryMapper.selectOne(queryWrapper);
    }
}
