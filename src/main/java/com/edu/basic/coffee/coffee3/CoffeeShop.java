package com.edu.basic.coffee.coffee3;

public class CoffeeShop {
	public static void main(String[] args) {
		HotAmericano	hot		= new HotAmericano();
		IceAmericano	ice		= new IceAmericano();
		Coffee 			coffee 	= new Coffee();
		
		coffee.setCoffee(hot);
		coffee.coffeeType();
		coffee.setCoffee(ice);
		coffee.coffeeType();
	}

}
