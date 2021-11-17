package com.edu.basic.coffee.coffee2;

public class CoffeeShop {
	public static void main(String[] args) {
		HotAmericano 	hot 	= new HotAmericano();
		IceAmericano 	ice 	= new IceAmericano();
		TepidAmericano 	tepid	= new TepidAmericano();
		
		Coffee coffee = new Coffee(tepid);
		coffee.coffeeType();
	}

}
