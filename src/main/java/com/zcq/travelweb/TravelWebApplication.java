package com.zcq.travelweb;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zcq.travelweb.Mapper")
@EnableMPP
public class TravelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelWebApplication.class, args);
    }

}
