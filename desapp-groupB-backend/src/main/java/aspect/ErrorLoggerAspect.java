package aspect;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;


public class ErrorLoggerAspect {
	
	private Logger log = Logger.getLogger(ErrorLoggerAspect.class);
	
	@AfterThrowing(pointcut = "execution(*webService.vehiclecorcerns..*(..))",throwing = "exception")
	public void log(JoinPoint joinPoint, Exception exception){
		log.error("#ERROR " + LocalDateTime.now() + " -> " + exception.getMessage());
	}

}
