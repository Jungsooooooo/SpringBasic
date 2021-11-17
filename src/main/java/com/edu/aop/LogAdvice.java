package com.edu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.java.Log;

//-----------------------------------------------------------------------------------------------------------
// 관심사를 실제로 구현한 코드로써 로그를 기록해주는 역할을 하는 클래스
// @Aspect		: 해당 클래스의 객체가 Aspect를 구현한 것임을 나타내기 위해서 사용
// @Log			: 로그 메시지를 출력하기 위해서 사용
// @Component	: AOP와 관계는 없지만 스프링에서 Bean으로 인식하기 위해서 사용
//-----------------------------------------------------------------------------------------------------------
@Aspect
@Log
@Component
public class LogAdvice {

	//-----------------------------------------------------------------------------------------------------------
	// 로그 메시지를 사용하기 위한 변수
	//-----------------------------------------------------------------------------------------------------------
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그를 보여주는 메서드
	// @Before, @After, @AfterReturning, @AfterThrowing, @Around
	// @Before (이전) : 어드바이스 타겟 메소드가 호출되기 전에 어드바이스 기능을 수행
	// @After (이후) : 타겟 메소드의 결과에 관계없이(즉 성공, 예외 관계없이) 타겟 메소드가 완료 되면 어드바이스 기능을 수행
	// @AfterReturning (정상적 반환 이후)타겟 메소드가 성공적으로 결과값을 반환 후에 어드바이스 기능을 수행
	// @AfterThrowing (예외 발생 이후) : 타겟 메소드가 수행 중 예외를 던지게 되면 어드바이스 기능을 수행
	// @Around (메소드 실행 전후) : 어드바이스가 타겟 메소드를 감싸서 타겟 메소드 호출전과 후에 어드바이스 기능을 수행

	//-----------------------------------------------------------------------------------------------------------
	@Before("execution(* com.edu.board2.service.*.*(..))")
	public void board2LogBefore() throws Exception {
		logger.info("==========================================================================================");
		logger.info("나는 @Before AOP 입니다.");
		logger.info("==========================================================================================");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------
	@After("execution(* com.edu.board2.service.*.*(..))")
	public void board2LogAfter() throws Exception {
		logger.info("==========================================================================================");
		logger.info("나는 @After AOP 입니다.");
		logger.info("==========================================================================================");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 모든 패키지 내의 service, dao package에 존재하는 클래스
	//-----------------------------------------------------------------------------------------------------------
	@Around("execution(* *..service.*.*(..)) or execution(* *..dao.*.*(..))")
	public Object calculateExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		
		// 해당 클래스의 처리 전 시간
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 해당 클래스의 메서드 실행 ==> 핵심업무
		Object result = pjp.proceed();
		
		// 해당 클래스의 처리 후 시간
		sw.stop();
		// start와 stop 사이의 걸린 시간을 가져온다.
		long executionTime = sw.getTotalTimeMillis();
		
		String	className		= pjp.getTarget().getClass().getName();	// 핵심업무 클래스명
		String	methodName		= pjp.getSignature().getName();			// 핵심업무 메서드명
		String	task			= className + "." + methodName;
		
		logger.info("[업무처리시간] " + task + " ==> " + executionTime + "(ms)");
		
		return result;
		
	} // End - public Object calculateExecutionTime(ProceedingJoinPoint pjp)


} // End - public class LogAdvice














