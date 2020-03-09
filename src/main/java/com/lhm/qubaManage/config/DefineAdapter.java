package com.lhm.qubaManage.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lhm.qubaManage.interceptor.LoginInterceptor;

/**
 * 拦截器配置
 * 
 * @package: com.lhm.qubaManage.config
 * @author: liu huangming
 * @date: 2019年12月27日 下午1:43:05
 */
@Configuration
public class DefineAdapter implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	// 这个方法是用来配置静态资源的，比如html，js，css，等等
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	// 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("进入");
		// 排除不被拦截的资源
		List<String> excludePaths = new ArrayList<>();
		// layui的静态文件
		excludePaths.add("/layui/**");
		excludePaths.add("/css/**");
		excludePaths.add("/js/**");
		excludePaths.add("/image/**");
		// 静态页面
		excludePaths.add("/**/*.html");
		excludePaths.add("/login.htm");
		// 登录
		excludePaths.add("/user/login.htm");
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePaths);
	}

}
