package tp.appliSpring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Profile("dev")
public class MyPerfLogAspect {
	
	private static Logger logger = LoggerFactory.getLogger(MyPerfLogAspect.class);
	
	@Around("execution(* tp.appliSpring.core.service.*.*(..))")
	public Object doXxxLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		long td = System.nanoTime();
		Object objRes = pjp.proceed();
		long tf = System.nanoTime();
		logger.debug(
				">> trace == fin == " + pjp.getSignature().toShortString() + " [" + (tf - td) / 1000000.0 + " ms] >>");
		return objRes;
	}
}
