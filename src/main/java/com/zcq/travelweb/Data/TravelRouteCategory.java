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
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Component
@TableName("tab_category")
public class TravelRouteCategory {
    @TableId(value = "cid",type = IdType.AUTO)
    private int tcid;
    @TableField("cname")
    private String tcname;
}
