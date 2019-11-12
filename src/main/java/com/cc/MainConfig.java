package com.cc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.cc.mapper")
@ComponentScan(basePackages = {"com.cc.*","com.cc.config.*"})
public class MainConfig {

}
