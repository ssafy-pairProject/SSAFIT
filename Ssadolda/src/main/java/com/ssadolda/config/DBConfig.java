package com.ssadolda.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssadolda.model.dao" )
public class DBConfig {

}
