package com.site.board.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.site.board.service.BoardCheckCommand;
import com.site.board.service.BoardCommand;
import com.site.board.service.BoardDeleteCommand;
import com.site.board.service.BoardDetailCommand;
import com.site.board.service.BoardListCommand;
import com.site.board.service.BoardReplyCommand;
import com.site.board.service.BoardUpdateCommand;
import com.site.board.service.BoardWriteCommand;

@WebServlet(urlPatterns = {"/board", "*.do"})
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		String com = request.getRequestURI();
		
		BoardCommand command = null;
		String nextPage = null;
		
		if (com.equals("/board/list.do")) {
			command = new BoardListCommand();
			command.execute(request,response);
			nextPage = "list.jsp";
		}
		
		//글쓰기 폼
		if(com.equals("/board/writeForm.do")) {
			nextPage = "writeForm.jsp";
		}
		//글쓰기
		if(com.equals("/board/write.do")) {
			command = new BoardWriteCommand();
			command.execute(request, response);
			nextPage = "movePage.jsp";
		}
		//상세보기
		if(com.equals("/board/detail.do")) {
			command = new BoardDetailCommand();
			command.execute(request, response);
			nextPage = "detail.jsp";
		}
		//패스워드 확인
		if(com.equals("/board/pwdCheck.do")) {
			command = new BoardCheckCommand();
			command.execute(request, response);
			nextPage = "pwdCheck.jsp";
		}
		//게시글 수정폼으로 이동
		if(com.equals("/board/updateForm.do")) {
			command = new BoardDetailCommand();
			command.execute(request, response);
			nextPage = "updateForm.jsp";
		}
		//게시글 수정완료
		if(com.equals("/board/update.do")) {
			command = new BoardUpdateCommand();
			command.execute(request, response);
			nextPage = "movePage.jsp";
		}
		//게시글 삭제
		if(com.equals("/board/delete.do")) {
			command = new BoardDeleteCommand();
			command.execute(request, response);
			nextPage = "movePage.jsp";
		}
		//답변글 입력 폼 보기
		if(com.equals("/board/replyForm.do")) {
			command = new BoardDetailCommand();
			command.execute(request, response);
			nextPage = "replyForm.jsp";
		}
		//답변글달기
		if(com.equals("/board/reply.do")) {
			command = new BoardReplyCommand();
			command.execute(request, response);
			nextPage = "movePage.jsp";
		}
		
		
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/"+nextPage);
		dis.forward(request, response);
		}
}
