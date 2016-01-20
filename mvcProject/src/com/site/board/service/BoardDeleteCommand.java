package com.site.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;

public class BoardDeleteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		new BoardMapper().delete(dto);
		
		request.setAttribute("url", "list.do");
	}
}
