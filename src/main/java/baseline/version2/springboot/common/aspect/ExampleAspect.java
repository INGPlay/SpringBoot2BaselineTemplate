package baseline.version2.springboot.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExampleAspect {

    @Pointcut("execution(* baseline.version2.springboot..*.*(..))")
    private void cut() {

    }

    @Pointcut("@annotation(baseline.version2.springboot.common.aspect.annotation.ExampleAnnotation)")
    private void annotation(){

    }
    
    @Before("cut() && annotation()")
    public void before(JoinPoint joinPoint){
        log.info("실행 전");
    }
    
    @After("cut() && annotation()")
    public void after(JoinPoint joinPoint){
        log.info("실행 후");
    }

//    @Around("cut() && annotation()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//        log.info("실행 전");
//
//        Object proceed = proceedingJoinPoint.proceed();
//
//        log.info("실행 후");
//    }

}
