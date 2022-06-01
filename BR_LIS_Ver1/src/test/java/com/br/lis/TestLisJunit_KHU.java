package com.br.lis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.lis.model.member.mapper.IAdminDao;
import com.br.lis.model.member.mapper.ILibMemberDao;
import com.br.lis.vo.LibMemberVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_KHU {
	
	@Autowired
	private ILibMemberDao mDao;
	
	@Autowired
	private IAdminDao aDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/* 중복회원 검사 BR_W_UM001 */
	@Test
	public void duplicateMemberCheck() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		map.put("phone", "01085439644");
		map.put("birth", "9705161******");
		int cnt = mDao.duplicateMemberCheck(map);
		logger.info("중복회원 검사 결과 : {}", cnt);
	}
	

	/* 일반 회원가입 BR_W_UM002 */
//	@Test
	public void signUpMember() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd125852");
		map.put("password", "Clsclffk@12");
		map.put("phone", "01085439644");
		map.put("name", "김휘웅");
		map.put("address", "주소주소");
		map.put("birth", "9705161******");
		int cnt = mDao.signUpMember(map);
		logger.info("회원가입 결과 : {}", cnt);
	}
	
	/* 아이디 중복 검사 BR_W_UM003 */
//	@Test
	public void idDuplicateCheck() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
		int cnt = mDao.idDuplicateCheck(map);
		logger.info("중복아이디 결과 : {}" , cnt);
	}
	
	/* 일반회원 로그인 BR_W_UM009*/
//	@Test
	public void loginMember() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
		map.put("password", "Clsclffk@12");
		int cnt = mDao.loginMember(map);
		logger.info("회원 로그인 결과 : {}", cnt);
	}
	
	/* 관리자 로그인 BR_W_UM010*/
//	@Test
	public void loginAdmin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin_id", "admin001");
		map.put("password", "Clsclffk@12");
		int cnt = aDao.loginAdmin(map);
		logger.info("관리자 로그인 결과 : {}", cnt);
	}
	
	/* 회원 아이디 찾기 BR_W_UM_011 */
//	@Test
	public void findId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "김휘웅1");
		map.put("phone", "01042935376");
		LibMemberVo vo = mDao.findId(map);
		logger.info("찾은 아이디 : {}", vo.getMember_id());
	}
	
	/* 비밀번호 찾기 인증BR_W_UM_012  */
//	@Test
	public void findPw() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", "gnldnd11");
		map.put("password", "Clsclffk@12");
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
		map.put("password", "Clsclffk@12");
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
	
	/*	*/
	
	
	
	
	
	
	
	
	
	

	
	
	
	

}
