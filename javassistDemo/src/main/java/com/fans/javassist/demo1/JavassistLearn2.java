package com.fans.javassist.demo1;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by 58 on 2016/3/15.
 * javassist 实现AOP，对一些类统一加权限过滤，加日志监控等
 * 对JavassistClass类的getName()方法进行拦截前置处理
 */
public class JavassistLearn2 {
    public static void main(String[] args)throws Exception {
        ProxyFactory factory = new ProxyFactory();
        //设置弗雷，ProxyFactory将会动态生成一个类，继承该父类
        factory.setSuperclass(JavassistClass.class);

        //设置过滤器，判断哪些方法调用需要被拦截
        factory.setFilter(new MethodFilter() {
            public boolean isHandled(Method method) {
                if(method.getName().equals("getName")){
                    return true;
                }
                return false;
            }
        });

        factory.setHandler(new MethodHandler() {
            public Object invoke(Object self, Method method, Method proced, Object[] objects) throws Throwable {
                //拦截后，前置处理，改写name属性的内容
                //实际情况可根据需求修改
                JavassistClass o = (JavassistClass) self;
                o.setName("haha");
                return proced.invoke(o);
            }
        });
        Class<?> c = factory.createClass();
        JavassistClass object = (JavassistClass) c.newInstance();
        System.out.println(object.getName());
    }


}
