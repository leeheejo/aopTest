package com.example.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class TestAOP {

	@Before("within(com.example.controller.MainController)")
	public void beforeMethod() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		System.out.println("before Method Header Value: " + request.getContentLength());
	}

	@AfterReturning(pointcut = "execution(* com.example.controller..*.*(..))", returning = "ret") // 패키지 및 하위 패키지에 있는
																									// 파라미터가 0개 이상인 모든
																									// 메서드
	public void afterReturning(JoinPoint joinPoint, String ret) {
		System.out.println("afterReturning method Return Value: " + ret);
	}

	@AfterThrowing(value = "execution(* com.example.controller..*.*(..))", throwing = "exception")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) throws RuntimeException {
		System.out.println("afterThrowing Method Throwing Value: " + exception.getMessage());
	}

	@Around("within(com.example.controller.MainController)")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {
			return pjp.proceed(pjp.getArgs());
		} finally {
			System.out.println("around method Request Value: " + request.getMethod().toString() + " "
					+ request.getRequestURI().toString());
		}
	}
}
