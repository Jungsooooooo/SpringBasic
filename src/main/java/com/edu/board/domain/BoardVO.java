package com.edu.board.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

//-------------------------------------------------------------------------------------------------
// public class BoardVO
//-------------------------------------------------------------------------------------------------
//@Getter
//@Setter
//@ToString
// @Data
public class BoardVO {

	private		int				bno;
	private		String			title;
	private		String			content;
	private		String			writer;
	private		Timestamp		regDate;
	private		int				viewCnt;
	private		List<BoardVO>	list;
	
	public BoardVO() {
		list = new ArrayList<BoardVO>();
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public List<BoardVO> getList() {
		return list;
	}

	public void setList(List<BoardVO> list) {
		this.list = list;
	}
	
	

} // End - public class BoardVO



