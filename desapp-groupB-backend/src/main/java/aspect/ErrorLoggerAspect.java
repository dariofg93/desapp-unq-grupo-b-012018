package aspect;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ErrorLoggerAspect {
	
	private Logger log = Logger.getLogger(ErrorLoggerAspect.class);
	
	@After("execution(* *.SendingThread.run())")
	public void log(JoinPoint point){
		System.out.println("____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
		log.info("#SUCCESS" + LocalDateTime.now() + " -> " + "email sending to");
	}

}
