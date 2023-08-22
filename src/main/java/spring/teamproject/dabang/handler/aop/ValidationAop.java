package spring.teamproject.dabang.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.handler.exception.CustomValidationApiException;

@Slf4j
@Aspect
@Component
public class ValidationAop {
	
	@Pointcut("@annotation(spring.teamproject.dabang.handler.aop.annotation.ValidCheck)")
	private void enableValid() {};

	@Before("enableValid()")
	public void ValidBofore(JoinPoint joinpoint) {
		
		Object[] args = joinpoint.getArgs();
		log.info(">>>> 유효성 검사중... <<<<");
		
		for(Object arg : args) {
			if(arg.getClass() == BeanPropertyBindingResult.class) {
				BindingResult bindingResult = (BindingResult)arg;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMessage = new HashMap<String, String>();
					
					bindingResult.getFieldErrors().forEach(error -> {
						System.out.println("오류발생 필드명: " + error.getField());
						System.out.println("오류발생 상세메시지: "+ error.getDefaultMessage());
						errorMessage.put(error.getField(), error.getDefaultMessage());
					});
					throw new CustomValidationApiException("유효성 검사 실패", errorMessage);
				}
			}
		}
	}
		
				
	@AfterReturning(value = "enableValid()", returning = "returnObj")
	public void afterReturn(JoinPoint joinPoint, Object returnObj) {
		log.info("유효성 검사 완료: {}", returnObj);
	}
			
		
	
}