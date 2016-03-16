package com.fans.javassist.demo1;

import javassist.*;

import java.lang.reflect.Method;

/**
 * Created by 58 on 2016/3/15.
 */
public class TestClass {
    public static void main(String[] args)throws Exception {
        //创建类
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("cn.ibm.com.TestClass");

        //添加私有成员name及其getter， setter方法
        CtField param = new CtField(pool.get("java.lang.String"),"name",ctClass);
        param.setModifiers(Modifier.PRIVATE);
        ctClass.addMethod(CtNewMethod.setter("setName",param));
        ctClass.addMethod(CtNewMethod.getter("getName",param));
        ctClass.addField(param);

        //添加无参构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{},ctClass);
        ctConstructor.setBody("{name = \"Brand\" ;}");
        ctClass.addConstructor(ctConstructor);

        //添加有参的构造方法
        ctConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String")},ctClass);
        ctConstructor.setBody("{$0.name = $1;}");
        //打印创建类的名字
        System.out.println(ctClass.toClass());

        // 通过反射创建无参的实例，并调用getName方法
        Object o = Class.forName("cn.ibm.com.TestClass").newInstance();
        Method getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));

        // 调用其setName方法
        Method setter = o.getClass().getMethod("setName",new Class[]{String.class});
        setter.invoke(o,"Adam");
        System.out.println(getter.invoke(o));

        //通过反射创建有参的实例，并调用getName方法
        //o = Class.forName("cn.ibm.com.TestClass").getConstructor(String.class).newInstance("fanchunshuai");
        //getter = o.getClass().getMethod("getName");
        //System.out.println(getter.invoke(o));

    }
}
