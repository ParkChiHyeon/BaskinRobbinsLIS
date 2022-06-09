package com.br.lis.model.lendinginfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.lis.model.lendinginfo.mapper.ILendingBookDao;
import com.br.lis.model.lendinginfo.mapper.IReturnBookDao;
import com.br.lis.model.member.mapper.ILibMemberDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class ReturnBookServiceImpl implements IReturnBookService {

	@Autowired
	private IReturnBookDao dao;

	@Autowired
	private ILibMemberDao lDao;

	@Autowired
	private ILendingBookDao bDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 반납시 해당도서의 대출건 조회
	@Override
	public LendingVo lendingDetailForReturnBook(String book_serial) {
		logger.info("ReturnBookServiceImpl 반납 도서의 대출내역 {} ", book_serial);
		return dao.lendingDetailForReturnBook(book_serial);
	}

	// 반납시 해당도서의 반납자 정보 조회
	@Override
	public LibMemberVo lendingDetailForReturnUser(String member_code) {
		logger.info("ReturnBookServiceImpl 반납 도서의 반납자 정보 {} ", member_code);
		return dao.lendingDetailForReturnUser(member_code);
	}

	// 반납시 해당 도서의 예약내역 조회
	@Override
	public ReservationVo returnBookReserveCheck(Map<String, Object> map) {
		logger.info("ReturnBookServiceImpl 반납 도서의 예약내역 {} ", map);
		return dao.returnBookReserveCheck(map);
	}

	// 예약 없는 정상 반납의 경우 {LD:N, RV:N, DL:N, BA:Y, OD:N, DG:Y, NM:Y}
	@Override
	public int normalReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 정상반납 {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.returnLendingBookCount(member_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lending_seq", lending_seq);
		map.put("member_code", member_code);
		int p = lDao.lendingPenalty(map);
		return (n > 0 && m > 0 && o > 0 && p > 0) ? 1 : 0;
	}

	// 예약 있는 정상 반납의 경우 {LD:N, RV:Y, DL:N, BA:Y, OD:N, DG:N, NM:Y}
	@Override
	public int existReserveReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 정상반납(예약있음) {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.returnLendingBookCount(member_code);
		int q = dao.existReserveReturnBookStatus(lending_seq);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lending_seq", lending_seq);
		map.put("member_code", member_code);
		int p = lDao.lendingPenalty(map);
		return (n > 0 && m > 0 && o > 0 && q > 0 && p > 0) ? 1 : 0;

	}

	// 파손 반납의 경우 {LD:N,RV:N, DL:N, BA:Y,OD:N, DG:N, NM:N}, retrun_status=DM => 예약 여부
	// 먼저 판단
	@Override
	public int damegeReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 파손반납 {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.damegeReturnBook(lending_seq);
		int p = dao.dgAndLsReturnBookStatus(lending_seq);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lending_seq", lending_seq);
		map.put("member_code", member_code);
		int r = lDao.lendingPenalty(map);
		return (n > 0 && m > 0 && o > 0 && p > 0 && r > 0) ? 1 : 0;
	}

	// 분실 반납의 경우 {LD:N,RV:N, DL:N, BA:Y,OD:N, DG:N, NM:N}, retrun_status=LS => 예약 여부
	// 먼저 판단
	@Override
	public int lossReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 분실반납 {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.lossReturnBook(lending_seq);
		int p = dao.dgAndLsReturnBookStatus(lending_seq);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lending_seq", lending_seq);
		map.put("member_code", member_code);
		int r = lDao.lendingPenalty(map);
		return (n > 0 && m > 0 && o > 0 && p > 0 && r > 0) ? 1 : 0;
	}

	// 연체 처리(상태변경+관내회원권한변경)
	@Override
	@Scheduled(cron = "0 0 09 * * *?")
	public int overdueLendingBook() {
		logger.info("ReturnBookServiceImpl 연체 처리");
		System.out.println("연체처리 상태변경, 관내회원권한변경");
		int n = dao.overdueLendingBook();
		int m = dao.penaltyAuthModify();
		return (n > 0 && m > 0) ? 1 : 0;
	}

	// 연체 3일째 조회
	@Override
	@Scheduled(cron = "0 10 16 * * *?")
	public void overdueThreeDayLendingBook() {
		logger.info("ReturnBookServiceImpl 연체 3일째 조회 및 문자 발송");
		List<LibMemberVo> lists = dao.overdueThreeDayLendingBook();
		// 문자 전송 가능 TEST 완료
//		String api_key = "NCSXHAZLCERE8DKQ";
//		String api_secret = "GIIQ8SVILBOG01WFEMRVCCQMFD1LSHYX";
//		Message coolsms = new Message(api_key, api_secret);
//		HashMap<String, String> params = new HashMap<String, String>();
//		for (int i = 0; i < lists.size(); i++) {
//			String phone = lists.get(i).getPhone();
//			String name = lists.get(i).getName();
//			System.out.println(phone + " " + name);
//			params.put("to", phone);
//			params.put("from", "01050329744");
//			params.put("type", "SMS");
//			params.put("text", name + "님 3일 연체된 도서가 있습니다 빠른 반납바랍니다.");
//			params.put("app_version", "test app 1.2");
//			try {
//				JSONObject obj = (JSONObject) coolsms.send(params);
//				System.out.println(obj.toString());
//			} catch (CoolsmsException e) {
//				System.out.println(e.getMessage());
//				System.out.println(e.getCode());
//			}
//		}
	}

	// 대출 연장
	@Override
	public int delayLendingBook(String lending_seq) {
		logger.info("ReturnBookServiceImpl 대출 연장, {}", lending_seq);
		return dao.delayLendingBook(lending_seq);
	}

	// 서고 자료 조회
	@Override
	public List<BookInfoVo> warehouseList() {
		logger.info("ReturnBookServiceImpl 서고 자료 조회");
		return dao.warehouseList();
	}

	// 부록 여부 조회
	@Override
	public List<BookInfoVo> supplementList() {
		logger.info("ReturnBookServiceImpl 부록 여부 조회");
		return dao.supplementList();
	}

	// 파손.분실 반납시 예약자에게 문자송신
	@Override
	public void certifiedPhoneNumber(String phone, String name) {
		logger.info("ReturnBookServiceImpl 예약 취소 문자송신");
		String api_key = "NCSXHAZLCERE8DKQ";
		String api_secret = "GIIQ8SVILBOG01WFEMRVCCQMFD1LSHYX";
		Message coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phone);
		params.put("from", "01050329744");
		params.put("type", "SMS");
		params.put("text", name + "님이 예약하신 도서가 파손 및 분실되어 예약이 취소되었습니다.");
		params.put("app_version", "test app 1.2");
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

	// 예약 취소
	@Override
	public int reserveSelfDel(String lending_seq) {
		logger.info("ReturnBookServiceImpl 예약 취소");
		return bDao.reserveSelfDel(lending_seq);
	}
	
	//DATATABLE 반납 완료 목록
	@Override
	public List<LendBookBean> returnBookList() {
		logger.info("ReturnBookServiceImpl 반납 완료 목록");
		return dao.returnBookList();
	}
}
