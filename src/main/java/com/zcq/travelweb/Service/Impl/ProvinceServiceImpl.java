package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.Province;
import com.zcq.travelweb.Mapper.ProvinceMapper;
import com.zcq.travelweb.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 16:36
 * @className: com.zcq.travelweb.Service.Impl.ProvinceServiceImpl
 * @description: TODO
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    @Autowired(required = false)
    ProvinceMapper provinceMapper;

    @Override
    public Integer[] showProvince() {
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        List<Province> provinceList = provinceMapper.selectList(queryWrapper);
        Integer[] provices = new Integer[34];
        int i = 0;
        for (Province province : provinceList) {
            provices[i] = province.getNum();
            i++;
        }
        return provices;
    }
}
