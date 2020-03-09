package com.lhm.qubaManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhm.qubaManage.util.RSAUtils;
import com.lhm.qubaManage.util.RedisUtil;

/**  
 * @package: com.lhm.qubaManage
 * @author: liu huangming
 * @date: 2019年12月26日 下午4:10:31 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
	@Autowired
	RedisTemplate redisTemplate;
	@Test
	public void testString(){
		//操作String类型的数据
		ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
		//存储一条数据
		valueStr.set("goodsProdu","长安");
		//获取一条数据并输出
		String goodsName = valueStr.get("goodsProdu");
		System.out.println(goodsName);
		//存储多条数据
		Map<String,String> map = new HashMap<>();
		map.put("goodsName","福特汽车");
		map.put("goodsPrice","88888");
		map.put("goodsId","88");
 
		valueStr.multiSet(map);
		//获取多条数据
		System.out.println("========================================");
		List<String>list = new ArrayList<>();
		list.add("goodsName");
		list.add("goodsPrice");
		list.add("goodsId");
		list.add("goodsProdu");
 
		List<String> listKeys = valueStr.multiGet(list);
		for (String key : listKeys) {
			System.out.println(key);
		}
 
 
	}
	
	@Test
	public void test2() throws Exception {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		redisUtil.set("keyMap", RSAUtils.getKeyPair());
		System.out.println(redisUtil.get("keyMap"));
	}
}
