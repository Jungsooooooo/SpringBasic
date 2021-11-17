package com.edu.basic.car.mycar06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarShop {

	public static void main(String[] args) {
		// 스프링을 통한 의존성 주입
		// 스프링 설정 파일을 읽어들인다. => 컨테이너가 구성이 된다.
		// 컨테이터를 구성한다. => 설정 파일에서 <bean>으로 지정한 클래스들이 Bean으로 등록이 된다.
		ApplicationContext ctx
			= new ClassPathXmlApplicationContext("com/edu/basic/car/mycar06/appCtx.xml");
		
		// 필요한 빈을 가져온다.
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car.getTireBrand());

	}

}
