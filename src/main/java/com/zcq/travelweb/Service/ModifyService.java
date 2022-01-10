package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.User;

import java.util.Date;

public interface ModifyService extends IService<User> {
    boolean UpdateUser(User user);
}
