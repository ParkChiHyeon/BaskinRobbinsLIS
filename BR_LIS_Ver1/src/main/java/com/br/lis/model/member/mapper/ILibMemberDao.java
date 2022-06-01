package com.br.lis.model.member.mapper;

import java.util.Map;

import com.br.lis.vo.LibMemberVo;


public interface ILibMemberDao {

	public int signUpMember(Map<String, Object> map);

	public int idDuplicateCheck(Map<String, Object> map);
	
	public int duplicateMemberCheck(Map<String, Object> map);
	
	public int loginMember(Map<String, Object> map);
	
	public LibMemberVo findId(Map<String, Object> map);

	public int findPw(Map<String, Object> map);

	public int resetPw(Map<String, Object> map);

	public int updateNewPw(Map<String, Object> map);

}
