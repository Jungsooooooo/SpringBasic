package com.edu.basic.coffee.coffee2;

public class Coffee {
	// private HotAmericano ame;
	// private IceAmericano ame;
	private Americano americano;

	/*
	public Coffee() {
		// ame = new HotAmericano();
		ame = new IceAmericano();
	}
	*/
	public Coffee(Americano ame) {
		americano = ame;
	}
	public void coffeeType() {
		System.out.println(americano.getName());
	}
}
