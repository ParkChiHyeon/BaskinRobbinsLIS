package com.br.lis.model.test.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.TestVo;

public interface ITestDao {

	public List<Map<String, String>> testSelect();
	
}
