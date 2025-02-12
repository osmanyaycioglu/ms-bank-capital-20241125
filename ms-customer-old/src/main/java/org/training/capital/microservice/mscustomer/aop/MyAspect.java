package org.training.capital.microservice.mscustomer.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class MyAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);

    private Map<String, AtomicLong> deltaTimes = new ConcurrentHashMap<>();

    @Around("@annotation(methodTimeParam)")
    public Object method(ProceedingJoinPoint joinPointParam,
                         MethodTime methodTimeParam) {
        try {
            String     tagLoc = methodTimeParam.tag();
            AtomicLong lLoc   = deltaTimes.get(tagLoc);
            if (lLoc == null) {
                synchronized (this) {
                    lLoc = deltaTimes.get(tagLoc);
                    if (lLoc == null) {
                        lLoc = new AtomicLong();
                        deltaTimes.put(tagLoc,
                                       lLoc);
                    }
                }
            }
            long   delta      = System.nanoTime();
            Object proceedLoc = joinPointParam.proceed();
            delta = System.nanoTime() - delta;
            System.out.println(tagLoc + " Metod Delta : " + delta);
            long tagDelta = lLoc.addAndGet(delta);
            System.out.println(tagLoc + " Tag Delta : " + tagDelta);
            return proceedLoc;
        } catch (Throwable exp) {
            return null;
        }
    }

}
