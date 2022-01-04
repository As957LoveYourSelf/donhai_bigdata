package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.Favorite;
import com.zcq.travelweb.Data.TravelRoute;

import java.util.List;

public interface MyFavoriteService extends IService<Favorite> {
    List<TravelRoute> getMyFavorite(String userid, List<TravelRoute> routeList);
    boolean addFavorite(Integer uid, Integer rid);
}
