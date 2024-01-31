package baseline.version3.springboot.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ProcessLoggingAspect {

    @Pointcut("execution(* baseline.version3.springboot..*Service.*(..))")
    private void cut() {

    }

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();

        log.info("Start ['{}'] in ['{}']", methodName, className);

        for(int i = 0; i < params.length; i++){
            String param = params[i].toString();
            log.info(" - ['{}'] Param {} : {}", methodName, i, param);
        }
    }

    @After("cut()")
    public void after(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("End ['{}'] in ['{}']", methodName, className);
    }

}
