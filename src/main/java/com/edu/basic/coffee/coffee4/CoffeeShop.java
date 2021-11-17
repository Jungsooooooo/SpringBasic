package com.edu.basic.coffee.coffee4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CoffeeShop {

	public static void main(String[] args) {

		ApplicationContext context
			= new GenericXmlApplicationContext("com/edu/basic/coffee/coffee4/applicationContext.xml");
		
		//Coffee coffee = (Coffee) context.getBean("coffee"); 	// 직접 형변환하는 방법
		Coffee coffee = context.getBean("coffee", Coffee.class);// 클래스의 정보를 같이 넘겨주는 방법.
		coffee.coffeeType();
	}

}
