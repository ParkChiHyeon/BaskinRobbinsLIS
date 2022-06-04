package com.br.lis.model.purchaseinfo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.test.mapper.ITestDao;

@Service
public class RequestPurchaseServiceImpl implements IRequestPurchaseService {

	
	@Autowired
	private ITestDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
