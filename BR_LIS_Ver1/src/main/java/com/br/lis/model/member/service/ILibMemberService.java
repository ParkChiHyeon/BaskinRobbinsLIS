package com.br.lis.model.member.service;

import java.util.Map;

import com.br.lis.vo.LibMemberVo;

public interface ILibMemberService {
	
	public int signUpMember(Map<String, Object> map);

	public int idDuplicateCheck(Map<String, Object> map);
	
	public int duplicateMemberCheck(Map<String, Object> map);
	
	public LibMemberVo loginMember(Map<String, String> resultMap);
	
	public LibMemberVo findId(Map<String, Object> map);

	public int findPw(Map<String, Object> map);

	public int resetUpdatePw(Map<String, Object> map);

	public int updateNewPw(Map<String, Object> map);

	public int updateNmToGh(Map<String, Object> map);

	public LibMemberVo checkResident(Map<String, Object> map);

	public int updateEmail(Map<String, Object> map);

	public LibMemberVo selectMyInfo(Map<String, Object> map);

	public int updateMyInfo(Map<String, Object> map);

	public int quitRequest(Map<String, Object> map);

	public int quitApprove(Map<String, Object> map);

	public LibMemberVo quitSelectMember();

	public int lendingPenalty(Map<String, Object> map);

	public int endPenalty();

	public int notificationYN(Map<String, Object> map);
	
	//비밀번호 확인
	public String pwChk(Map<String, Object> map);
		
	//아이디 확인
	public int idCheck(String id);
	
}
