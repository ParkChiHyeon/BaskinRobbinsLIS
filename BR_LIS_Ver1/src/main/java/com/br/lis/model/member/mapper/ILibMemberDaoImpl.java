package com.br.lis.model.member.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ILibMemberDaoImpl implements ILibMemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";
	
}
