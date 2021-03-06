package com.profess.component;

import com.profess.util.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 使用redis来缓存ip，限制ip可访问次数
 */
@Aspect
@Component
public class RequestTimesAop {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Pointcut("execution(public * com.profess.controller.FileController.upload(..))")
    public void limitUpload(){

    }
    @Pointcut("execution(public * com.profess.controller.ExpController.add(..))")
    public void limitAdd(){

    }
    /**
     * 限制了add和upload接口
     * JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象
     * @param joinPoint
     * @param times
     */
    @Before("(limitAdd() || limitUpload()) && @annotation(times)")
    public void ifovertimes(final JoinPoint joinPoint, RequestTimes times) {
        try {
            //java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；
            //Signature getSignature() ：获取连接点的方法签名对象；
            //java.lang.Object getTarget() ：获取连接点所在的目标对象；
            //java.lang.Object getThis() ：获取代理对象本身；
            //####################################################################
            /**
             * 比如：获取连接点方法运行时的入参列表
             *  不足：如果连接点方法中没有request参数的话，就没法获取request，如果不做处理的话，会报空指针异常的
             *  但是所有请求怎么可能没有request
             */
//            Object[] objects = joinPoint.getArgs();
//            HttpServletRequest request = null;
//            for (int i = 0; i < objects.length; i++) {
//                if (objects[i] instanceof HttpServletRequest) {
//                    request = (HttpServletRequest) objects[i];
//                    break;
//                }
//            }
            //####################################################################
            /**
             * 另一种获取request
             */
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String ip = IPUtil.getRemortIP(request);
            String url = request.getRequestURL().toString();
            String key = "ifovertimes:".concat(ip);
            //访问次数加一
            long count = redisTemplate.opsForValue().increment(key, 1);
            //如果是第一次，则设置过期时间
            if (count == 1) {
                redisTemplate.expire(key, times.time(), TimeUnit.SECONDS);
            }
            if (count > times.count()) {
                request.setAttribute("ifovertimes", "true");
            } else {
                request.setAttribute("ifovertimes", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

