package com.edu.basic.ballpen;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BallpenMain {
	public static void main(String[] args) {
		AbstractApplicationContext ctx
			= new GenericXmlApplicationContext("classpath:ballpenContext.xml");
		
		Ballpen ballpen = ctx.getBean("ballpen", Ballpen.class);
		ballpen.write();
		
		ctx.close();
	}

}
