package com.edu.basic.car.mycar03;

public class CarShop {

	public static void main(String[] args) {
		// 스프링없이 의존성 주입하기 => 속성을 통한 의존성 주입.
		//Tire	tire	= new AmericaTire();
		Tire tire		= new KoreaTire();
		// 생성자를 통한 주입방법과 다른 부분
		Car		car		= new Car();
		car.setTire(tire);
		System.out.println(car.getTireBrand());
	}

}
