package com.br.lis;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.lis.model.test.service.ITestService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit {

	@Autowired
	private ITestService service;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testSelect() {
		logger.info("selectTest Junit");
		List<Map<String, String>> lists = service.testSelect();
		System.out.println("---------------------------------------------------------------------------------");
		
//		for (TestVo v : lists) {
//			System.out.println(v);
//		}
		for(Map<String, String> map : lists){
			for(Map.Entry<String, String> entry:map.entrySet()){
			        String key = entry.getKey();
			        Object value = entry.getValue();
			     System.out.println(key + " | " + value);
			}
			System.out.println("------------------------------------------------------------------");
		}
	}

}
