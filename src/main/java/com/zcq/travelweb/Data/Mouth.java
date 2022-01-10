package com.zcq.travelweb.Data;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @version 1.0
 * @author: Hanguchunsheng
 * @create: 2022-01-10 15:03
 * @className: com.zcq.travelweb.Data.Mouths
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Accessors(chain = true)
@Validated
@TableName("tab_mouths")
public class Mouth {
    private int num;
    private String mouth;
}
