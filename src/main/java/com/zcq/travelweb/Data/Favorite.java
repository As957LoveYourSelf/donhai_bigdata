package com.zcq.travelweb.Data;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
@TableName("tab_favorite")
public class Favorite {
    private int rid;
    @DateTimeFormat(pattern = "yyyy-MM-dd :hh:mm:ss")
    @TableId
    private Date date;
    private int uid;
}
