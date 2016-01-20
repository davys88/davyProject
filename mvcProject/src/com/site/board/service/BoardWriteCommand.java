package com.site.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardDAO;
import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;

public class BoardWriteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDTO dto = new BoardDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setAuthor(request.getParameter("author"));
		dto.setContent(request.getParameter("content"));
		dto.setPasswd(request.getParameter("passwd"));
		
//		BoardDAO dao = BoardDAO.getInstance();
//		dao.write(dto);
		
		BoardMapper mapper = new BoardMapper();
		mapper.reqStepUpdate(dto);
		mapper.write(dto);
		mapper.close();
		
		request.setAttribute("url", "list.do");
	}

}
