package com.zcq.travelweb.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcq.travelweb.Data.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
