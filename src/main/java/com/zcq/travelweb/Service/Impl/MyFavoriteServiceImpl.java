package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.zcq.travelweb.Data.Favorite;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Mapper.MyFavoriteMapper;
import com.zcq.travelweb.Mapper.RouteMapper;
import com.zcq.travelweb.Service.MyFavoriteService;
import com.zcq.travelweb.Service.RouteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MyFavoriteServiceImpl extends MppServiceImpl<MyFavoriteMapper, Favorite> implements MyFavoriteService {

    @Autowired(required = false)
    RouteListService routeListService;
    @Autowired(required = false)
    private Favorite favorite;
    @Autowired(required = false)
    private RouteMapper routeMapper;

    @Override
    public List<TravelRoute> getMyFavorite(String userid) {
        //具体业务
        //通过Userid获取对应用户的收藏信息

        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",userid);
        List<Favorite> favorites = baseMapper.selectList(queryWrapper);
        if (favorites.isEmpty()){
            System.out.println("favorites is null");
            return null;
        }
        List<TravelRoute> routeList = new ArrayList<>();
        for (Favorite favorite:favorites) {
            System.out.println(favorite);
            routeList.add(routeListService.getById(favorite.getRid()));
        }
        return routeList;
    }

    @Override
    public void addFavorite(String uid, Integer rid) {
        favorite.setRid(rid);
        favorite.setUid(uid);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String d = dateFormat.format(date);
        favorite.setDate(strToDateLong(d));
        try{
            saveOrUpdateByMultiId(favorite);
            TravelRoute travelRoute = routeMapper.selectById(rid);
            travelRoute.setCount(travelRoute.getCount()+1);
            routeMapper.updateById(travelRoute);
            System.out.println("添加收藏成功，用户id为："+uid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO:取消收藏，根据uid和rid共同确定(Have Bug)
    @Override
    public void cancelFavorite(String uid, Integer rid) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid).eq("rid",rid);
        try {
            Favorite one = getOne(queryWrapper);
            TravelRoute travelRoute = routeMapper.selectById(one.getRid());
            travelRoute.setCount(travelRoute.getCount()-1);
            routeMapper.updateById(travelRoute);
            System.out.println("Update Success: "+travelRoute);
            deleteByMultiId(one);
            System.out.println("Delete Success: "+one);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<TravelRoute> getFavoriteRankInfo(String rname, Double priceF, Double priceL) {
        priceF = (priceF == null)?0:priceF;
        priceL = (priceL == null)?Double.MAX_VALUE:priceF;
        QueryWrapper<TravelRoute> queryWrapper = new QueryWrapper<>();
        if (rname != null){
            queryWrapper.like("rname", rname)
                    .gt("price",priceF)
                    .lt("price",priceL).orderByDesc("count");
        }else {
            queryWrapper.gt("price",priceF)
                    .lt("price",priceL).orderByDesc("count");
        }
        return routeMapper.selectList(queryWrapper);
    }


    private Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }
}
