package com.yedam.app.farm.vo;

import lombok.Data;

@Data
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next; 
	private int pageNum;

	public PageDTO(int pageNum, int total) {
		this.pageNum = pageNum;
		
		this.endPage = (int)Math.ceil(this.pageNum/10.0)*10; //ceil 소수점올림
		this.startPage = this.endPage-9;
		
		int realEnd = (int)(Math.ceil(total/10.0)); //아예 마지막 페이지
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage>1;
		this.next = this.endPage < realEnd;
		
		
	}
	
	
}

