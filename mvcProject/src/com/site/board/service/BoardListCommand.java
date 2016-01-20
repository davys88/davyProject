package com.site.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.board.dao.BoardMapper;
import com.site.board.entity.BoardDTO;
import com.site.common.page.paging;

public class BoardListCommand implements BoardCommand {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) {
      // TODO Auto-generated method stub
      
//      BoardDAO dao = BoardDAO.getInstance();
//      ArrayList<BoardDTO> list = dao.list();
      
	  BoardDTO dto = new BoardDTO();
	  dto.setPage(request.getParameter("page"));
	  dto.setPageSize(request.getParameter("pageSize"));
	  
	  dto.setSearch(request.getParameter("search"));
	  dto.setKeyword(request.getParameter("keyword"));
	  
	  //페이지 세팅
	  paging.set(dto);
	  
	  //전체 레코드수 구현
	  BoardMapper mapper = new BoardMapper();
	  int total = mapper.listCnt(dto);
	  List<BoardDTO> list = mapper.list(dto);
	  mapper.close();
	   
      request.setAttribute("list", list);
      request.setAttribute("total", total);
      request.setAttribute("data", dto);
   }
}