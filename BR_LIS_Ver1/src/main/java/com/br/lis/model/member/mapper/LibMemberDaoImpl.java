package com.br.lis.model.member.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.LibMemberVo;

@Repository
public class LibMemberDaoImpl implements ILibMemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.member.mapper.LibMemberDaoImpl.";

	@Override
	public int signUpMember(Map<String, Object> map) {
		return sqlSession.insert(NS+"signUpMember", map);
	}

	@Override
	public int idDuplicateCheck(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"idDuplicateCheck",map);
		
	}

	@Override
	public int duplicateMemberCheck(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"duplicateMemberCheck", map);
	}

	@Override
	public int loginMember(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"loginMember",map);
	}

	@Override
	public LibMemberVo findId(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"findId", map);
	}

	@Override
	public int findPw(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"findPw", map);
	}

	@Override
	public int resetPw(Map<String, Object> map) {
		return sqlSession.update(NS+"resetPw",map);
	}

	@Override
	public int updateNewPw(Map<String, Object> map) {
		return sqlSession.update(NS+"updateNewPw",map);
	}
	
}
