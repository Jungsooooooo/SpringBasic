package com.edu.basic.car.mycar04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CarShop {

	public static void main(String[] args) {

		// 스프링을 통한 의존성 주입 - XML파일을 사용하는 방법
		
		//ApplicationContext context
		//	= new ClassPathXmlApplicationContext("com/edu/basic/car/mycar04/appCtx.xml");
		//ApplicationContext context
		//	= new GenericXmlApplicationContext("com/edu/basic/car/mycar04/appCtx.xml");
		ApplicationContext context
			= new FileSystemXmlApplicationContext("src/main/java/com/edu/basic/car/mycar04/appCtx.xml");
		
		Car		car		= context.getBean("car",			Car.class);
		Tire	tire	= context.getBean("tire",			Tire.class);
		Tire	aTire	= context.getBean("americaTire",	AmericaTire.class);
		
		car.setTire(tire);
		System.out.println(car.getTireBrand());
		
		car.setTire(aTire);
		System.out.println(car.getTireBrand());
	}

}
