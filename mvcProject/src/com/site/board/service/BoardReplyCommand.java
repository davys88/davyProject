package com.site.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;

public class BoardReplyCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("num")!=null) {
			BoardDTO dto = new BoardDTO();
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setTitle(request.getParameter("title"));
			dto.setAuthor(request.getParameter("author"));
			dto.setContent(request.getParameter("content"));
			dto.setPasswd(request.getParameter("passwd"));
			dto.setRepRoot(Integer.parseInt(request.getParameter("repRoot")));
			dto.setRepIndent(Integer.parseInt(request.getParameter("repIndent")));
			dto.setRepStep(Integer.parseInt(request.getParameter("repStep")));
			
			BoardMapper mapper = new BoardMapper();
			mapper.replyWrite(dto);
			mapper.close();
			
			request.setAttribute("url", "list.do");
		}

	}

}
