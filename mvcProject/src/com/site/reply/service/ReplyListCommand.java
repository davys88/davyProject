package com.site.reply.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.reply.dao.ReplyMapper;
import com.site.reply.entity.ReplyDTO;

public class ReplyListCommand implements ReplyCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		String listData = "";
		
		ReplyMapper mapper = new ReplyMapper();
		List<ReplyDTO> list = mapper.replyList(Integer.parseInt(num));
		mapper.close();
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			listData = om.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("resultData", listData);
	}

}
