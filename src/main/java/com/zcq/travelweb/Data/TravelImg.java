package com.zcq.travelweb.Data;


import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tab_route_img")
public class TravelImg {
    @TableId(type = IdType.AUTO)
    private int rgid;
    private int rid;
    private String bigPic;
    private String smallPic;
}
