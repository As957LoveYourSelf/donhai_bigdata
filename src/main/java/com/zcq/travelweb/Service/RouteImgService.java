package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.TravelImg;

public interface RouteImgService extends IService<TravelImg> {
    public TravelImg getRouteImg(String routeid);
}
