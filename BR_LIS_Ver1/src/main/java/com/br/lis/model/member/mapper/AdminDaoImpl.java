package com.br.lis.model.member.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";
	
}
