package com.edu.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.basic.controller.BookTypeController;
import com.edu.basic.dao.BookTypeDAO;
import com.edu.basic.dto.BookTypeDTO;

//-----------------------------------------------------------------------------------------------------------
// public class BookTypeServiceImpl implements BookTypeService
//-----------------------------------------------------------------------------------------------------------
@Service
public class BookTypeServiceImpl implements BookTypeService {

	private static final Logger logger = LoggerFactory.getLogger(BookTypeServiceImpl.class);
	
	@Inject
	private BookTypeDAO bookTypeDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// public List<BookTypeDTO> selectBookType()
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BookTypeDTO> selectBookType() throws Exception {

		logger.info("BookTypeServiceImpl selectBookType() Start.....");
		return bookTypeDAO.selectBookType();
	} // End - public List<BookTypeDTO> selectBookType()

} // End - public class BookTypeServiceImpl implements BookTypeService






