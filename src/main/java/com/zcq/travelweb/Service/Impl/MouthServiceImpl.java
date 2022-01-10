package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.Mouth;
import com.zcq.travelweb.Mapper.MouthMapper;
import com.zcq.travelweb.Service.MouthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 15:59
 * @className: com.zcq.travelweb.Service.Impl.MouthServiceImpl
 * @description: TODO
 */
@Service
public class MouthServiceImpl extends ServiceImpl<MouthMapper, Mouth> implements MouthService {

    @Autowired(required = false)
    MouthMapper mouthMapper;

    @Override
    public Integer[] showMouth() {
        QueryWrapper<Mouth> queryWrapper = new QueryWrapper<>();
        List<Mouth> mouthList = mouthMapper.selectList(queryWrapper);
        Integer[] mouths = new Integer[12];
        int i = 0;
        for(Mouth mouth : mouthList) {
            mouths[i] = mouth.getNum();
            i++;
        }
        return mouths;
    }
}
