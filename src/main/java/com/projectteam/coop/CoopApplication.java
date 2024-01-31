package com.projectteam.coop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableFeignClients
@SpringBootApplication
@ServletComponentScan(basePackages = "com.projectteam.coop.util") // @WebListener 스캔을 가능하도록
public class CoopApplication {
    //외부 설정파일을 이용해서 스프링 부트 구성
    public static void main(String[] args) {
        String APPLICATION_LOCATIONS = "spring.config.import="
                + "optional:../properties/local_security/coop_server_local.yml,"
                + "optional:../properties/coop_server.yml";
        new SpringApplicationBuilder(CoopApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
//        SpringApplication.run(CoopApplication.class, args);
    }
}
