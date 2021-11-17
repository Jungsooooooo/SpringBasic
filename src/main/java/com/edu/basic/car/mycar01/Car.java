package com.edu.basic.car.mycar01;

public class Car {
	Tire tire;
	public Car() {
		// 자동차가 타이어를 생산하는 부분이 의존관계가 일어나고 있는 부분이다.
		//tire = new AmericaTire();
		tire = new KoreaTire();
	}
	public String getTireBrand() {
		return "현재 장착된 타이어는 " + tire.getBrand();
	}
}
