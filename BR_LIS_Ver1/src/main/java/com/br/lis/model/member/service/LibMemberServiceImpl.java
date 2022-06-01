package com.br.lis.model.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.member.mapper.ILibMemberDao;

@Service
public class LibMemberServiceImpl implements ILibMemberService {
	
	@Autowired
	private ILibMemberDao mDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public int signUpMember(Map<String, Object> map) {
		return mDao.signUpMember(map);
	}
	
	
	
}
