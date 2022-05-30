package com.br.lis.model.lendinginfo.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LendingBookDaoImpl implements ILendingBookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";

}
