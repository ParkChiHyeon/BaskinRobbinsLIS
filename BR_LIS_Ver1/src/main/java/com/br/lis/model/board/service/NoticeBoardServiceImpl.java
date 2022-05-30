package com.br.lis.model.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.board.mapper.INoticeBoardDao;

@Service
public class NoticeBoardServiceImpl implements INoticeBoardService {

	@Autowired
	private INoticeBoardDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
