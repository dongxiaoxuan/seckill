package com.geek.ms.Intercepter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.geek.ms.annotation.CacheLock;
import com.geek.ms.mapper.CacheKeyGenerator;
import com.geek.ms.util.RedisLockHelper;

import java.lang.reflect.Method;
import java.util.UUID;


@Aspect
@Configuration
public class CacheLockMethodInterceptor {

    @Autowired
    public CacheLockMethodInterceptor(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    private final RedisLockHelper redisLockHelper;
    private final CacheKeyGenerator cacheKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.geek.ms.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
            if (!success) {
            	System.out.println("重复提交！！！");
                throw new RuntimeException("重复提交");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
            //redisLockHelper.unlock(lockKey, value);
        }
    }
}
