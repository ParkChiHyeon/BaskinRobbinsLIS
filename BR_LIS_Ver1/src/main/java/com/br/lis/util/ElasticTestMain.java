package com.br.lis.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class ElasticTestMain {
	
	public static void main(String[] args) throws IOException {
		
		ElasticSearchModule test = new ElasticSearchModule("notice_board", "417");
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("title", "엘라스틱 수정 테스트 제목");
		data.put("content", "엘라스틱 수정 테스트 내용");
		test.updateElasticMap(data);
		
		
//		test.deleteElastic();
	}

}
