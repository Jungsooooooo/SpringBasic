package com.edu.basic.car.mycar06;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
	@Autowired
	Tire kTire;
	
	public String getTireBrand() {
		return "장착된 타이어는 " + kTire.getBrand() + "입니다.";
	}
}
