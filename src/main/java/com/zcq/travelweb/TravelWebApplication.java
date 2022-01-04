package com.zcq.travelweb;

import com.zcq.travelweb.Data.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(value = "com.zcq.travelweb.Mapper")
@EnableCaching
public class TravelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelWebApplication.class, args);
    }

}
