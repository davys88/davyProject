package com.site.board.entity;

import com.site.common.entity.CommonDTO;

public class BoardDTO extends CommonDTO {

	private int num;			//글 번호
	private String author;      //글 작성자
	private	String title;       //글 제목
	private String content;     //글 내용
	private int readcnt;        //글 조회수
	private String writeday;    //글 작성일
	private int repRoot;        //답변글 작성시 사용(원래글의 번호 참조)
	private int repStep;        //답변글 작성시 사용(답변글의 들여쓰기 지정)
	private int repIndent;      //답변글 작성시 사용(답변글의 순서지정)
	private String passwd;      //비밀 번호
	
	//조건검색시 사용할 속성
	private String search = "";
	private String keyword = "";
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getRepIndent() {
		return repIndent;
	}
	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public int getRepRoot() {
		return repRoot;
	}
	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}
	public int getRepStep() {
		return repStep;
	}
	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
