package com.edu.basic.coffee.coffee3;

public class Coffee {
	private Americano americano;

	public void setCoffee(Americano americano) {
		this.americano = americano;
	}
	public void coffeeType() {
		System.out.println(americano.getName());
	}
	
}
