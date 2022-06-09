package com.br.lis.model.member.mapper;

import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.LibMemberVo;

@Repository
public class LibMemberDaoImpl implements ILibMemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final String  NS="com.br.lis.model.member.mapper.LibMemberDaoImpl.";

	@Override
	public int signUpMember(Map<String, Object> map) {
		String enPassword = passwordEncoder.encode((String)map.get("password"));		
		map.put("password", enPassword);
		System.out.println("Spring security에 의해서 암호화된 비밀번호 :" + map.get("password"));
		return sqlSession.insert(NS+"signUpMember", map);
	}

	@Override
	public LibMemberVo idDuplicateCheck(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"idDuplicateCheck",map);
		
	}

	@Override
	public LibMemberVo duplicateMemberCheck(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"duplicateMemberCheck", map);
	}

	@Override
	public LibMemberVo loginMember(Map<String, String> map) {
		LibMemberVo vo = null;
		
		System.out.println("스프링 시큐리티 암호화");
		System.out.println("화면에서 전달받은 요청 값 \t : "+map.get("password"));
		
		String enPw = passwordEncoder.encode((String) map.get("password"));	
		
		System.out.println("화면에서 전달 받은 값을 암호화 처리 \t :" + enPw );
		
		String dbPw = sqlSession.selectOne(NS+"pwChk",map.get("member_id"));
		if (passwordEncoder.matches((String) map.get("password"), dbPw)) {
			vo = sqlSession.selectOne(NS+"enLogin",map.get("member_id"));
		}
		
		return vo;
	}
	
	@Override
	public int idCheck(String id) {
		return sqlSession.selectOne(NS+"idCheck",id);
	}
	
	public String pwChk(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"pwChk", map);
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

	@Override
	public int updateNmToGh(Map<String, Object> map) {
		return sqlSession.update(NS+"updateNmToGh",map);
	}

	@Override
	public LibMemberVo checkResident(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"checkResident",map);
	}

	@Override
	public int updateEmail(Map<String, Object> map) {
		return sqlSession.update(NS+"updateEmail",map);
	}

	@Override
	public LibMemberVo selectMyInfo(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"selectMyInfo",map);
	}

	@Override
	public int updateMyInfo(Map<String, Object> map) {
		return sqlSession.update(NS+"updateMyInfo",map);
	}

	@Override
	public int quitRequest(Map<String, Object> map) {
		return sqlSession.update(NS+"quitRequest",map);
	}

	@Override
	public int quitApprove(Map<String, Object> map) {
		return sqlSession.update(NS+"quitApprove",map);
	}

	@Override
	public LibMemberVo quitSelectMember() {
		return sqlSession.selectOne(NS+"quitSelectMember");
	}

	@Override
	public int lendingPenalty(Map<String, Object> map) {
		return sqlSession.update(NS+"lendingPenalty",map);
	}

	@Override
	public int endPenalty() {
		return sqlSession.update(NS+"endPenalty");
	}

	@Override
	public int notificationYN(Map<String, Object> map) {
		return sqlSession.update(NS+"notificationYN",map);
	}
	
//	@Override
//	public int encryptPassword(Map<String, Object> map) {
//		String enPassword = passwordEncoder.encode((String)map.get("password"));
//		System.out.println("######"+enPassword);
//		map.put("password", enPassword);
//		System.out.println("######"+map);
//		return sqlSession.update(NS+"encryptPassword",map);
//	}

	@Override
	public String findPwOne(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"findPwOne",map);
	}

	
	@Override
	public int encryptPassword(String k, Map<String, Object> map) {
		String enPassword = passwordEncoder.encode(k);
		System.out.println("######"+enPassword);
		map.put("password", enPassword);
		System.out.println("######"+map);
		return sqlSession.update(NS+"encryptPassword",map);
	}

	
	
}
