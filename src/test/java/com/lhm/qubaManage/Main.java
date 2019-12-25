package com.lhm.qubaManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**  
 * @package: com.lhm.qubaManage
 * @author: liu huangming
 * @date: 2019年12月19日 下午5:50:29 
 */
@SpringBootApplication
public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(Main.class);
		logger.info("启动成功！");
	}
}
