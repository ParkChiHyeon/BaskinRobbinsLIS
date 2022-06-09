package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.Notice_FAQBoardVo;

@Repository
public class FAQBoardDaoImpl implements IFAQBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS ="com.br.lis.model.board.mapper.FAQBoardDaoImpl.";

	@Override
	public List<Notice_FAQBoardVo> viewAllFAQ() {
		return sqlSession.selectList(NS+"viewAllFAQ");
	}
	
	@Override
	public Notice_FAQBoardVo viewDetailFAQ(String seq) {
		return sqlSession.selectOne(NS+"viewDetailFAQ", seq);
	}

	@Override
	public int insertFAQ(Map<String, String> map) {
		return sqlSession.insert(NS+"insertFAQ", map);
	}

	@Override
	public int modifyFAQ(Notice_FAQBoardVo vo) {
		sqlSession.close();
		return sqlSession.update(NS+"modifyFAQ", vo);
	}

	@Override
	public int deleteFAQ(List<String> list) {
		sqlSession.close();
		return sqlSession.delete(NS+"deleteFAQ", list);
	}


}
