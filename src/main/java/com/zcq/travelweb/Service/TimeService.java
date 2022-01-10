package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.Time;

import java.util.List;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 15:29
 * @className: com.zcq.travelweb.Service.TimeService
 * @description: TODO
 */
public interface TimeService extends IService<Time> {
    Integer[] showTime();
}
