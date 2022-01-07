package com.zcq.travelweb.Data;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
@TableName("tab_route")
public class TravelRoute {
    @TableId(type = IdType.AUTO)
    private int rid;
    private String rname;
    private double price;
    private String routeIntroduce;
    private char rflag;
    private String rdate;
    private char isThemeTour;
    private int count;
    private int cid;
    private String rimage;
    private int sid;
    private String sourceId;
}
