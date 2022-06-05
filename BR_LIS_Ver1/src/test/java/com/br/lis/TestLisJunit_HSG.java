package com.br.lis;

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

import com.br.lis.model.purchaseinfo.mapper.IPurchaseDao;
import com.br.lis.model.purchaseinfo.mapper.IPurchaseRegistrationDao;
import com.br.lis.model.purchaseinfo.mapper.IRequestPurchaseDao;
import com.br.lis.model.purchaseinfo.service.IPurchaseRegistrationService;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.Possessing_BookVo;
import com.br.lis.vo.PurchaseVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_HSG {
	
	@Autowired
	private IRequestPurchaseDao reqDao;

	@Autowired
	private IPurchaseDao purchDao;
	
	@Autowired
	private IPurchaseRegistrationService registService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	// 신청도서 조회(BR_W_BM_501) : 제목으로 조회
	// 신청도서 조회(BR_W_BM_501) : 출판사로 조회
	// 신청도서 신청(BR_W_BM_502) : 신청도서정보 삽입
	// 신청도서 신청(BR_W_BM_502) : 신청도서 월 1회만 신청하기 위한 쿼리(해당 구매 월에 MEMBER_ID가 있다면 신청하지 못함)
	// 신청도서 신청(BR_W_BM_502) : 한 권의 책을 중복 신청하는 것을 방지하기 위한 쿼리 (NULL이 아닐 경우 신청 불가)
	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자가 신청한 도서의 목록을 조회할 수 있다. 구매코드로 조회
	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자가 신청한 도서의 목록을 조회할 수 있다. 사용자 ID로 조회
	// 신청도서 상태조회(BR_W_BM_504) : 사용자는 자신이 신청한 도서의 구매여부(승인이 됐는지)를 조회할 수 있다.(로그인 된 사용자의 것만 조회)
	// 신청도서 알림관리(BR_W_BM_505) : 신청도서 중 구매승인이 된 도서에 한하여 신청한 사용자에게 알린다.
	// 신청도서 전체목록 조회(BR_W_BM_506) : 신청도서 중 구매 승인이 된 도서의 목록을 조회한다(리스트 전체조회)
	
	// 구매코드생성(BR_W_BM_601) : 해당 월의 구매 코드를 생성
	// 정기구매(BR_W_BM_602) : 구매정보 전체조회
	// 정기구매(BR_W_BM_602) : 구매 해당 월의 구매코드를 조회
	// 정기구매(BR_W_BM_602) : 정기구매 정보를 삽입
	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(권수 업데이트)
	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(구매여부(CONFIRM)업데이트)
	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(권수 업데이트)
	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(구매여부 업데이트)
	// 발주목록(BR_W_BM_606) : 신청구매
	// 발주목록(BR_W_BM_606) : 정기구매
	// 발주(BR_W_BM_607) : (관리자)구매할 책의 총 가격, 권수, 구매 업체의 이메일 업데이트
	// 발주(BR_W_BM_607) : (관리자)주문날짜, 확졍여부(발주O) 업데이트
	// 발주(BR_W_BM_607) : (관리자)확정일, 확졍여부(반입C) 업데이트
	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(신청도서)
	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(정기구매도서)
	
	// 구매도서등록(BR_W_BM_701) : 보유도서에 삽입
//	@Test
	public void insertPurchaseBook() {
		String isbn = "9791191942002";
		
		int n = registService.insertPurchaseBook(isbn);
		System.out.println("성공갯수 : " + n);
	}
	
	// 구매도서등록(BR_W_BM_701) : 보유도서의 책 갯수와 일치하도록 업데이트
//	@Test
	public void updateBookCount() {
		String isbn = "9791191942002";
		
		int n = registService.updateBookCount(isbn);
		
		System.out.println("성공갯수 : " + n);
	}
	// 신규도서조회(BR_W_BM_702) : (관리자) 등록된 도서 중 현재월로부터 등록일이 한 달 이내인 도서를 조회
//	@Test
	public void selectNewBookAdmin() {
		List<Possessing_BookVo> lists = registService.selectNewBookAdmin();
		
		System.out.println("selectNewBookAdmin의 목록" + lists);
	}
	// 신규도서조회(BR_W_BM_702) : (사용자) 선택된 ISBN으로 등록일이 한 달 이내인 도서정보를 조회
//	@Test
	public void selectNewBookUser() {
		List<BookInfoVo> lists = registService.selectNewBookUser();
		
		System.out.println("selectNewBookUser의 목록" + lists);
	}
	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(신청구매)
//	@Test
	public void selectReqHistory() {
		String purchCodeVo = "P2201";
		List<RequestPurchaseVo> lists = registService.selectReqHistory(purchCodeVo);
		
		System.out.println("selectReqHistory의 목록" + lists);
	}
	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(신청구매)
//	@Test
	public void updateReqHistory() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("history","이월 2022-06-06");
		map.put("wish_serial","PW22011007");
		
		int n = registService.updateReqHistory(map);
		System.out.println("성공 갯수 :" + n);
	}
	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(정기구매)
