package com.br.lis.model.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.board.mapper.IFAQBoardDao;


@Service
public class FAQBoardServiceImpl implements IFAQBoardService {

	@Autowired
	private IFAQBoardDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
