package com.edu.basic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//-------------------------------------------------------------------------------------------------
// public class BasicController
// @Controller : @Controller가 붙어있는 클래스를 컨트롤러로 설정하는 어노테이션이다.
// @RequestMapping 은 특정 url에 매칭되는 클래스나 메서드를 명시하는 어노테이션이다.
//
// @Controller : 컨트롤러 역할을 하는 빈으로 servlet-context.xml에 스캔대상이 된다.
// @RequestMapping("/basic") => url에 /basic으로 들어오면 전담으로 처리한다.
//-------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/basic")
public class BasicController01 {

	private static final Logger logger = LoggerFactory.getLogger(BasicController01.class);
	
	//-------------------------------------------------------------------------------------------------	
	// void 타입으로 메서드를 정의한 경우 url경로에 해당하는 jsp를 찾아서 실행한다.
	// ex) @RequestMapping("/basic/doA") 으로 접근을 했다면 basic폴더 밑에 있는 doA.jsp를 찾아서 실행한다.
	//-------------------------------------------------------------------------------------------------

	//-------------------------------------------------------------------------------------------------
	// public void doA()
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA가 실행됩니다.");
	} // End - public void doA()
	
	//-------------------------------------------------------------------------------------------------
	// public void doB()
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/doB")
	public void doB() {
		logger.info("doB가 실행됩니다.");
	} // End - public void doB()
	
	//-------------------------------------------------------------------------------------------------
	// 우편번호 검색
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/address", method=RequestMethod.GET)
	public String address() throws Exception {
		return "/basic/address";
	}
	
	
	
} // End - public class BasicController











