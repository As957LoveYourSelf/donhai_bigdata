package com.zcq.travelweb.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcq.travelweb.Data.User;

import java.util.List;

public interface PersonService extends IService<User> {
    User getUserInformation(String uid);
}

