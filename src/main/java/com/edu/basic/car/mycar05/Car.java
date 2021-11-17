package com.edu.basic.car.mycar05;

public class Car {
	Tire tire;

	public Tire getTire() {
		return tire;
	}

	public void setTire(Tire tire) {
		this.tire = tire;
	}
	
	public String getTireBrand() {
		return "현재 장착된 타이어는 " + tire.getBrand() + "입니다.";
	}
}
