package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

import javax.mail.Session;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.Notice_FAQBoardVo;

@Repository
public class NoticeBoardDaoImpl implements INoticeBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.board.mapper.NoticeBoardDaoImpl.";

	@Override
	public Notice_FAQBoardVo viewDetailNotice(String seq) {
		return sqlSession.selectOne(NS+"viewDetailNotice",seq);
	}

	@Override
	public List<Notice_FAQBoardVo> viewAllNotice() {
		return sqlSession.selectList(NS+"viewAllNotice");
	}


	@Override
	public int modifyNotice(Notice_FAQBoardVo noticeVo) {
		return sqlSession.insert(NS+"modifyNotice",noticeVo);
	}

	@Override
	public int insertNotice(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertNotice",map);
	}
	
	@Override
	public int multiDelNotice(List<String> list) {
		return sqlSession.delete(NS+ "multiDelNotice", list);
	}

	
	@Override
	public List<Notice_FAQBoardVo> selectPaging(Map<String, Object> map) {
		return null;
	}

	@Override
	public int getCountBoardList() {
		return 0;
	}

	
}
