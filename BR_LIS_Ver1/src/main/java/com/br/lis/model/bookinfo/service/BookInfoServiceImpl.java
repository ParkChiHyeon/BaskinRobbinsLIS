package com.br.lis.model.bookinfo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.bookinfo.mapper.IBookInfoDao;

@Service
public class BookInfoServiceImpl implements IBookInfoService {

	@Autowired
	private IBookInfoDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
