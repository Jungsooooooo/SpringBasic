package com.edu.basic.coffee.coffee1;

public class Coffee {
	//private HotAmericano ame;
	private IceAmericano ame;
	
	public Coffee() {
		//ame = new HotAmericano();
		ame = new IceAmericano();
	}
	public void coffeeType() {
		System.out.println(ame.getName());
	}
}
