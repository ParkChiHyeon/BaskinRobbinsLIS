package com.br.lis.model.member.mapper;

import java.util.Map;

import com.br.lis.vo.LibMemberVo;


public interface ILibMemberDao {

	public int signUpMember(Map<String, Object> map);

	public LibMemberVo idDuplicateCheck(Map<String, Object> map);
	
	public LibMemberVo duplicateMemberCheck(Map<String, Object> map);
	
	public LibMemberVo loginMember(Map<String, String> map);
	
	public LibMemberVo findId(Map<String, Object> map);

	public int findPw(Map<String, Object> map);

	public int resetPw(Map<String, Object> map);

	public int updateNewPw(Map<String, Object> map);

	public int updateNmToGh(Map<String, Object> map);

	public LibMemberVo checkResident(Map<String, Object> map);

	public int updateEmail(Map<String, Object> map);

	public LibMemberVo selectMyInfo(Map<String, Object> map);

	public int updateMyInfoName(Map<String, Object> map);
	
	public int updateMyInfoPw(Map<String, Object> map);
	
	public int updateMyInfoPhone(Map<String, Object> map);
	
	public int updateMyInfoAddress(Map<String, Object> map);
	
	public int updateMyInfoEmail(Map<String, Object> map);

	public int quitRequest(Map<String, Object> map);

	public int quitApprove(Map<String, Object> map);

	public LibMemberVo quitSelectMember();

	public int lendingPenalty(Map<String, Object> map);

	public int endPenalty();

	public int notificationYN(Map<String, Object> map);

//	public int encryptPassword(String k);
	
	//비밀번호 확인
	public String pwChk(Map<String, Object> map);
		
	//아이디 확인
	public int idCheck(String id);
	
	public String findPwOne(Map<String, Object> map);

	public int encryptPassword(String k, Map<String, Object> map);

	

	
	
		

}
