package com.br.lis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.lis.model.member.mapper.IAdminDao;
import com.br.lis.model.member.mapper.ILibMemberDao;
import com.br.lis.model.member.service.ILibMemberService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.LibMemberVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_KHU {
	
	@Autowired
	private ILibMemberDao mDao;
	
	@Autowired
	private ILibMemberService msDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAdminDao aDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/* 중복회원 검사 BR_W_UM001 */
//	@Test
	public void duplicateMemberCheck() {
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("member_id", "gnldnd125852");
		map.put("phone", "01085439644");
		map.put("birth", "9705161******");
		LibMemberVo cnt = mDao.duplicateMemberCheck(map);
		logger.info("중복회원 검사 결과 : {}", cnt);
	}
	

	/* 일반 회원가입 BR_W_UM002 */
//	@Test
	public void signUpMember() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "user001");
		map.put("password", "Xptmxm@12");
		String enPassword = passwordEncoder.encode((String)map.get("password"));
		map.put("password", enPassword);
		map.put("phone", "01085439648");
		map.put("name", "테스트이름");
		map.put("address", "테스트 주소");
		map.put("birth", "9705164******");
		int cnt = mDao.signUpMember(map);
		logger.info("회원가입 결과 : {}", cnt);
	}
	
	/* 아이디 중복 검사 BR_W_UM003 */
//	@Test
	public void idDuplicateCheck() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
		LibMemberVo cnt = mDao.idDuplicateCheck(map);
		logger.info("중복아이디 결과 : {}" , cnt);
	}
	
	/* 일반회원 로그인 BR_W_UM009*/
//	@Test
	public void loginMember() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", "securityTest123");
		map.put("password", "Clsclffk@12");
		LibMemberVo vo = mDao.loginMember(map);
		logger.info("회원 로그인 결과 : {}", vo);
	}
	
	/* 관리자 로그인 BR_W_UM010*/
//	@Test
	public void loginAdmin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin_id", "securityTest123");
		map.put("password", "Clsclffk@12");
		AdminVo vo= aDao.loginAdmin(map);
		logger.info("관리자 로그인 결과 : {}", vo);
	}
	
	/* 회원 아이디 찾기 BR_W_UM_011 */
	@Test
	public void findId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "김휘웅1");
		map.put("phone", "01042935376");
		LibMemberVo vo = mDao.findId(map);
		logger.info("찾은 아이디 : {}", vo);
	}
	
	/* 비밀번호 찾기 인증BR_W_UM_012  */
//	@Test
	public void findPw() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
//		map.put("password", "Clsclffk@12");
		map.put("phone", "01042935376");
		int cnt = mDao.findPw(map);
		logger.info("비밀번호 인증 결과 : {}", cnt);
	}
	/* 비밀번호 초기화 BR_W_UM_013 
	 * 13번과 14번 트랜잭션 (서비스로 다시)
	 *  */
//	@Test
	public void resetPw() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
//		map.put("password", "$2a$10$k16LX0byXeT39JfIBfkkSe1K72h1d.WFGFv22wXjLS2q.urYlDUS6");
		map.put("phone", "01042935376");
		int cnt = mDao.resetPw(map);
		logger.info("비밀번호 초기화결과 : {}", cnt);
	}
	/* 임시 비밀번호 발급  BR_W_UM014 
	 * 13번과 14번 트랜잭션  (서비스로 다시)
	 * 들어갈때 암호화
	 * */
//	@Test
	public void updateNewPw() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
		map.put("phone", "01042935376");
		int cnt = mDao.updateNewPw(map);
		logger.info("비밀번호 변경 결과: {} ", cnt);
	}
	
//	@Test
	public void resetUpdatePw() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
		map.put("password", "$2a$10$k16LX0byXeT39JfIBfkkSe1K72h1d.WFGFv22wXjLS2q.urYlDUS6");
		map.put("phone", "01042935376");	
//		mDao.updateNewPw(map);
		int cnt = msDao.resetUpdatePw(map);
		
		
		logger.info("트랜잭션 변경 결과: {} ", cnt);
	}
	
	
	/* 	  --2022-06-01--
	 * 관내회원 등록 BR_W_UM_015 
	 * 15,16,17 트랜잭션
	 * */
//	@Test
	public void updateNmToGh() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		map.put("filepath", "C:\\eclipse_web_2020_09");
		int cnt = mDao.updateNmToGh(map);
		logger.info("관내회원 등록 결과: {} ", cnt);
	}
	
	/* 등본인증 BR_W_UM_016
	 * 15,16,17 트랜잭션
	 *  */
//	@Test
	public void checkResident() {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("name", "김휘웅");
		 map.put("address", "주소주소");
		 map.put("birth", "9705161******");
		 LibMemberVo vo = mDao.checkResident(map);
		 logger.info("등본의 아이디 정보 :{}", vo);
		}
	
	/* 이메일 인증(업데이트) BR_W_UM_017 
	 * 15,16,17 트랜잭션
	 * */
//	@Test
	public void updateEmail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", "test125852@gmail.com");
		map.put("member_id", "gnldnd125852");
		int cnt  = mDao.updateEmail(map);
		logger.info("이메일 업데이트 결과 :{}", cnt);
	}
	
	/* 개인정보 조회 BR_W_UM_024 */
//	@Test
	public void selectMyInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		LibMemberVo vo = mDao.selectMyInfo(map);
		logger.info("개인 정보 조회 :{}", vo);
	}
		
	/* 개인정보 수정 BR_W_UM_018 */
//	@Test
	public void updateMyInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", "01042997777");
		map.put("email", "gnldnd125852@gmail.com");
		map.put("address", "수정된 주소");
		map.put("password", "Clsclffk@123");
		map.put("member_id", "gnldnd125852");
		int cnt = mDao.updateMyInfo(map);
		logger.info("개인정보 수정 결과 :{}", cnt);
	}
	
	/* 회원탈퇴 신청 BR_W_UM_019 */
//	@Test
	public void quitRequest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		int cnt = mDao.quitRequest(map);
		logger.info("회원탈퇴 신청 결과 :{}", cnt);
	}	
	
	/* 회원탈퇴 신청 BR_W_UM_020 */
//	@Test
	public void quitApprove() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		int cnt = mDao.quitApprove(map);
		logger.info("회원탈퇴 승인 결과 :{}", cnt);
	}
	
	/* 회원탈퇴 신청 조회 BR_W_UM_021 */
//	@Test
	public void quitSelectMember() {
		LibMemberVo vo = mDao.quitSelectMember();
		logger.info("회원탈퇴 신청 조회 :{}", vo);
	}
	
	/* 패널티 부여 BR_W_UM_022 */
//	@Test
	public void lendingPenalty() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_code", "M2205000769");
		map.put("lending_seq", "LEN190614000013033");
		int cnt = mDao.lendingPenalty(map);
		logger.info("패널티 부여 결과 :{}", cnt);
	}
	
	/* 패널티 부여 BR_W_UM_023 */
//	@Test
	public void endPenalty() {
		int cnt = mDao.endPenalty();
		logger.info("패널티 복구 결과 :{}", cnt);
	}
	
	/* 알림설정 BR_W_UMA_001 */
//	@Test
	public void notificationYN() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		int cnt = mDao.notificationYN(map);
		logger.info("알림 설정 결과 :{}", cnt);
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
	

}
