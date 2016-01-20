package com.site.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReplyCommand {
	public void execute(HttpServletRequest request,HttpServletResponse response);
}
