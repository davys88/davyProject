package com.site.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.site.board.entity.BoardDTO;

public class BoardDAO {
	DataSource ds;

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	private BoardDAO() {
	}

	/*
	String requestURI = request.getRequestURI();
	// ���࿡ ��û URI : /mvcProject/board/list.do

	String contextPath = request.getContextPath();
	// contextPath : /mvcProject. �׷��� ���� ����  11.
			
	String com = requestURI.substring
			(contextPath.length());
	// �׷��� �ĺ��ڴ� requestURI.substring(���̼�[11] ����) ����.*/
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
		return ds.getConnection();
	}
	
	//�۸�� ��ȸ ����Ʈ �޼ҵ�
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT NUM, AUTHOR, TITLE, ");
			query.append("TO_CHAR(WRITEDAY, 'YYYY/MM/DD') WRITEDAY, ");
			query.append("READCNT, REPROOT, REPSTEP, REPINDENT, passwd ");
			query.append("FROM BOARD ORDER BY REPROOT DESC, REPSTEP ASC ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardDTO data = new BoardDTO();
			data.setNum(rs.getInt("num"));
			data.setAuthor(rs.getString("author"));
			data.setTitle(rs.getString("title"));
			data.setWriteday(rs.getString("writeday"));
			data.setReadcnt(rs.getInt("readcnt"));
			data.setRepRoot(rs.getInt("reproot"));
			data.setRepStep(rs.getInt("repstep"));
			data.setRepIndent(rs.getInt("repindent"));
			data.setPasswd(rs.getString("passwd"));
			list.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		return list;
	}

	//�۾��� ������Ʈ �޼ҵ�
	public void write(BoardDTO dto) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("insert into board(num, title, author, content, repRoot, repStep, repIndent, passwd, readcnt) ");
			query.append("values(board_seq.nextval, ?, ?, ?, board_seq.currval, 0, 0, ?, 0) ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null)	pstmt.close();
					if(con != null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	//�� �󼼺��� ����Ʈ �޼ҵ�
	public BoardDTO detail(String num) {
		readCount(num); //��ȸ�� ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO data = new BoardDTO();
		
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, content, ");
			query.append("TO_CHAR(writeday, 'YYYY/MM/DD HH24:MI:SS') writeday, ");
			query.append("readcnt, repRoot, repIndent, repStep ");
			query.append("FROM board WHERE num = ? ");
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepIndent(rs.getInt("repIndent"));
				data.setRepStep(rs.getInt("repStep"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		return data;
	}

	//��ȸ�� ���� ������Ʈ �޼ҵ�
	private void readCount(String num) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		//��ȸ�� 1 ����
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET ");
			query.append("readcnt = readcnt+1 ");
			query.append("WHERE num=? ");
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//�н����� üũ ����Ʈ �޼ҵ�
	public int pwdCheck(String num, String passwd) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT NVL((SELECT 1 FROM board WHERE num = ? AND passwd = ?), 0) ");
			query.append("as result FROM dual ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("result");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		return result;
	}
	//�� ���� ���� ������Ʈ �޼ҵ�
	public void update(BoardDTO dto) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET title=?, content=?, passwd=? WHERE num=? ");
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setInt(4, dto.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
	}

	public void delete(BoardDTO dto) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("DELETE board WHERE num = ? ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, dto.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
	}
}
