package com.edu.basic.coffee.coffee4;

public class Coffee {
	private IceAmericano ame;
	
	public Coffee() {
		ame = new IceAmericano();
	}
	public void coffeeType() {
		System.out.println(ame.getName());
	}
}
