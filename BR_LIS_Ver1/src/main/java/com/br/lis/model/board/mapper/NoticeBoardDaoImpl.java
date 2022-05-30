package com.br.lis.model.board.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeBoardDaoImpl implements INoticeBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";
}
