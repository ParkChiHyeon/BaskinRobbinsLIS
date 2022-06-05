package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

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
		return null;
	}

	@Override
	public List<Notice_FAQBoardVo> viewAllNotice(Map<String, Object> map) {
		return sqlSession.selectList(NS+"viewAllNotice", map);
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
	public List<Notice_FAQBoardVo> selectPaging(Map<String, Object> map) {
		return null;
	}

	@Override
	public int getCountBoardList() {
		return 0;
	}

	@Override
	public int deleteNotice(List<String> seqs) {
		return sqlSession.delete(NS+"deleteBoard", seqs);
	}

	@Override
	public List<Notice_FAQBoardVo> viewNoticeAllAdmin() {
		return null;
	}






	
	
	
	
	
	
}
