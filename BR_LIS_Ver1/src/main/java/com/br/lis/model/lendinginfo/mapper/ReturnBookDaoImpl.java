package com.br.lis.model.lendinginfo.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;

@Repository
public class ReturnBookDaoImpl implements IReturnBookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS = "com.br.lis.model.lendinginfo.mapper.ReturnBookDaoImpl.";

	@Override
	public LendingVo lendingDetailForReturnBook(String book_serial) {
		return sqlSession.selectOne(NS+"lendingDetailForReturnBook", book_serial);
	}

	@Override
	public LibMemberVo lendingDetailForReturnUser(String member_code) {
		return sqlSession.selectOne(NS+"lendingDetailForReturnUser", member_code);
	}
	
	@Override
	public int allReturnBook(String lending_seq) {
		return sqlSession.update(NS+"allReturnBook", lending_seq);
	}

	@Override
	public int returnBookDate(String lending_seq) {
		return sqlSession.update(NS+"returnBookDate",lending_seq);
	}

	@Override
	public int damegeReturnBook(String lending_seq) {
		return sqlSession.update(NS+"damegeReturnBook", lending_seq);
	}

	@Override
	public int lossReturnBook(String lending_seq) {
		return sqlSession.update(NS+"lossReturnBook", lending_seq);
	}

	@Override
	public int dgAndLsReturnBookStatus(String lending_seq) {
		return sqlSession.update(NS+"dgAndLsReturnBookStatus", lending_seq);
	}

	@Override
	public int existReserveReturnBookStatus(String lending_seq) {
		return sqlSession.update(NS+"existReserveReturnBookStatus", lending_seq);
	}

	@Override
	public int returnLendingBookCount(String member_code) {
		return sqlSession.update(NS+"returnLendingBookCount", member_code);
	}

	@Override
	public int overdueLendingBook() {
		return sqlSession.update(NS+"overdueLendingBook");
	}

	@Override
	public List<LibMemberVo> overdueThreeDayLendingBook() {
		return sqlSession.selectList(NS+"overdueThreeDayLendingBook");
	}

	@Override
	public int delayLendingBook(String lending_seq) {
		return sqlSession.update(NS+"delayLendingBook", lending_seq);
	}
	
	@Override
	public int delayPossessingBook(String lending_seq) {
		return sqlSession.update(NS+"delayPossessingBook", lending_seq);
	}

	@Override
	public List<BookInfoVo> warehouseList() {
		return sqlSession.selectList(NS+"warehouseList");
	}

	@Override
	public List<BookInfoVo> supplementList() {
		return sqlSession.selectList(NS+"supplementList");
	}

	@Override
	public ReservationVo returnBookReserveCheck(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"returnBookReserveCheck",map);
	}

	@Override
	public int penaltyAuthModify() {
		return sqlSession.update(NS+"penaltyAuthModify");
	}

	@Override
	public List<LendBookBean> returnBookList() {
		return sqlSession.selectList(NS+"returnBookList");
	}

	@Override
	public List<LendBookBean> yetReturnBookList() {
		return sqlSession.selectList(NS+"yetReturnBookList");
	}

	@Override
	public List<BookInfoVo> possessingBookList() {
		return sqlSession.selectList(NS+"possessingBookList");
	}

	@Override
	public List<LendBookBean> lendingListUser(String member_id) {
		return sqlSession.selectList(NS+"lendingListUser", member_id);
	}






	
}
