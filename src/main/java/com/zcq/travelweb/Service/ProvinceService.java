package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.Province;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 15:30
 * @className: com.zcq.travelweb.Service.ProvinceService
 * @description: TODO
 */
public interface ProvinceService extends IService<Province> {
    Integer[] showProvince();
}
