package com.hzhetun.example.config;

import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈炳坤
 * @date 2018-01-01
 */
@Configuration
@MapperScan("com.hzhetun.example.mapper")
public class MybatisPlusConfig {
    /*@Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }*/
}
