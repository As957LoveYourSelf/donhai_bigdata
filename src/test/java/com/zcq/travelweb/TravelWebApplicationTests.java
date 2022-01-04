package com.zcq.travelweb;

import com.zcq.travelweb.Service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TravelWebApplicationTests {

    @Autowired(required = false)
    private LoginService loginService;

    @Test
    public void test(){

    }

}
