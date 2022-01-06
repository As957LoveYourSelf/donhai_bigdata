package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.zcq.travelweb.Data.Favorite;
import com.zcq.travelweb.Data.TravelRoute;

import java.util.List;

public interface MyFavoriteService extends IMppService<Favorite> {
    List<TravelRoute> getMyFavorite(String userid);
    void addFavorite(String uid, Integer rid);
    void cancelFavorite(String uid, Integer rid);
}
