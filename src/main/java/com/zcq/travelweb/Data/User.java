package com.zcq.travelweb.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.sql.RowSet;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Accessors(chain = true)
@Validated
@TableName("tab_user")
public class User {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uid;
    private String username;
    private String password;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String sex;
    private String telephone;
    private String email;
    private char status;
    private String code;

}
