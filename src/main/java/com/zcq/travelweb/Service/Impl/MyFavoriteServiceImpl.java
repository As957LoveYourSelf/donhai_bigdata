package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.Favorite;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Mapper.MyFavoriteMapper;
import com.zcq.travelweb.Service.MyFavoriteService;
import com.zcq.travelweb.Service.RouteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MyFavoriteServiceImpl extends ServiceImpl<MyFavoriteMapper, Favorite> implements MyFavoriteService {

    @Autowired(required = false)
    MyFavoriteMapper myFavoriteMapper;
    @Autowired(required = false)
    RouteListService routeListService;
    @Autowired
    Favorite favorite;


    @Override
    public List<TravelRoute> getMyFavorite(String userid, List<TravelRoute> routeList) {
        //具体业务
        //通过Userid获取对应用户的收藏信息
        QueryWrapper<Favorite> queryWrapperUser = new QueryWrapper<>();
        queryWrapperUser.eq("uid",userid);
        List<Favorite> favorites = myFavoriteMapper.selectList(queryWrapperUser);
        for (Favorite favorite:favorites) {
            routeList.add(routeListService.getById(favorite.getRid()));
        }
        if (favorites.isEmpty()){
            return null;
        }
        return routeList;
    }

    @Override
    public boolean addFavorite(Integer uid, Integer rid) {
        favorite.setRid(rid);
        favorite.setUid(uid);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String d = dateFormat.format(date);
        favorite.setDate(strToDateLong(d));
        System.out.println(favorite);
        try{
            saveOrUpdate(favorite);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }
}
