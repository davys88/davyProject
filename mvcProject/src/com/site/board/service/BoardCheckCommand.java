package com.site.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;

public class BoardCheckCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		String passwd = request.getParameter("passwd");
		
//		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(num));
		dto.setPasswd(passwd);
		BoardMapper mapper = new BoardMapper();
		int result = mapper.pwdCheck(dto);
		mapper.close();
		
		request.setAttribute("result", result);
	}

}
