package com.zcq.travelweb.Controller;

import com.sun.xml.internal.ws.developer.Serialization;
import com.zcq.travelweb.Service.MouthService;
import com.zcq.travelweb.Service.ProvinceService;
import com.zcq.travelweb.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 11:30
 * @className: com.zcq.travelweb.Controller.VisualController
 * @description: TODO
 */
@Controller
public class VisualController {

    @Autowired(required = false)
    private TimeService timeService;
    @Autowired(required = false)
    private MouthService mouthService;
    @Autowired(required = false)
    private ProvinceService provinceService;

    @RequestMapping("/toecharts")
    public String toecharts(){
        return "/visual";
    }

    @RequestMapping("/getEcharts")
    @ResponseBody
    public Map<String, Integer[]> visual() {
        Map<String, Integer[]> map = new HashMap<>();
        Integer[] times = timeService.showTime();
        Integer[] mouths = mouthService.showMouth();
        Integer[] provinces = provinceService.showProvince();
        System.out.println(times.toString()+" "+mouths.toString()+" "+provinces.toString());
        map.put("times", times);
        map.put("mouths", mouths);
        map.put("provinces", provinces);
        System.out.println(map);
        return map;
    }
}
