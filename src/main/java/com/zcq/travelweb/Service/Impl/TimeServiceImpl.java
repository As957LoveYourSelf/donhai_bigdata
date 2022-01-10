package com.zcq.travelweb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcq.travelweb.Data.Time;
import com.zcq.travelweb.Mapper.TimeMapper;
import com.zcq.travelweb.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 15:35
 * @className: com.zcq.travelweb.Service.Impl.TimeServiceImpl
 * @description: TODO
 */
@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements TimeService {

    @Autowired(required = false)
    TimeMapper timeMapper;

    @Override
    public Integer[] showTime() {
        QueryWrapper<Time> queryWrapper = new QueryWrapper<>();
        List<Time> timeList = timeMapper.selectList(queryWrapper);
        Integer[] times = new Integer[4];
        int i = 0;
        for(Time time : timeList) {
            times[i] = time.getNum();
            i++;
        }
        return times;
    }
}
