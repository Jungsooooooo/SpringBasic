package com.edu.basic.bmi;

//-------------------------------------------------------------------------------------------------
// BMI(신체질량지수) : 신장과 체중으로 알아보는 자신의 비만도
// BMI(Body Mass Index : 카우프지수)에 의한 비만도 계산
// BMI(신체질량지수) = 체중(kg) ÷ [신장(㎠)]
// 저체중 : 20미만
// 정  상 : 20 ~ 24
// 과체중 : 25 ~ 29
// 비  만 : 30이상
//-------------------------------------------------------------------------------------------------
public class BMICalculator {

	private	double	lowWeight;	// 저체중
	private	double	normal;		// 평균
	private	double	overWeight;	// 과체중
	private	double	obesity;	// 비만
	
	public void bmiCalculator(double weight, double height) {
		double h		= height * 0.01;
		double result	= weight / (h*h);
		
		System.out.println("BMI 지수 => " + (int)result);
		
		// if, else if를 사용할 때 큰 수부터 비교해 나가야 제대로 조건처리가 된다.
		// 안그러면 작은 수에 보다 큰 수들은 작은 수의 조건에 걸리게 된다.
		if(result > obesity) {
			System.out.println("비만입니다.");
		} else if(result > overWeight) {
			System.out.println("과체중입니다.");
		} else if(result > normal) {
			System.out.println("정상입니다.");
		} else {
			System.out.println("저체중입니다.");
		}
	}
	
	public double getLowWeight() {
		return lowWeight;
	}
	public void setLowWeight(double lowWeight) {
		this.lowWeight = lowWeight;
	}
	public double getNormal() {
		return normal;
	}
	public void setNormal(double normal) {
		this.normal = normal;
	}
	public double getOverWeight() {
		return overWeight;
	}
	public void setOverWeight(double overWeight) {
		this.overWeight = overWeight;
	}
	public double getObesity() {
		return obesity;
	}
	public void setObesity(double obesity) {
		this.obesity = obesity;
	}
	
	
} // End - public class BMICalculator
