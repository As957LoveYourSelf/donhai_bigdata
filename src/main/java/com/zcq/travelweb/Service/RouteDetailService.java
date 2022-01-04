package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.Seller;
import com.zcq.travelweb.Data.TravelImg;
import com.zcq.travelweb.Data.TravelRoute;
import com.zcq.travelweb.Data.TravelRouteCategory;

import java.util.List;
import java.util.Map;

public interface RouteDetailService extends IService<TravelRoute> {
    TravelRoute getRouteDetail(Integer routeid);
    List<TravelImg> getImgsFromRImgTabByRid(Integer rid);
    Seller getSellerBySid(Integer sid);
    TravelRouteCategory getCategoryByCid(Integer cid);
}
