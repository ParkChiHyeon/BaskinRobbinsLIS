package com.br.lis.model.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.lis.model.member.mapper.ILibMemberDao;
import com.br.lis.vo.LibMemberVo;

@Service
public class LibMemberServiceImpl implements ILibMemberService {
	
	@Autowired
	private ILibMemberDao mDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public int signUpMember(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl signUpMember : {}", map);
		return mDao.signUpMember(map);
	}

	@Override
	public LibMemberVo idDuplicateCheck(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl idDuplicateCheck : {}", map);
		return mDao.idDuplicateCheck(map);
	}

	@Override
	public LibMemberVo duplicateMemberCheck(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl duplicateMemberCheck : {}", map);
		return mDao.duplicateMemberCheck(map);
	}

	@Override
	public LibMemberVo loginMember(Map<String, String> map) {
		logger.info("Lib_MemberServiceImpl loginMember : {}", map);
		return mDao.loginMember(map);
	}

	@Override
	public LibMemberVo findId(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl findId : {}", map);
		return mDao.findId(map);
	}
	
	// 메소드명 바꿀예정
	@Override
	public int findPw(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl findPw : {}",map);
		return mDao.findPw(map);
	}

	@Override
	public int resetUpdatePw(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl resetPw : {}", map);
		int n = mDao.resetPw(map);
		int m = mDao.updateNewPw(map);
		int k = mDao.encryptPassword(map);
		
		
		return (n>0||m>0||k>0)? 1:0;
	}

	@Override
	public int updateNewPw(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl updateNewPw : {} ", map);
		return mDao.updateNewPw(map);
	}

	@Override
	public int updateNmToGh(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl updateNmToGh : {} ", map);
		return mDao.updateNmToGh(map);
	}

	@Override
	public LibMemberVo checkResident(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl checkResident :  {} ", map);
		return mDao.checkResident(map);
	}

	@Override
	public int updateEmail(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateEmail : {} ", map);
		return mDao.updateEmail(map);
	}

	@Override
	public LibMemberVo selectMyInfo(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl selectMyInfo : {} ", map);
		return mDao.selectMyInfo(map);
	}

	@Override
	public int updateMyInfo(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateMyInfo : {} ", map);
		return mDao.updateMyInfo(map);
	}

	@Override
	public int quitRequest(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl quitRequest : {} ", map);
		return mDao.quitRequest(map);
	}

	@Override
	public int quitApprove(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl quitApprove : {} ", map);
		return mDao.quitApprove(map);
	}

	@Override
	public LibMemberVo quitSelectMember() {
		logger.info("Lib_MemberServiceImpl quitSelectMember");
		return mDao.quitSelectMember();
	}

	@Override
	public int lendingPenalty(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl lendingPenalty : {} ", map);
		return mDao.lendingPenalty(map);
	}

	@Override
	public int endPenalty() {
		logger.info("Lib_MemberServiceImpl endPenalty");
		return mDao.endPenalty();
	}

	@Override
	public int notificationYN(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl notificationYN : {} ", map);
		return mDao.notificationYN(map);
	}

	@Override
	public String pwChk(Map<String, Object> map) {
		logger.info("pwChk");
		return mDao.pwChk(map);
	}

	

	@Override
	public int idCheck(String id) {
		logger.info("idCheck");
		return mDao.idCheck(id);
	}
	
	
}
