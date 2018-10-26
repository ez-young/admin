package cn.admin.file.config;

import cn.admin.file.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author cbk
 * @date 2017/12/29
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**/*").excludePathPatterns("/login","/doLogin","/logout");
        super.addInterceptors(registry);
    }
}
