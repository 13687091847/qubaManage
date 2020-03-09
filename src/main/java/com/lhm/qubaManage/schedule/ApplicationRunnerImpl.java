package com.lhm.qubaManage.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.lhm.qubaManage.common.CommonConstant;
import com.lhm.qubaManage.util.RedisUtil;

/**  
 * 项目启动成功后执行
 * @package: com.lhm.qubaManage.schedule
 * @author: liu huangming
 * @date: 2019年12月27日 上午9:41:57 
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner{
	@Value("${spring.rsa.privatekey}")
	private String privatekey; // 私钥
	@Value("${spring.rsa.publickey}")
	private String publickey; // 公钥
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	private Logger logger = LoggerFactory.getLogger(ApplicationRunnerImpl.class);
	@Override
    public void run(ApplicationArguments args) throws Exception {
		logger.info("开始执行=================");
		logger.info("执行完毕=================");
    }
	
	/**
	 * 设置公钥和私钥
	 * @package: com.lhm.qubaManage.schedule
	 * @author: liu huangming
	 * @date: 2019年12月27日 上午9:47:22
	 */
	public void setRSA() {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		if (redisUtil.get(CommonConstant.PRIVATE_KEY) == null) {
			redisUtil.set(CommonConstant.PRIVATE_KEY, this.privatekey);
		}
		if (redisUtil.get(CommonConstant.PUBLIC_KEY) == null) {
			redisUtil.set(CommonConstant.PUBLIC_KEY, this.publickey);
		}
	}
}
