package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.Mouth;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 15:29
 * @className: com.zcq.travelweb.Service.MouthService
 * @description: TODO
 */
public interface MouthService extends IService<Mouth> {
    Integer[] showMouth();
}
