package com.site.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.site.board.entity.BoardDTO;
import com.site.query.SqlMapConfig;
import com.site.reply.entity.ReplyDTO;

public class ReplyMapper {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	private SqlSession sqlSession;
	
	public ReplyMapper() {
		sqlSession = factory.openSession(true);
	}
	public void close() {
		sqlSession.close();
	}
	
	public List<ReplyDTO> replyList(int num) {
		List<ReplyDTO> list = sqlSession.selectList("replyList", num);
		return list;
	}
	public int replyInsert(ReplyDTO dto) {
		int result = sqlSession.insert("replyInsert", dto);
		return result;
	}
	
	
	


}