//	@Test
	public void selectRegulHistory() {
		String purchCodeVo = "P2202";
		List<RegularPurchaseVo> lists = registService.selectRegulHistory(purchCodeVo);
		
		System.out.println("selectRegulHistory의 목록" + lists);
	}
	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(정기구매)
//	@Test
	public void updateRegulHistory() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("history","이월 2022-06-06");
		map.put("regular_serial","PR22021010");
		
		int n = registService.updateRegulHistory(map);
		System.out.println("성공 갯수 :" + n);
	}
	
	

//
//	// 신청도서 조회(BR_W_BM_501)
////	@Test
//	public void purchSelectById() {
//		String memId = "gnldnd17";
//		List<RequestPurchaseVo> lists = reqDao.purchSelectById(memId);
//		System.out.println(lists);
//	}
//
//	// 신청도서 신청(BR_W_BM_502) : 사용자는 조회된 도서 목록에서 원하는 도서를 선택하여 신청을 할 수 있다.
////	@Test
//	public void purchRequestInsert() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("member_id", "gnldnd17");
//		map.put("isbn", "9791188240722");
//		map.put("title", "걱정 삼킨 학교");
//		map.put("publisher", "꿈터");
//		map.put("author", "김지연 글;장정오 그림");
//		map.put("translator", "김번역");
//		map.put("price", "15900");
//		
//		int n = reqDao.purchRequestInsert(map);
//		
//		System.out.println("성공한 갯수 : " + n);
//	}
//	// 신청도서 신청목록 조회(BR_W_BM_503) : 목록 전체조회
////	@Test
//	public void purchRequestListAll() {
//		List<RequestPurchaseVo> lists = reqDao.purchRequestListAll();
//		System.out.println("신청도서 전체 목록 : " + lists);
//	}
//	
//	// 신청도서 신청목록 조회(BR_W_BM_503) : 목록 전체조회
////	@Test
//	public void purchReqSelectByCode() {
//		String purchCode = "P2206";
//		List<RequestPurchaseVo> lists = reqDao.purchReqListSelectByCode(purchCode);
//		
//		System.out.println("신청도서 목록(구매코드) : " + lists);
//	}
//	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자 ID로 조회
////	@Test
//	public void purchReqListSelectById() {
//		String memId = "gnldnd17";
//		List<RequestPurchaseVo> lists = reqDao.purchReqListSelectById(memId);
//		
//		System.out.println("신청도서 목록(회원아이디) : " + lists);
//	}
//	
//	// 신청도서 상태조회(BR_W_BM_504) : 구매확정 된 도서 조회(로그인 된 사용자의 것만 조회)
////	@Test
//	public void purchReqConfirmYSelect() {
//		String memId = "gnldnd17";
//		List<RequestPurchaseVo> lists = reqDao.purchReqConfirmYSelect(memId);
//		
//		System.out.println("구매확정 도서 : " + lists);
//	}
//	// 신청도서 상태조회(BR_W_BM_504) : 구매가 반려된 도서 조회(로그인 된 사용자의 것만 조회)
////	@Test
//	public void purchReqConfirmNSelect() {
//		String memId = "gnldnd17";
//		List<RequestPurchaseVo> lists = reqDao.purchReqConfirmNSelect(memId);
//		
//		System.out.println("구매반려 도서 : " + lists);
//	}
//	
//	// 신청도서 알림관리(BR_W_BM_505) : 신청도서 중 구매승인이 된 도서에 한하여 신청한 사용자에게 알린다.
////	@Test
//	public void purchReqPhoneSelect() {
//		String memId = "gnldnd17";
//		LibMemberVo vo = reqDao.purchReqPhoneSelect(memId);
//		
//		System.out.println("사용자의 휴대폰 번호 : " + vo);
//	}
//
//	// 신청도서 전체목록 조회(BR_W_BM_506) : 신청도서 중 구매 승인이 된 도서의 목록을 조회한다(리스트 전체조회)
////	@Test
//	public void purchReqConfirmYList() {
//		List<RequestPurchaseVo> lists = reqDao.purchReqConfirmYList();
//		
//		System.out.println("신청도서 전체목록 조회 : " + lists);
//	}
////-----------------------------------------------------------------------------------------------------------------	
//	// 구매코드생성(BR_W_BM_601) : 해당 월의 구매 코드를 생성
////	@Test
//	public void purchCodeInsert() {
//		int n = purchDao.purchCodeInsert();
//		
//		System.out.println("생성한 구매코드 갯수 : " + n);
//	}
//	// 정기구매(BR_W_BM_602) : 구매 해당 월의 구매코드를 조회
////	@Test
//	public void purchCodeSelect() {
//		String purchCode = "06";
//		PurchaseVo vo = purchDao.purchCodeSelect(purchCode);
//		
//		System.out.println("정기구매 코드 : " + vo);
//	}
//	// 정기구매(BR_W_BM_602) : 정기구매 정보를 삽입
////	@Test
//	public void regulPurchInsert() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("purchase_code", "06");
//		map.put("isbn", "9788952786470");
//		map.put("title", "91층 나무 집");
//		map.put("publisher", "시공사");
//		map.put("author", "앤디 그리피스 글 ;신수진 옮김");
//		map.put("translator", "앤디 그리피스 글 ;신수진 옮김");
//		map.put("price", "12000");
//		map.put("ea", "1");
//		map.put("confirm", "Y");
//		map.put("history", "구매예정");
//		
//		int n = purchDao.regulPurchInsert(map);
//		System.out.println("성공한 갯수 : " + n);
//	}
//	// 신청구매(BR_W_BM_603) : 구매코드로 신청구매정보 삽입
////	@Test
//	public void reqPurchInsert() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("member_id", "gnldnd17");
//		map.put("purchase_code", "06");
//		map.put("isbn", "9788952786470");
//		map.put("title", "91층 나무 집");
//		map.put("publisher", "시공사");
//		map.put("author", "앤디 그리피스 글 ;신수진 옮김");
//		map.put("translator", "앤디 그리피스 글 ;신수진 옮김");
//		map.put("price", "12000");
//		map.put("ea", "1");
//		map.put("confirm", "Y");
//		map.put("history", "구매예정");
//		
//		int n = purchDao.reqPurchInsert(map);
//		System.out.println("성공한 갯수 : " + n);
//	}
//	// 정기구매목록조회(BR_W_BM_604) : 보유도서에 없는 도서의 정보 조회
////	@Test
//	public void bookListDontHave() {
//		List<BookInfoVo> lists = purchDao.bookListDontHave();
//		
//		System.out.println("보유도서에 없는 정보" + lists);
//	}
//	// 정기구매목록조회(BR_W_BM_604) : 보유도서에 없는 도서의 정보 단일조회
////	@Test
//	public void bookOneDontHave() {
//		String isbn = "9791191942002";
//		
//		BookInfoVo vo = purchDao.bookOneDontHave(isbn);
//		System.out.println("보유도서에 없는 도서 정보" + vo);
//		
//	}
//	// 정기구매목록조회(BR_W_BM_605) : 보유도서 목록에 있는 도서의 정보를 조회
////	@Test
//	public void bookListHave() {
//		List<BookInfoVo> lists = purchDao.bookListHave();
//		
//		System.out.println("보요도서에 잇는 도서정보 : " + lists);
//	}
//	// 정기구매목록조회(BR_W_BM_605) : 보유도서 목록에 있는 도서의 정보 단일조회
////	@Test
//	public void bookOneHave() {
//		String isbnVo = "9791170288695";
//		BookInfoVo vo = purchDao.bookOneHave(isbnVo);
//		
//		System.out.println("보유도서에 있는 도서정보 단일조회" + vo);
//	}
//	// 발주목록(BR_W_BM_606) : 신청구매
////	@Test
//	public void orderReqBookList() {
//		String purchVo = "P2206";
//		List<RequestPurchaseVo> lists = purchDao.orderReqBookList(purchVo);
//		
//		System.out.println("신청구매 목록 : " + lists);
//	}
//	// 발주목록(BR_W_BM_606) : 정기구매
////	@Test
//	public void orderRegulBookList() {
//		String purchVo = "P2206";
//		List<RegularPurchaseVo> lists = purchDao.orderRegulBookList(purchVo);
//		
//		System.out.println("정기구매 목록 : " + lists);
//	}
//	// 발주(BR_W_BM_607) : 구매테이블 정보 업데이트
////	@Test
//	public void updatePurchInfo() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("total_price", "100000");
//		map.put("total_ea", "50");
//		map.put("order_date", "2022-06-01");
//		map.put("distributor_email", "GD1234@naver.com");
//		map.put("confirm", "Q");
//		map.put("confirm_date", "2022-06-01");
//		map.put("purchase_code", "P2206");
//		int n = purchDao.updatePurchInfo(map);
//		
//		System.out.println("업데이트 성곡 갯수 : " + n);
//	}
////-----------------------------------------------------------------------------------------------------------------
//	// 구매도서등록(BR_W_BM_701) : 정기구매도서 구매완료된 도서 조회
////	@Test
//	public void regulCompliteBookList() {
//		String purchCodeVo = "P2201";
//		List<RegularPurchaseVo> lists = registDao.regulCompliteBookList(purchCodeVo);
//		
//		System.out.println("구매완료된 도서" + lists);
//	}
//	// 구매도서등록(BR_W_BM_701) : 신청구매도서 구매완료된 도서 조회
////	@Test
//	public void reqCompliteBookList() {
//		String purchCodeVo = "P2201";
//		List<RequestPurchaseVo> lists = registDao.reqCompliteBookList(purchCodeVo);
//		
//		System.out.println("구매완료된 도서" + lists);
//	}
//	// 신규도서조회(BR_W_BM_702) : 신규도서조회 등독된지 30일 이내 도서의 BOOKINFO조회
////	@Test
//	public void selectNewBookInfo() {
//		List<BookInfoVo> lists = registDao.selectNewBookInfo();
//		
//		System.out.println("신규도서 : " + lists);
//	}
//	
//	
//	// 등록예정목록조회(BR_W_BM_704) : 새로입고된 도서 목록들 조회(신청도서)
////	@Test
//	public void selectNewReqBookList() {
//		String purchCodeVo = "P2201";
//		List<RequestPurchaseVo> lists = registDao.selectNewReqBookList(purchCodeVo);
//		
//		System.out.println("신청도서 신간 : " + lists);
//	}
//	// 등록예정목록조회(BR_W_BM_704) : 새로입고된 도서 목록들 조회(정기도서)
////	@Test
//	public void selectNewRegulBookList() {
//		String purchCodeVo = "P2201";
//		List<RegularPurchaseVo> lists = registDao.selectNewRegulBookList(purchCodeVo);
//		
//		System.out.println("정기도서 신간 : " + lists);
//	}
//	// 등록수정(BR_W_BM_705) : 입고도서 상태를 대기로 변경
////	@Test
//	public void updatePurchConfirm() {
//		String purchCodeVo = "P2201";
//		int n = registDao.updatePurchConfirm(purchCodeVo);
//		
//		System.out.println("상태변경 성공 갯수 : " + n);
//		
//	}
//	// 등록취소(BR_W_BM_707) : (정기) 입고도서 상태를 N으로 변경, 히스토리 업데이트
////	@Test
//	public void updateRegulHistory() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("history", "2022-06-02 재고없음 반려");
//		map.put("regular_serial", "PR22011036");
//		
//		int n = registDao.updateRegulHistory(map);
//		System.out.println("상태변경 성공 갯수 : " + n);
//	}
//	// 등록취소(BR_W_BM_707) : (신청) 입고도서 상태를 N으로 변경, 히스토리 업데이트
////	@Test
//	public void updateReqHistory() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("history", "2022-06-02 재고없음 반려");
//		map.put("wish_serial", "PW22051021");
//		
//		int n = registDao.updateReqHistory(map);
//		System.out.println("상태변경 성공 갯수 : " + n);
//	}
	
	
}

