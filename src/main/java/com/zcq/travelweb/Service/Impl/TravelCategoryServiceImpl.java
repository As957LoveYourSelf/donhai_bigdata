package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.TravelRouteCategory;
import com.zcq.travelweb.Mapper.CategoryMapper;
import com.zcq.travelweb.Service.TravelCategoryService;
import org.springframework.stereotype.Service;


@Service
public class TravelCategoryServiceImpl extends ServiceImpl<CategoryMapper, TravelRouteCategory> implements TravelCategoryService {

    @Override
    public TravelRouteCategory getRouteCategory() {
        return null;
    }
}
