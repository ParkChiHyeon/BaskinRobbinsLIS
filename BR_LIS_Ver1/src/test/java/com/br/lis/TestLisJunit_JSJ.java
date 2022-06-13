package com.br.lis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.lis.model.lendinginfo.mapper.ILendingBookDao;
import com.br.lis.model.lendinginfo.service.ILendingBookService;
import com.br.lis.model.member.mapper.ILibMemberDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_JSJ {
	
	@Autowired
	private ILendingBookDao dao;
	
	@Autowired
	private ILibMemberDao iDao;
	
	@Autowired
	private ILendingBookService bService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
// OK
	@Test  
	public void nowLendingBook() {
		logger.info("nowLendingBook _ 즉시대출 가능한 도서조회");
		String book_serial = "BKSR100213";
		List<LendBookBean> n= dao.nowLendingBook(book_serial);
		System.out.println(n);
	}
	
//	@Test  
	public void memberSelect() {
		logger.info("memberSelect _ 회원조회");
		Map<String,Object> mMap = new HashMap<String, Object>();
		mMap.put("member_id","user001");
		LibMemberVo vo = iDao.selectMyInfo(mMap);
		System.out.println(vo);
	}
	
	// OK	
//	@Test
	public void allReserveLending() {
		logger.info("allReserveLending _ 예약한 목록 전체 조회");
//		List<BookInfoVo> lists= dao.allReserveLending();
//		System.out.println(lists);
	}
	
	// OK	
//	@Test
	public void reserveLendingBook() {
		logger.info("reserveLendingBook _ 회원 예약 목록 조회");
////		List<LendingVo> lists= dao.reserveLendingBook();
//		
//		String member_code = "M2205000005";
//		int n =dao.reserveLendingBook(member_code);
//		System.out.println(n);
		
		
//		String member_code = "M2205000005";
//		List<LendBookBean> lists = dao.reserveLendingBook(member_code);
//		System.out.println(lists);
		
		
		List<Map<String, Object>>  map = new ArrayList<Map<String,Object>>();
		String membercode = "M2205000005";
		
		//map.put("member_code", "M2205000005");
		List<Map<String, Object>> Ib =dao.reserveLendingBook(membercode);
		Map<String, Object> a = Ib.get(0);
		
		String m= (String) a.get("MEMBER_CODE");
		String n= (String) a.get("ISBN");
		String o = (String) a.get("LENDING_SEQ");
		String p = (String) a.get("TITLE");
		String q= (String) a.get("PUBLISHER");
		String r= (String) a.get("AUTHOR");
		
		System.out.println(Ib);
		System.out.println(m);
		System.out.println(n);
		System.out.println(o);
		System.out.println(p);
		System.out.println(q);
		System.out.println(r);

		
		
	}
	
	// OK
	//@Test
	public void insertLendingBook() {
		logger.info("insertLendingBook _대출 신청");
		LendingVo vo = new LendingVo();
		vo.setBook_serial("BKSR100035");
		vo.setMember_code("M2205000050");
		
//		int n = dao.insertLendingBook(vo);
		System.out.println();
	}
	// OK
//	@Test
	public void insertLendingBookUpdate() {
		logger.info("insertLendingBookUpdate _대출 신청 상태변경");
		LendingVo vo = new LendingVo();
		vo.setBook_serial("BKSR100035");
//		int n = dao.insertLendingBookUpdate(vo);
		System.out.println();
	}
	// OK -
//	@Test
	public void deleyPenalty() {
		logger.info("deleyPenalty _도서연체패널티대상 조회");
		LibMemberVo vo = new LibMemberVo();
		vo.setMember_code("M2205000050");
		System.out.println(vo);
	}
	
	// 조인쿼리-다시 
//	@Test
//	public void lendingList() {
//		logger.info("lendingList  대출내역조회");
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("member_code", "M2205000004");
//		List<LendBookBean> lists = new ArrayList<LendBookBean>();
//		lists =dao.lendingList(map);
//		System.out.println(lists);
		
		
//		List<Map<String, String>> list = new  ArrayList<Map<String,String>>();
//		
//		list.add(map);
//		LendBookBean lb =dao.lendingList(list);
//		System.out.println(lb);
		
		
//		Map<String, String> map = new HashMap<String, String>();
//		map.add("member_code", "M2205000004");
//		LendBookBean lb =dao.lendingList(map);
//		System.out.println(lb);
		
//		String member_code = "M2205000004";
//		int n = dao.lendingList(member_code);
//		System.out.println(n);
		
		
//		LibMemberVo vo = new LibMemberVo();
//		vo.setMember_code("M2205000004");
//		System.out.println(vo);
//	}
	
	
	// OK
//	@Test
	public void reservationBook() {
		logger.info("reservationBook _대출예약 신청");
		LendingVo vo = new LendingVo();
		vo.setBook_serial("BKSR100030");
		vo.setMember_code("M2205000005");
		int n = dao.reservationBook(vo);
		
		System.out.println(n);
	}
	// OK
//	@Test
	public void reservationBookUpdate() {
		logger.info("reservationBookUpdate _대출예약 신청 후  상태변경");
		BookInfoVo vo = new BookInfoVo();
		vo.setBook_serial("BKSR165176");
		int n = dao.reservationBookUpdate(vo);
		System.out.println(n);
	}
	
	// OK
//	@Test 
	public void realReserBook() {
		logger.info("realReserBook _예약후  대출확정");
		String lending_seq = "LEN220531000000022";
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("lending_seq", "LEN220531000000022");
//		map.put("lending_delay", "Y");
//		map.put("return_status", "NM");
		
		int n = dao.realReserBook(lending_seq);
		System.out.println(n);
		
//		LendingVo vo = new LendingVo();
//		vo.setLending_seq("LEN220531000000022");
//		int n = dao.realReserBook(vo);
//		System.out.println(n);
	}
	
//	ok
//	@Test
	public void realReserBookUpdate() {
		logger.info("realReserBookUpdate _예약 후 대출 확정 상태변경");
		LendingVo vo = new LendingVo();
		vo.setBook_serial("BKSR100030");
	//	int n = dao.realReserBookUpdate(vo);
//		System.out.println(n);
	}
	
	//ok
//	@Test
	public void fastReturnDayBook() {
		logger.info("fastReturnDayBook  반납일이 빠른도서 조회");
		LendingVo vo = new LendingVo();
		vo.setBook_serial("BKSR101677");
		List<LendingVo> lists = dao.fastReturnDayBook(vo);
		System.out.println(lists);
	}
	//ok
//	@Test
	public void reserveAutoDel() {
		logger.info("reserveAutoDel  대출 예약 자동취소");
		String lending_seq = "LEN220602000000041";
		int n = dao.reserveAutoDel(lending_seq);
		System.out.println(n);
	}
//	ok
//	@Test
	public void reserveSelfDel() {
		logger.info("reserveSelfDel  대출 예약 취소");
		String lending_seq = "LEN220602000000042";
//		LendingVo vo = new LendingVo();
//		vo.setLending_seq("LEN220602000000031");
		int n = dao.reserveSelfDel(lending_seq);
		System.out.println(n);
	}
	//OK
//	@Test
//	public void reserveDelUpdate() {
//		logger.info("reserveDelUpdate  대출 예약 취소 상태변경");
//		BookInfoVo vo = new BookInfoVo();
//		vo.setBook_serial("BKSR100030");
//		int n = dao.reserveDelUpdate(vo);
//		System.out.println(n);
//	}
	// 5권 이상일땐??-> 증가 안되게.?
//	@Test
	public void lendingCount() {
		logger.info("lendingCount  대여가능 권수");
		String member_code = "M2205000050";
		int n = dao.lendingCount(member_code);
		System.out.println(n);
	}
	
	
//	@Test
	public void lendingCount1() {
		logger.info("test 중");
		  
		String lending_seq = "LEN220607000000002";
		String book_serial = "BKSR155997";
		String member_code = "M2205000788";
		int n = bService.confrimReserveBook(lending_seq, book_serial, member_code);
		System.out.println(n);
	}

}
