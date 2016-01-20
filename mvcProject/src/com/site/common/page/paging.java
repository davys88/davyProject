package com.site.common.page;

import com.site.common.entity.CommonDTO;
import com.site.common.util.Util;

public class paging {
	/***************************************
	 * 페이징을 위한 설정 작업
	 * @param dto
	 */
	public static void set(CommonDTO dto) {
		int page = Util.nvl(dto.getPage(), 1);
		int pageSize = Util.nvl(dto.getPageSize(), 2);
		
		if(dto.getPage()==null) dto.setPage(page+"");
		if(dto.getPageSize()==null) dto.setPageSize(page+"");
		
		int start_row = (page-1)*pageSize+1;
		int end_row = (page-1)*pageSize + pageSize;
		
		dto.setStart_row(start_row+"");
		dto.setEnd_row(end_row+"");
		
		System.out.println("start_row : "+dto.getStart_row());
		System.out.println("end_row : "+dto.getEnd_row());	
	}
}
