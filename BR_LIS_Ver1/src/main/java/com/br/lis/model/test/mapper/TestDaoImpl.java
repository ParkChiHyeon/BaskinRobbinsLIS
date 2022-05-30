package com.br.lis.model.test.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.TestVo;

@Repository
public class TestDaoImpl implements ITestDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";
//	private final String  NS="com.br.lis.model.test.mapper.TestDaoImpl.";

	
	@Override
	public List<Map<String, String>> testSelect() {
		return sqlSession.selectList(NS+"testSelect");
	}
}
