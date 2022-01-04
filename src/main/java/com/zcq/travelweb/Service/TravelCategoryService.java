package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.TravelRouteCategory;

public interface TravelCategoryService extends IService<TravelRouteCategory> {
    public TravelRouteCategory getRouteCategory();
}
