package com.br.lis.model.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.member.mapper.IAdminDao;
import com.br.lis.model.test.mapper.ITestDao;
import com.br.lis.vo.AdminVo;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao aDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public AdminVo loginAdmin(Map<String, Object> map) {
		logger.info("AdminServiceImpl loginAdmin : {}", map);
		return aDao.loginAdmin(map);
	}
	
}
