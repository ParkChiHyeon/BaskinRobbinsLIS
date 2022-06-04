package com.br.lis.model.lendinginfo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.lendinginfo.mapper.IReturnBookDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.ReservationVo;

@Service
public class ReturnBookServiceImpl implements IReturnBookService {

	@Autowired
	private IReturnBookDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		return (n>0&&m>0&&o>0)? 1:0;
	}
	
	// 예약 있는 정상 반납의 경우 {LD:N, RV:N, DL:N, BA:Y, OD:N, DG:N, NM:Y}
	@Override
	public int existReserveReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 정상반납(예약있음) {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.returnLendingBookCount(member_code);
		int q = dao.existReserveReturnBookStatus(lending_seq);
		return (n>0&&m>0&&o>0&&q>0)? 1:0;
		
	}
	
	// 파손 반납의 경우 {LD:N,RV:N, DL:N, BA:Y,OD:N, DG:N, NM:N}, retrun_status=DM => 예약 여부 먼저 판단
	@Override
	public int damegeReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 파손반납 {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.damegeReturnBook(lending_seq);
		int p = dao.dgAndLsReturnBookStatus(lending_seq);
		int q = dao.returnLendingBookCount(member_code);
		return (n>0&&m>0&&o>0&&p>0&&q>0)? 1:0;
		}

	// 분실 반납의 경우 {LD:N,RV:N, DL:N, BA:Y,OD:N, DG:N, NM:N}, retrun_status=LS => 예약 여부 먼저 판단
	@Override
	public int lossReturnBook(String lending_seq, String member_code) {
		logger.info("ReturnBookServiceImpl 분실반납 {},{}", lending_seq, member_code);
		int n = dao.allReturnBook(lending_seq);
		int m = dao.returnBookDate(lending_seq);
		int o = dao.lossReturnBook(lending_seq);
		int p = dao.dgAndLsReturnBookStatus(lending_seq);
		int q = dao.returnLendingBookCount(member_code);
		return (n>0&&m>0&&o>0&&p>0&&q>0)? 1:0;
	}
	
	// 연체 처리
	@Override
	public int overdueLendingBook() {
		logger.info("ReturnBookServiceImpl 연체 처리");
		return dao.overdueLendingBook();
	}

	// 연체 3일째 조회
	@Override
	public List<LendingVo> overdueThreeDayLendingBook() {
		logger.info("ReturnBookServiceImpl 연체 3일째 조회");
		return dao.overdueThreeDayLendingBook();
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

	
	
	
	
	
	
}
