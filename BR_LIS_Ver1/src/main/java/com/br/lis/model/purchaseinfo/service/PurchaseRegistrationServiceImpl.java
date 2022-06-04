package com.br.lis.model.purchaseinfo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.purchaseinfo.mapper.IPurchaseRegistrationDao;

@Service
public class PurchaseRegistrationServiceImpl implements IPurchaseRegistrationService {

	@Autowired
	private IPurchaseRegistrationDao registDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	

	
}
