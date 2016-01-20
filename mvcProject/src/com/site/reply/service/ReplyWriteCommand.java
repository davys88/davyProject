package com.site.reply.service;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.reply.dao.ReplyMapper;
import com.site.reply.entity.ReplyDTO;

public class ReplyWriteCommand implements ReplyCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String resultData = "";
		StringBuffer sb = new StringBuffer();
		
		ObjectMapper om = new ObjectMapper();
		try {
			BufferedReader br = request.getReader();
			if(br != null) sb.append(br.readLine());
			
			ReplyDTO dto = om.readValue(sb.toString(), ReplyDTO.class	);
			
			ReplyMapper mapper = new ReplyMapper();
			int result = mapper.replyInsert(dto);
			mapper.close();
			
			if(result==1) {
				resultData = "SUCCESS";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("resultData", resultData);
	}

}
