package com.lhm.qubaManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @package: com.lhm.qubaManage
 * @author: liu huangming
 * @date: 2019年12月19日 下午5:50:29
 */
@SpringBootApplication
@EnableScheduling
public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		// 解决controller返回json报406的错误
//		configurer.favorPathExtension(false);
//	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(Main.class);
		logger.info("启动成功！");
	}
}
