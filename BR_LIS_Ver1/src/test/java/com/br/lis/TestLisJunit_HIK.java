package com.br.lis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.lis.model.lendinginfo.mapper.IReturnBookDao;
import com.br.lis.model.lendinginfo.service.IReturnBookService;
import com.br.lis.model.test.service.ITestService;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_HIK {

	@Autowired
	private IReturnBookDao dao;
	
	@Autowired
	private IReturnBookService service;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Test 
	public void allReturnBook() {
		logger.info("단위테스트 allReturnBook");
		String lending_seq = "LEN181031000000191";
		int n = dao.allReturnBook(lending_seq);
		System.out.println(n);
	}
	
//	@Test 
	public void returnBookDate() {
		logger.info("단위테스트 returnBookDate");
		String lending_seq = "LEN181031000000191";
		int n = dao.returnBookDate(lending_seq);
		System.out.println(n);
	}
	
//	@Test 
	public void damegeReturnBook() {
		logger.info("단위테스트 damegeReturnBook");
		String lending_seq = "LEN181031000000191";
		int n = dao.damegeReturnBook(lending_seq);
		System.out.println(n);
	}
	
//	@Test 
	public void lossReturnBook() {
		logger.info("단위테스트 lossReturnBook");
		String lending_seq = "LEN181031000000191";
		int n = dao.lossReturnBook(lending_seq);
		System.out.println(n);
	}
	
//	@Test 
	public void dgAndLsReturnBookStatus() {
		logger.info("단위테스트 dgAndLsReturnBookStatus");
		String lending_seq = "LEN181031000000191";
		int n = dao.dgAndLsReturnBookStatus(lending_seq);
		System.out.println(n);
	}

//	@Test 
	public void existReserveReturnBookStatus() {
		logger.info("단위테스트 existReserveReturnBookStatus");
		String lending_seq = "LEN181031000000191";
		int n = dao.existReserveReturnBookStatus(lending_seq);
		System.out.println(n);
	}
	
//	@Test 
	public void returnLendingBookCount() {
		logger.info("단위테스트 returnLendingBookCount");
		String member_code = "M2205000109";
		int n = dao.returnLendingBookCount(member_code);
		System.out.println(n);
	}
	
//	@Test 
	public void overdueLendingBook() {
		logger.info("단위테스트 overdueLendingBook");
		int n = dao.overdueLendingBook();
		System.out.println(n);
	}
	
//	@Test 
	public void overdueThreeDayLendingBook() {
		logger.info("단위테스트 overdueThreeDayLendingBook");
		List<LibMemberVo> lists = dao.overdueThreeDayLendingBook();
		System.out.println(lists);
	}
	
//	@Test 
	public void delayLendingBook() {
		logger.info("단위테스트 delayLendingBook");
		String lending_seq = "LEN220514000000012";
		int n = dao.delayLendingBook(lending_seq);
		System.out.println(n);
	}
	
	
//	@Test 
	public void warehouseList() {
		logger.info("단위테스트 warehouseList");
		List<BookInfoVo> lists = dao.warehouseList();
		System.out.println(lists);
	}
	
//	@Test 
	public void supplementList() {
		logger.info("단위테스트 supplementList");
		List<BookInfoVo> lists = dao.supplementList();
		System.out.println(lists);
	}
	
//	@Test 
	public void damegeService() {
		logger.info("단위 테스트 damegeService");
		String lending_seq = "LEN181031000000191";
		String member_code="M2205000109";
		int n = service.damegeReturnBook(lending_seq, member_code);
		System.out.println(n);
	}
	
//	@Test 
	public void normalBook() {
		logger.info("단위 테스트 normalBook");
		String lending_seq = "LEN181031000000191";
		String member_code="M2205000109";
		int n = service.normalReturnBook(lending_seq, member_code);
		System.out.println(n);
	}
	
//	@Test
	public void existService() {
		logger.info("단위 테스트 existService");
		String lending_seq = "LEN181031000000191";
		String member_code="M2205000109";
		int n = service.existReserveReturnBook(lending_seq, member_code);
		System.out.println(n);
		
	}
	
//	@Test 
	public void lossReturnBookService() {
		logger.info("단위 테스트 lossReturnBookService");
		String lending_seq = "LEN181031000000191";
		String member_code="M2205000109";
		int n = service.lossReturnBook(lending_seq, member_code);
		System.out.println(n);
	}
	
//	@Test
	public void overdueService() {
		int n = service.overdueLendingBook();
		System.out.println(n);
	}
	
//	@Test
//	public void overdueThreeService() {
//		List<LibMemberVo> lists = service.overdueThreeDayLendingBook();
//		System.out.println(lists);
//	}
	
//	@Test
	public void warehouseService() {
		List<BookInfoVo> lists = service.warehouseList();
		System.out.println(lists);
	}
	
//	@Test
	public void suppleService() {
		List<BookInfoVo> lists = service.supplementList();
		System.out.println(lists);
	}
	
//	@Test
	public void returnBookReserveCheck() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("book_serial", "BKSR214093");
		ReservationVo vo = dao.returnBookReserveCheck(map);
		System.out.println(vo);
	}
	
//	@Test
	public void lendingDetailForReturnBook() {
		String book_serial ="BKSR155997";
		LendingVo vo = dao.lendingDetailForReturnBook(book_serial);
		System.out.println(vo);
	}
	
//	@Test
	public void lendingDetailForReturnUser() {
		String member_code = "M2205000793";
		LibMemberVo vo = dao.lendingDetailForReturnUser(member_code);
		System.out.println(vo);
	}
	
//	@Test
	public void returnBookList() {
		List<LendBookBean> lists = dao.returnBookList();
		System.out.println(lists);
	}
}
