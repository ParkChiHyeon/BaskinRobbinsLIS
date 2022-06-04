package com.br.lis.model.test.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.test.mapper.ITestDao;
import com.br.lis.vo.TestVo;

@Service
public class TestServiceImpl implements ITestService {
	
	
	@Autowired
	private ITestDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public List<Map<String, String>> testSelect() {
		logger.info("TestServiceImpl testSelect");
		return dao.testSelect();
	}

}
