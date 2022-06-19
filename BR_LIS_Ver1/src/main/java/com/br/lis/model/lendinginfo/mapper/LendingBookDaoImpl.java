package com.br.lis.model.lendinginfo.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

@Repository
public class LendingBookDaoImpl implements ILendingBookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.lendinginfo.mapper.LendingBookDaoImpl.";

	@Override
	public List<LendBookBean> nowLendingBook(String book_serial) {
		return sqlSession.selectList(NS+"nowLendingBook",book_serial);
	}

	@Override
//	public List<BookInfoVo> allReserveLending() {
	public List<LendBookBean> allReserveLending(Map<String, Object>map){
		return sqlSession.selectList(NS+"allReserveLending");
	}

	@Override
	public List<Map<String, Object>> reserveLendingBook(String member_code) {
		return sqlSession.selectList(NS+"reserveLendingBook", member_code);
	}

	@Override
	public int insertLendingBook(LendingVo vo) {
		return sqlSession.insert(NS+"insertLendingBook",vo);
	}

	@Override
	public int insertLendingBookUpdate(String book_serial) {
		return sqlSession.update(NS+"insertLendingBookUpdate",book_serial);
	}

	@Override
	public List<LibMemberVo> deleyPenalty() {
		return sqlSession.selectList(NS+"deleyPenalty");
	}
	
	
//	BR_W_BM_207 대출 내역 조회 조인 쿼리
	
	@Override
//	public LendBookBean lendingList(List<Map<String, String>>  map) {
	public List<LendBookBean> lendingList(String member_code) {
		return sqlSession.selectList(NS+"lendingList",member_code);
	}

	//추가 : 대출중면서 예약이 안된책의 빠른대출일 조회
	@Override
	public List<LendBookBean> possibleReserve(String isbn) {
		return sqlSession.selectList(NS+"possibleReserve",isbn);
	}
	@Override
	public List<LendBookBean> selectPossibleReserve() {
		return sqlSession.selectList(NS+"selectPossibleReserve");
	}
	
	@Override
	public int reservationBook(LendingVo vo) {
		return sqlSession.insert(NS+"reservationBook",vo);
	}

	@Override
	public int reservationBookUpdate(String book_serial) {
		return sqlSession.update(NS+"reservationBookUpdate",book_serial);
	}

	@Override
	public int realReserBook(String lending_seq) {
		return sqlSession.update(NS+"realReserBook", lending_seq);
	}

	@Override
	public int realReserBookUpdate(String book_serial) {
		return sqlSession.update(NS+"realReserBookUpdate",book_serial);
	}

	@Override
	public int reserveAutoDel(String lending_seq) {
		return sqlSession.delete(NS+"reserveAutoDel",lending_seq);
	}

	@Override
	public int reserveSelfDel(String lending_seq) {
		return sqlSession.delete(NS+"reserveSelfDel",lending_seq);
	}

	@Override
	public int reserveDelUpdate(String book_serial) {
		return sqlSession.update(NS+"reserveDelUpdate",book_serial);
	}

	@Override
	public int lendingCount(String member_code) {
		return sqlSession.update(NS+"lendingCount",member_code);
	}

	@Override
	public List<LendingVo> limitBookCount(String member_id) {
		return sqlSession.selectList(NS+"limitBookCount",member_id);
	}
	
	@Override
	public LibMemberVo rentalBookCount(String member_code) {
		return sqlSession.selectOne(NS+"rentalBookCount",member_code);
	}
	
	
	
	

}
