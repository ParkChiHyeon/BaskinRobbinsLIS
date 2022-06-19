package com.br.lis.model.lendinginfo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.lis.model.lendinginfo.mapper.ILendingBookDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

@Service
public class LendingBookServiceImpl implements ILendingBookService {

	@Autowired
	private ILendingBookDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<LendBookBean> nowLendingBook(String book_serial) {
		logger.info("LendingBookServiceImpl 즉시대출도서목록_nowLendingBook");
		return dao.nowLendingBook(book_serial);
	}

	@Override
//	public List<BookInfoVo> allReserveLending() {
	public List<LendBookBean> allReserveLending(Map<String, Object>map){
		logger.info("LendingBookServiceImpl 예약한 목록 전체 조회_allReserveLending");
		return dao.allReserveLending(map);
	}

	@Override
	public List<Map<String, Object>> reserveLendingBook(String member_code) {
		logger.info("LendingBookServiceImpl 회원 예약 목록 조회_reserveLendingBook");
		return dao.reserveLendingBook(member_code);
	}
	
	// BR_W_BM_204 대출 신청 Transction처리  : 대출 신청 후 대출가능여부변경 LD =Y , DG =N  + 대여가능권수변경
	@Override
	@Transactional
	public int lendingBook(String book_serial,String member_code) {
		logger.info("LendingBookServiceImpl  대출 신청 후 보유도서 상태변경 lendingBook",book_serial,member_code);
		int x = dao.lendingCount(member_code);
		LendingVo vo = new LendingVo();
		vo.setBook_serial(book_serial);
		vo.setMember_code(member_code);
		int n = dao.insertLendingBook(vo);
		int m = dao.insertLendingBookUpdate(book_serial);
		return (x>0&&n>0&&m>0)?1:0;
	}

	
	@Override
	public List<LibMemberVo> deleyPenalty() {
		logger.info("LendingBookServiceImpl 도서연체패널티대상 조회_deleyPenalty");
		return dao.deleyPenalty();
	}

	@Override
	public List<LendBookBean> lendingList(String member_code) {
		logger.info("LendingBookServiceImpl 대출내역조회_lendingList");
		return dao.lendingList(member_code);
	}

	//추가 : 대출중면서 예약이 안된책의 빠른대출일 조회
	@Override
	public List<LendBookBean> possibleReserve(String isbn){
		logger.info("LendingBookServiceImpl 대출중이면서 예약안되있고 빠른대출일 조회 possibleReserve");
		return dao.possibleReserve(isbn);
	}
	@Override
	public List<LendBookBean> selectPossibleReserve() {
		logger.info("LendingBookServiceImpl 예약가능 도서 selectPossibleReserve");
		return dao.selectPossibleReserve();
	}
	
	//BR_W_BM_208 대출 예약 신청 RV=Y , DG=N 
	// + 예약가능권수 초과하면 추가예약 불가
	@Override
	@Transactional
	public int bookReservation(LendingVo vo, String book_serial) {
		logger.info("LendingBookServiceImpl 대출예약 신청 후 보유도서 상태변경 bookReservation",vo,book_serial);
		
		int n = dao.reservationBook(vo);
		int m = dao.reservationBookUpdate(book_serial);
		return (n>0&&m>0)?1:0;
	}
	//BR_W_BM_209 예약건 대출 확정 후 상태변경
	// ++ 반납일이 빠른도서로 예약
	@Override
	@Transactional
	public int confrimReserveBook(String lending_seq, String book_serial,String member_code) {
		logger.info("LendingBookServiceImpl 예약건 대출확정 후 상태변경 confrimReserveBook");
		int n = dao.realReserBook(lending_seq);
		int m =dao.realReserBookUpdate(book_serial);
		int x = dao.lendingCount(member_code);
		return (n>0&&m>0&&x>0)?1:0;
	}
	
	@Override
	public List<LendingVo> fastReturnDayBook(LendingVo vo) {
		logger.info("LendingBookServiceImpl  반납일이 빠른도서 조회_fastReturnDayBook");
		return dao.fastReturnDayBook(vo);
	}

	
	//BR_W_BM_211 대출예약 자동취소 후 상태변경 RV  =N ,DG=Y
	@Override
	@Transactional
	public int autoDeleteResrve(String lending_seq, String book_serial) {
		logger.info("LendingBookServiceImpl  대출 예약 자동취소 후 보유도서 상태변경 autoDeleteResrve");
		int m = dao.reserveDelUpdate(book_serial);
		int n =dao.reserveAutoDel(lending_seq);
		return (n>0&&m>0)?1:0;
	}
	
	
	//BR_W_BM_212 대출예약 취소 후 상태변경 RV  =N ,DG=Y
	@Override
	@Transactional
	public int selfDeleteResrve(String lending_seq, String book_serial) {
		logger.info("LendingBookServiceImpl 대출 예약 취소 후 보유도서 상태변경 selfDeleteResrve");
		int n = dao.reserveSelfDel(lending_seq);
		int m = dao.reserveDelUpdate(book_serial);
		return (n>0&&m>0)?1:0;
	}
	
	@Override
	public int lendingCount(String member_code) {
		logger.info(" LendingBookServiceImpl 대여가능 권수_lendingCount");
		return dao.lendingCount(member_code);
	}
	
	@Override
	public List<LendingVo> limitBookCount(String member_id) {
		logger.info("LendingBookServiceImpl 예약권수 제한limitBookCount");
		return dao.limitBookCount(member_id);
	}
	
	@Override
	public LibMemberVo rentalBookCount(String member_code) {
		logger.info(" LendingBookServiceImpl 대여가능 권수 조회 rentalBookCount");
		return dao.rentalBookCount(member_code);
	}
}
