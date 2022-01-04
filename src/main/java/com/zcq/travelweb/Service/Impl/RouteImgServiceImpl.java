package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.TravelImg;
import com.zcq.travelweb.Mapper.RouteImgMapper;
import com.zcq.travelweb.Service.RouteImgService;
import org.springframework.stereotype.Service;

@Service
public class RouteImgServiceImpl extends ServiceImpl<RouteImgMapper, TravelImg> implements RouteImgService {

    //具体业务
    //用于显示各个页面的图片

    @Override
    public TravelImg getRouteImg(String routeid) {
        return null;
    }



}
