package com.site.reply.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.site.reply.service.ReplyCommand;
import com.site.reply.service.ReplyListCommand;
import com.site.reply.service.ReplyWriteCommand;

@WebServlet(urlPatterns = {"/replies", "*.re"})
public class ReplyFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		String com = request.getRequestURI();
		
		ReplyCommand command = null;
		String nextPage = null;
		
		//댓글목록보기
		if(com.equals("/replies/replyList.re")) {
			command = new ReplyListCommand();
			command.execute(request, response);
			nextPage = "replyResult.jsp";
		}
		
		//댓글추가하기
		if(com.equals("/replies/replyInsert.re")) {
			command = new ReplyWriteCommand();
			command.execute(request, response);
			nextPage = "replyResult.jsp";
		}
		
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/"+nextPage);
		dis.forward(request, response);
		}
}
