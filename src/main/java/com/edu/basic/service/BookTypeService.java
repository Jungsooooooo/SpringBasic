package com.edu.basic.service;

import java.util.List;

import com.edu.basic.dto.BookTypeDTO;

//-----------------------------------------------------------------------------------------------------------
// public interface BookTypeService
//-----------------------------------------------------------------------------------------------------------
public interface BookTypeService {

	//-----------------------------------------------------------------------------------------------------------
	// 도서 종류 데이터 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public List<BookTypeDTO> selectBookType() throws Exception;
	
} // End - public interface BookTypeService
