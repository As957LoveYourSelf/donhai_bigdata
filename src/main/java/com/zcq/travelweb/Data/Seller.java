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
@TableName("tab_seller")
public class Seller {

    @TableId(type = IdType.AUTO)
    private int sid;
    private String sname;
    @TableField(value = "consphone")
    private String sellerphone;
    @TableField(value = "address")
    private String selleraddress;
}
