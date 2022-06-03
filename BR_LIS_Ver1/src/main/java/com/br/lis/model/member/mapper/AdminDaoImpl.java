package com.br.lis.model.member.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.AdminVo;

@Repository
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.member.mapper.AdminDaoImpl.";

	@Override
	public AdminVo loginAdmin(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"loginAdmin", map);
	}
	
}
