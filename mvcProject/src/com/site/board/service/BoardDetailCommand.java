package com.site.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;

public class BoardDetailCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		
//		BoardDAO dao = BoardDAO.getInstance();
		BoardMapper mapper = new BoardMapper();
		BoardDTO data = mapper.detail(Integer.parseInt(num));
		mapper.close();
		
		if(data.getContent()!=null && (!data.getContent().equals(""))) {
			data.setContent(data.getContent().toString().replace("\n", "<br>"));
		}
		
		request.setAttribute("detail", data);
	}

}
