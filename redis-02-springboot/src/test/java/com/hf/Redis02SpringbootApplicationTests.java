package com.hf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hf.pojo.User;
import com.hf.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void test1(){
		redisUtil.set("name","zhangsan");
		System.out.println(redisUtil.get("name"));
	}

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("myKey","myValue");
		System.out.println(redisTemplate.opsForValue().get("myKey"));
	}

	@Test
	public void test(){
		User user = new User("zhangsan", 12);
		String jsonUser = null;
		try {
			jsonUser = new ObjectMapper().writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		redisTemplate.opsForValue().set("user",jsonUser);
		System.out.println(redisTemplate.opsForValue().get("user"));

	}

}
