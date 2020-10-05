package com.myproject.myboard.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advice {
	
	/*
	 * Before : Ŭ������ �޼ҵ� ���� ��
	 * within : BoardController Ŭ������ ����
	 */
	@Before("within (com.myproject.myboard.controller.BoardController)")
	public void beforeAdvice() {
		System.out.println("BoardController Before");
	}
	
	/*
	 * After : �޼ҵ� ���� ��
	 * execution : getBoardList �޼ҵ� ���� * �� ��� �޼ҵ� ���� ����
	 * ���������� : ���� ���� ex) public, private
	 * * : ��ȯ Ÿ��
	 * 
	 */
	@After("execution(* com.myproject.myboard.controller.BoardController.getBoardList(..))")
	public void afterAdvice() {
		System.out.println("after getBoardList");
	}
	
	/*
	 * AfterThrowing : ���� �߻� ��
	 * ��� Ŭ�������� �޼ҵ� ȣ�� ������ �߻����� �� ����
	 */
	@AfterThrowing(pointcut="execution(* com.wipia*..*.*(..))", throwing="e")
	public void afterThrowingAdvice(Exception e) {
		System.out.println("������ : "+e);
	}
	
	/*
	 * ��� �޼ҵ� ����� �󸶳� �ɸ����� �ð� ���
	 */
	@Around("execution (* com.myproject..*.*(..))")
	public Object time(ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		System.out.println("--- Target : "+pjp.getTarget());
		System.out.println("--- Parameter : "+Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result=pjp.proceed();
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("--- Time : "+(end-start));
		
		return result;
	}

}
