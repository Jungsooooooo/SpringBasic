package com.edu.basic.car.mycar02;

public class CarShop {

	public static void main(String[] args) {
		// 스프링없이 의존성 주입하기 => 생성자를 통한 의존성 주입
		//Tire 	tire = new AmericaTire();
		Tire 	tire = new KoreaTire();
		Car		car	 = new Car(tire);
		System.out.println(car.getTireBrand());
	}

}
