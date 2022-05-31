package com.br.lis.model.member.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibMemberDaoImpl implements ILibMemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.member.mapper.LibMemberDaoImpl.";

	@Override
	public int signUpMember(Map<String, Object> map) {
		return sqlSession.insert(NS+"signUpMember", map);
	}
	
}
