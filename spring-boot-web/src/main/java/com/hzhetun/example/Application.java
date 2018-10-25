package com.hzhetun.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableDiscoveryClient 向服务中心注册
 * @EnableFeignClients 开启feign的功能
 * @EnableHystrix 开启断路器的功能
 * @author cbk
 * @date 2017/12/26
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * @LoadBalanced 开启负载均衡
	 * @return
	 */
	@Bean
    @LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	/**
	 * 显示声明CommonsMultipartResolver为mutipartResolver
	 * @return MultipartResolver
	 */
//	@Bean(name = "multipartResolver")
//	public MultipartResolver multipartResolver() {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("UTF-8");
//		//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//		resolver.setResolveLazily(true);
//		resolver.setMaxInMemorySize(40960);
//		//上传文件大小 5M 5*1024*1024
//		resolver.setMaxUploadSize(5 * 1024 * 1024);
//		return resolver;
//	}
}
