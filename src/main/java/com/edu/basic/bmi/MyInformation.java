package com.edu.basic.bmi;

import java.util.ArrayList;

//-------------------------------------------------------------------------------------------------
// 나의 정보 : BMI(신체질량지수) 체크를 하는데 필요한 정보
//-------------------------------------------------------------------------------------------------
public class MyInformation {

	private	String				name;			// 이름
	private	double				height;			// 키
	private	double				weight;			// 몸무게
	private	ArrayList<String>	hobby;			// 취미
	private	BMICalculator		bmiCalculator;	// 신체질량지수 계산기
	
	// 키와 몸무게를 넣어서 처리하는 메서드
	public void bmiCalculator() {
		bmiCalculator.bmiCalculator(weight, height);
	}
	
	// 입력한 나의 정보를 출력하는 메서드
	public void getInfo() {
		System.out.println("이  름 : " + getName());
		System.out.println("키     : " + getHeight());
		System.out.println("몸무게 : " + getWeight());
		System.out.println("취  미 : " + getHobby());
		// BMI 계산을 하는 메서드를 호출한다.
		bmiCalculator();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public ArrayList<String> getHobby() {
		return hobby;
	}
	public void setHobby(ArrayList<String> hobby) {
		this.hobby = hobby;
	}
	public BMICalculator getBmiCalculator() {
		return bmiCalculator;
	}
	public void setBmiCalculator(BMICalculator bmiCalculator) {
		this.bmiCalculator = bmiCalculator;
	}
	
} // End - public class MyInformation
