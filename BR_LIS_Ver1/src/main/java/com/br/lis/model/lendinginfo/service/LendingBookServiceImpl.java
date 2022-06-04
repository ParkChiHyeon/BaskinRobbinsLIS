package com.br.lis.model.lendinginfo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.lis.model.lendinginfo.mapper.ILendingBookDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

public class LendingBookServiceImpl implements ILendingBookService {

	@Autowired
	private ILendingBookDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<LendingVo> nowLendingBook() {
		logger.info("ILendingBookDao 즉시대출도서목록_nowLendingBook");
		return dao.nowLendingBook();
	}

	@Override
	public List<LendingVo> allReserveLending() {
		logger.info("ILendingBookDao 예약한 목록 전체 조회_allReserveLending");
		return dao.allReserveLending();
	}

	@Override
	public LendBookBean reserveLendingBook(Map<String, String> map) {
		logger.info("ILendingBookDao 회원 예약 목록 조회_reserveLendingBook");
		return dao.reserveLendingBook(map);
	}
	
	// BR_W_BM_204 대출 신청  : 대출 신청 후 대출가능여부변경 
	@Override
	public int insertLendingBook(LendingVo vo) {
		logger.info("ILendingBookDao 대출 신청_insertLendingBook");
		return 0;
	}
	@Override
	public int insertLendingBookUpdate(LendingVo vo) {
		logger.info("ILendingBookDao 대출 신청 상태변경_insertLendingBookUpdate");
		return 0;
	}

	
	
	@Override
	public List<LibMemberVo> deleyPenalty() {
		logger.info("ILendingBookDao 도서연체패널티대상 조회_deleyPenalty");
		return dao.deleyPenalty();
	}

	@Override
	public List<LendBookBean> lendingList(Map<String, Object> map) {
		logger.info("ILendingBookDao 대출내역조회_lendingList");
		return dao.lendingList(map);
	}

	//BR_W_BM_208 대출 예약 신청 
	@Override
	public int reservationBook(LendingVo vo) {
		logger.info("ILendingBookDao 대출예약 신청_reservationBook");
		return 0;
	}
	@Override
	public int reservationBookUpdate(BookInfoVo vo) {
		logger.info("ILendingBookDao 대출예약 신청 후  상태변경_reservationBookUpdate");
		return 0;
	}

	//BR_W_BM_209 예약 후 대출 확정
	@Override
	public int realReserBook(String lending_seq) {
		logger.info("ILendingBookDao 예약후  대출확정_realReserBook");
		return 0;
	}
	@Override
	public int realReserBookUpdate(LendingVo vo) {
		logger.info("ILendingBookDao 예약 후 대출 확정 상태변경_realReserBookUpdate");
		return 0;
	}

	
	@Override
	public List<LendingVo> fastReturnDayBook(LendingVo vo) {
		logger.info("ILendingBookDao 반납일이 빠른도서 조회_fastReturnDayBook");
		return dao.fastReturnDayBook(vo);
	}

	//BR_W_BM_211 대출예약 자동취소 / possisng상태변경 : 트렌젝션 처리
	@Override
	public int reserveAutoDel(String lending_seq) {
		logger.info("ILendingBookDao 대출 예약 자동취소_reserveAutoDel");
		return 0;
	}
	//BR_W_BM_212대출예약 취소 / possisng상태변경 : 트렌젝션 처리
	@Override
	public int reserveDelUpdate(BookInfoVo vo) {
		logger.info(" 대출 예약 취소 상태변경_reserveDelUpdate");
		return 0;
	}
	
	//상태변경
	@Override
	public int reserveSelfDel(String lending_seq) {
		logger.info("ILendingBookDao 대출 예약 취소_reserveSelfDel");
		return 0;
	}


	@Override
	public int lendingCount(String member_code) {
		logger.info(" 대여가능 권수_lendingCount");
		return dao.lendingCount(member_code);
	}
	
	
}
