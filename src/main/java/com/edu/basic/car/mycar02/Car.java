package com.edu.basic.car.mycar02;

public class Car {
	Tire tire;
	
	// 타이어의 종류는 생성자를 통해서 결정하게 한다.
	public Car(Tire tire) {
		this.tire = tire;
	}
	/* 타이어를 인터페이스로 만들지 않았다면
	public Car(KoreadTire tire) {
		this.tire = tire;
	}
	public Car(AmericaTire tire) {
		this.tire = tire;
	}
	*/
	public String getTireBrand() {
		return "현재 장착된 타이어는 " + tire.getBrand() + "입니다.";
	}
}
