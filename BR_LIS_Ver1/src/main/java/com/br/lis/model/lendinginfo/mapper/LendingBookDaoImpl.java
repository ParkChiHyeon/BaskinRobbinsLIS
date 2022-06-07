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
	public List<LendingVo> nowLendingBook() {
		
		return sqlSession.selectList(NS+"nowLendingBook");
	}

	@Override
	public List<BookInfoVo> allReserveLending() {
		return sqlSession.selectList(NS+"allReserveLending");
	}

//	public Map<String, String> reserveLendingBook(Map<String, String> map) {
//	public int reserveLendingBook(String member_code) {
//	public List<LendBookBean> reserveLendingBook(String member_code) {
	
	
	@Override
	public List<Map<String, Object>> reserveLendingBook(String member_code) {
		return sqlSession.selectList(NS+"reserveLendingBook", member_code);
	}

	@Override
	public int insertLendingBook(LendingVo vo) {
		return sqlSession.insert(NS+"insertLendingBook",vo);
	}

	@Override
	public int insertLendingBookUpdate(LendingVo vo) {
		return sqlSession.update(NS+"insertLendingBookUpdate",vo);
	}

	@Override
	public List<LibMemberVo> deleyPenalty() {
		return sqlSession.selectList(NS+"deleyPenalty");
	}
	
	
//	BR_W_BM_207 대출 내역 조회 조인 쿼리
	
	@Override
//	public LendBookBean lendingList(List<Map<String, String>>  map) {
	public List<LendBookBean> lendingList(Map<String, Object>map) {
		return sqlSession.selectList(NS+"lendingList",map);
	}

	@Override
	public int reservationBook(LendingVo vo) {
		return sqlSession.insert(NS+"reservationBook",vo);
	}

	@Override
	public int reservationBookUpdate(BookInfoVo vo) {
		return sqlSession.update(NS+"reservationBookUpdate",vo);
	}

	@Override
	public int realReserBook(String lending_seq) {
		return sqlSession.update(NS+"realReserBook", lending_seq);
	}

	@Override
	public int realReserBookUpdate(LendingVo vo) {
		return sqlSession.update(NS+"realReserBookUpdate",vo);
	}

	@Override
	public List<LendingVo> fastReturnDayBook(LendingVo vo) {
		return sqlSession.selectList(NS+"fastReturnDayBook",vo);
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
	public int reserveDelUpdate(BookInfoVo vo) {
		return sqlSession.update(NS+"reserveDelUpdate",vo);
	}

	@Override
	public int lendingCount(String member_code) {
		return sqlSession.update(NS+"lendingCount",member_code);
	}
	
	
	
	
	
	

}
