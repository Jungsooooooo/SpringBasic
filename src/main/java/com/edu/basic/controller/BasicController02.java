package com.edu.basic.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//---------------------------------------------------------------------------------------------------------------------
// public class BasicController02
//---------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/basic")
public class BasicController02 {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicController02.class);
	
	//---------------------------------------------------------------------------------------------------------------------
	// public String doC(@ModelAttribute("msg") String msg)
	// @ModelAttribute("msg") : 주소창에 있는 msg 파라미터의 값을 가져온다.
	// http://localhost:8090/basic/doC?msg=a123 => 자동적으로 msg의 값인 a123을 가져온다.
	//---------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/doC")
	public String doC(@ModelAttribute("msg") String msg) {
		
		logger.info("doC가 실행됩니다.");
		System.out.println("doC의 메시지 : " + msg);
		
		// return값이 문자열인 경우는 문자열.jsp 파일을 찾아서 실행한다.
		// return값에 경로까지 적어주어야 한다.
		return "/basic/doC";
	} // End  - public String doC(@ModelAttribute("msg") String msg)

	//---------------------------------------------------------------------------------------------------------------------
	// 구구단
	//---------------------------------------------------------------------------------------------------------------------
	// @RequestMapping("/gugu.doit") // GET, POST방식에 상관없음
	// public String gugu(Model model) {
	// public String gugu(HttpServletRequest request, Model model) {
	// public String gugu(int dan, Model model) {
	@RequestMapping(value="/gugu.doit", method=RequestMethod.GET)
	// public String gugu(Model model, int dan) {
	public String gugu(@RequestParam(defaultValue="2") int dan, Model model) {
		// Model : 데이터를 담는 그릇의 역할을 한다. map구조로 저장된다.
		// model.addAtrribute("변수명", 값);
		
		//---------------------------------------------------------------------------------------------------------------------
		// 구구단의 단을 고정하지 않고 url에서 요청하는 것으로 한다면
		//---------------------------------------------------------------------------------------------------------------------
		// int dan = 8;
		// Spring에서는 get에서 아래 줄과 같은 방식으로 사용하지 않는다.
		// int dan = Integer.parseInt(request.getParameter("dan"));
		
		// http://localhost:8090/basic/gugu.doit?dan=2
		// public String gugu(HttpServletRequest request, Model model) 을 사용하지 않고
		// public String gugu(int dan, Model model)를 사용한다.
		// 값을 넘겨주는 페이지에서 값은 String을 넘어오지만,
		// Spring 에서는 정수형으로 잘 받아들이고 있다.(이러한 점이 스프링의 편리한 점이다.)
		
		// public String gugu(int dan, Model model) 또는 public String gugu(Model model, int dan)
		// 변수명만 맞으면 매개변수의 위치는 상관이 없다.
		
		// public String gugu(int dan, Model model)
		// public String gugu(@RequestParam int dan, Model model)
		// 실제로는 @RequestParam이 생략된 것이므로, 기술하지 않아도 된다.
		
		// public String gugu(@RequestParam(defaultValue="2") int dan, Model model)
		// 매개변수에 값이 없을 때 기본값으로 설정하기 위해서 @RequestParam(defaultValue="2")를 사용한다.
		
		String result = "";
		
		for(int i = 1; i <= 9; i++) {
			result += "<h4>" + dan + " x " + i + " = " + dan*i + "</h4>";
		}
			
		model.addAttribute("result", result);
		return "basic/gugudan";
	}
	
	
} // End - public class BasicController02







