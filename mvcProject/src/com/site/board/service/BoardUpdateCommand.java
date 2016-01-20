package com.site.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;

public class BoardUpdateCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setTitle(request.getParameter("title"));
		dto.setAuthor(request.getParameter("author"));
		dto.setContent(request.getParameter("content"));
		dto.setPasswd(request.getParameter("passwd"));
		BoardMapper mapper = new BoardMapper();
		mapper.update(dto);
		mapper.close();
		
		request.setAttribute("url", "detail.do?num="+dto.getNum());
	}

}
