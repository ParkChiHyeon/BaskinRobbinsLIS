package com.br.lis.model.purchaseinfo.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestPurchaseDaoImpl implements IRequestPurchaseDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";
	
}
