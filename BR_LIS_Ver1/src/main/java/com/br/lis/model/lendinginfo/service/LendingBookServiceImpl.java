package com.br.lis.model.lendinginfo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.lis.model.lendinginfo.mapper.ILendingBookDao;

public class LendingBookServiceImpl implements ILendingBookService {

	@Autowired
	private ILendingBookDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
