package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.utils.ErrorsGetter;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@Aspect
public class ExceptionHandler {

    protected static Logger logger;

    public ExceptionHandler(){
        logger =
            Logger.getLogger(ExceptionHandler.class.getName());
    }

    @Around("execution(* org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.*.*(..))")
    public Object daoExceptionLog(ProceedingJoinPoint pjp) throws Throwable {
        Object output = null;
        try {
            output = pjp.proceed();
        } catch (Exception ex) {
            // TODO
            if (ErrorsGetter.get(ex).contains("ConstraintViolationException")){
                throw new Exception("ConstraintViolationException");
            }
            throw ex;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return output;
    }

    @Around("execution(* org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.*.*(..))")
    public Object serviceExceptionLog(ProceedingJoinPoint pjp) throws Throwable {
        Object output = null;
        try {
            output = pjp.proceed();
        } catch (Exception ex) {
            /*if (ex.getMessage().equals("ConstraintViolationException")){
                output =
                    ResponseModel.builder()
                        .status(ResponseModel.FAIL_STATUS)
                        .message("This name is already taken")
                        .build();
            } else {*/
                System.err.println("Google DataStore Error");
                ex.printStackTrace();
                output =
                    ResponseModel.builder()
                        .status(ResponseModel.FAIL_STATUS)
                        .message("Unknown storage error")
                        .build();
            //}
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return output;
    }
}
