package com.edu.basic.bmi;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//--------------------------------------------------------------------
// public class BmiTest
//--------------------------------------------------------------------
public class BmiTest {

	public static void main(String[] args) {

		System.out.println("스프링 컨테이너 구동 전입니다.");
		
		// classpath: => src/main/resources 를 가리킨다.
		String config = "classpath:applicationContext.xml";
		
		// 스프링 컨테이너를 생성한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(config);
		
		System.out.println("스프링 컨테이너가 구동되었습니다.");
		
		// 스프링 컨테이너에서 생성한 객체를 요청한다.
		MyInformation myInfo = ctx.getBean("myInfo", MyInformation.class);
		myInfo.getInfo();
		
		ctx.close();
	}

} // End - public class BmiTest













