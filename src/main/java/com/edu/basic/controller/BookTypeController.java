package com.edu.basic.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.basic.dto.BookTypeDTO;
import com.edu.basic.service.BookTypeService;

//-----------------------------------------------------------------------------------------------------------
// public class BookTypeController
//-----------------------------------------------------------------------------------------------------------
@Controller
public class BookTypeController {

	private static final Logger logger = LoggerFactory.getLogger(BookTypeController.class);
	
	@Inject
	private BookTypeService bookTypeService;
		
	//-----------------------------------------------------------------------------------------------------------
	// public String bookTypeList(Model model)
	//-----------------------------------------------------------------------------------------------------------
	//@RequestMapping(value="/basic/booktype", method=RequestMethod.GET)
	@RequestMapping("/basic/booktype")
	public String bookTypeList(Model model) throws Exception {
		
		logger.info("BookTypeController bookTypeList() Start.....");
		
		List<BookTypeDTO> typeList = bookTypeService.selectBookType();
		
		System.out.println("[Select Data] ==> " + typeList);
		
		model.addAttribute("typeList", typeList);
		
		return "/basic/typeList";
	} //  End - public String bookTypeList(Model model)
	
} // End - public class BookTypeController






