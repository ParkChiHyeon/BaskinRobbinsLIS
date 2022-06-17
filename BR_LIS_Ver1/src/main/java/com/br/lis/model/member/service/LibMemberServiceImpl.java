package com.br.lis.model.member.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public LibMemberVo findPw(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl findPw : {}",map);
		return mDao.findPw(map);
	}

	@Override
//	@Transactional
	public int resetUpdatePw(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl resetPw : {}", map);
//		int n = mDao.resetPw(map); //비밀번호 1로 초기화
		int m = mDao.updateNewPw(map); //비밀번호 새로 전송
//		String k = mDao.findPwOne(map); // password <<
//		logger.info("새로 발급받은 비밀번호: {}" ,k);
//		int j = mDao.encryptPassword(k, map);
		
		
		
		return (/*n>0*||*/m>0/*||k!=null /*|| j>0*/)? 1:0;
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
	public int updateMyInfoName(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateMyInfoId : {} ", map);
		return mDao.updateMyInfoName(map);
	}
	
	@Override
	public int updateMyInfoPhone(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateMyInfoPhone : {} ", map);
		return mDao.updateMyInfoPhone(map);
	}
	
	
	@Override
	public int updateMyInfoPw(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateMyInfoPw : {} ", map);
		return mDao.updateMyInfoPw(map);
	}
	
	
	@Override
	public int updateMyInfoAddress(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateMyInfoAddress : {} ", map);
		return mDao.updateMyInfoAddress(map);
	}
	
	
	@Override
	public int updateMyInfoEmail(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl  updateMyInfoEmail : {} ", map);
		return mDao.updateMyInfoEmail(map);
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
	public List<LibMemberVo> quitSelectMember() {
		logger.info("Lib_MemberServiceImpl quitSelectMember");
		return mDao.quitSelectMember();
	}

	@Override
	public int lendingPenalty(Map<String, Object> map) {
		logger.info("Lib_MemberServiceImpl lendingPenalty : {} ", map);
		return mDao.lendingPenalty(map);
	}
	
	@Override
	@Scheduled(cron = "0 0 0 * * *")
	public int endPenalty() {
		logger.info("Lib_MemberServiceImpl endPenalty");
		return mDao.endPenalty();
	}

	@Override
	public boolean notificationYN(Map<String, Object> map) {
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

	@Override
	public int quitRequirementChk(Map<String, Object> map) {
		return mDao.quitRequirementChk(map);
	}
	
	
}
