package com.edu.basic.car.mycar05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarShop {

	public static void main(String[] args) {
		// 스프링을 통한 의존성 주입 : 스프링 설정 파일(XML)에서 속성을 주입한다.
		
		ApplicationContext ctx
			 = new ClassPathXmlApplicationContext("com/edu/basic/car/mycar05/appCtx.xml");
		
		// 사용할 빈을 불러온다.
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car.getTireBrand());

	}

}
