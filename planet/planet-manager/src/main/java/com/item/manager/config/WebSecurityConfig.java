package com.item.manager.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author zcm
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 所有的当前站点的请求地址，都支持跨域访问。
        registry.addMapping("/**")
                // 所有的外部域都可跨域访问。 如果是localhost则很难配置，因为在跨域请求的时候，外部域的解析可能是localhost、127.0.0.1、主机名
                .allowedOrigins("*")
                // 是否支持跨域用户凭证
                .allowCredentials(true)
                // 当前站点支持的跨域请求类型是什么
                .allowedMethods(ORIGINS)
                // 超时时长设置为1小时。 时间单位是秒。
                .maxAge(3600);
    }

}