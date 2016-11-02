package com.oreilly.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(void com.oreilly..*.set*(*))")
    public void callSetters(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String arg = joinPoint.getArgs()[0].toString();
        logger.info("Called " + method + " with arg " + arg +
            " on " + joinPoint.getTarget());
    }

    @Around("execution(String playGame())")
    public Object checkForRain(ProceedingJoinPoint pjp) throws Throwable {
        boolean rain = Math.random() < 0.01;
        Object result = null;
        if (rain) {
            logger.info(pjp.getTarget() + " rained out");
        } else {
            result = pjp.proceed();
            logger.info(result.toString());
        }
        return result;
    }
}
