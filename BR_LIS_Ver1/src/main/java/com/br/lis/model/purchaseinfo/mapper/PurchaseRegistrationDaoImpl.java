package com.br.lis.model.purchaseinfo.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PurchaseRegistrationDaoImpl implements IPurchaseRegistrationDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.purchaseinfo.mapper.PurchaseRegistrationDaoImpl.";

	
	

	
	
	
}
