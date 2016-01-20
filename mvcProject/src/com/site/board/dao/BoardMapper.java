package com.site.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.site.board.entity.BoardDTO;
import com.site.query.SqlMapConfig;

public class BoardMapper {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	private SqlSession sqlSession;
	
	public BoardMapper() {
		sqlSession = factory.openSession(true);
	}
	
	public List<BoardDTO> list(BoardDTO dto) {
		return sqlSession.selectList("list", dto);
	}
	
	public void write(BoardDTO dto) {
		sqlSession.insert("write", dto);
	}
	
	public void close() {
		sqlSession.close();
	}

	public BoardDTO detail(int num) {
		sqlSession.update("readCount", num);
		return sqlSession.selectOne("detail", num);
	}

	public int pwdCheck(BoardDTO dto) {
		return sqlSession.selectOne("pwdCheck", dto);
	}

	public void delete(BoardDTO dto) {
		sqlSession.delete("delete", dto);
	}

	public void update(BoardDTO dto) {
		sqlSession.update("update", dto);
	}

	public void replyWrite(BoardDTO dto) {
		sqlSession.insert("replyWrite", dto);	
	}

	public void reqStepUpdate(BoardDTO dto) {
		sqlSession.update("reqStepUpdate", dto);		
	}

	public int listCnt(BoardDTO dto) {
		return (int)sqlSession.selectOne("listCnt", dto);
	}


}

