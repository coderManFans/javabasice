package com.fans.javassist.test;

import com.fans.javassist.demo1.TestProxy;
import javassist.*;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by 58 on 2016/3/14.
 * 1.      Javassist不支持要创建或注入的类中存在泛型参数
 2.      Javassist对@类型的注解（Annotation）只支持查询，不支持添加或修改

 */
public class JavaSsistTest {

    /*@Test
    public void test() throws NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.fans.javassis.demo1.Test");

        //为类型设置字段
        CtField field = new CtField(classPool.get(String.class.getName()),"value",ctClass);
        field.setModifiers(Modifier.PRIVATE);
        ctClass.addMethod(CtNewMethod.setter("setValue",field));
        ctClass.addMethod(CtNewMethod.getter("getValue",field));
        ctClass.addField(field);

        // 为类设置构造器
        // 无参构造器
        CtConstructor constructor = new CtConstructor(null,ctClass);
        constructor.setModifiers(Modifier.PUBLIC);
        constructor.setBody("{}");
        ctClass.addConstructor(constructor);

        constructor = new CtConstructor(new CtClass[]{classPool.get(String.class.getName())},ctClass);
       // constructor.setModifiers(Modifier);

    }

    //获取类型信息
    @Test
    public void test1() throws NotFoundException{
        //获取默认类型池对象
        ClassPool classPool = ClassPool.getDefault();

        //获取指定的类型
        CtClass ctClass = classPool.get("java.lang.String");
        //获取类名
        System.out.println("className : "+ctClass.getName());
        //获取包名
        System.out.println("\tpackage "+ctClass.getPackageName());
        System.out.println("\t"+Modifier.toString(ctClass.getModifiers()));
        //获取限定符和简要类名
        System.out.println("\t"+ Modifier.toString(ctClass.getModifiers())+" class "+ctClass.getSimpleName());
        //获取超类
        System.out.println(" extends "+ctClass.getSuperclass().getName());
        if(ctClass.getInterfaces() != null){
            System.out.println(" implements ");
            boolean first = true;
            for(CtClass c : ctClass.getInterfaces()){
                if(first){
                    first = false;
                }else {
                    System.out.print(", ");
                }
                System.out.println(c.getName());
            }
            System.out.println();
        }
    }

    // 动态创建类
    @Test
    public void test2() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool classPool = ClassPool.getDefault();
        //创建一个类
        CtClass ctClass = classPool.getCtClass("com.fans.javassist.demo1.DynamicClass");

        //为类型设置接口
        ctClass.setInterfaces(new CtClass[]{classPool.get(Runnable.class.getName())});


        //为类型设置字段
        CtField ctField = new CtField(classPool.get(String.class.getName()),"value",ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField);
        //添加getter和setter方法
        ctClass.addMethod(CtNewMethod.setter("setValue",ctField));
        ctClass.addMethod(CtNewMethod.getter("getValue",ctField));

        //为类设置构造器(这里不能设置无参构造方法)
        *//*CtConstructor ctConstructor = new CtConstructor(null,ctClass);
        ctConstructor.setModifiers(Modifier.PUBLIC);
        ctConstructor.setBody("{}");
        ctClass.addConstructor(ctConstructor);*//*

        //参数构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{classPool.get(String.class.getName())},ctClass);
        ctConstructor.setModifiers(Modifier.PUBLIC);
        ctConstructor.setBody("{this.value=$1;}");
        ctClass.addConstructor(ctConstructor);

        //为类设置方法
        CtMethod method = new CtMethod(CtClass.voidType,"run",null,ctClass);
        method.setModifiers(Modifier.PUBLIC);
        method.setBody("{System.out.println(\"执行结果\"+ this.value);}");
        ctClass.addMethod(method);
        //加载和执行生成的类
        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();
        clazz.getMethod("setValue",String.class).invoke(obj,"hello");
        clazz.getMethod("run").invoke(obj);
        obj = clazz.getConstructor(String.class).newInstance("OK");
        clazz.getMethod("run").invoke(obj);

    }

    //创建代理类
    @Test
    public void test4() throws IllegalAccessException, InstantiationException {
        //实例化工厂
        ProxyFactory proxyFactory = new ProxyFactory();

        //设置父类
        proxyFactory.setSuperclass(TestProxy.class);

        proxyFactory.setFilter(new MethodFilter() {
            public boolean isHandled(Method m) {
                return m.getName().startsWith("get");
            }
        });

        Class<?> clazz = proxyFactory.createClass();
        TestProxy testProxy = (TestProxy)clazz.newInstance();
        ((ProxyObject)testProxy).setHandler(new MethodHandler() {
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                //拦截后前置处理，改写name属性的内容
                //实际情况可根据需求修改
                System.out.println(thisMethod.getName()+"被调用");
                try {
                    Object ret = proceed.invoke(self,args);
                    System.out.println("返回值："+ret);
                    return ret;
                }finally {
                    System.out.println(thisMethod.getName()+"调用完毕");
                }

            }
        });
        testProxy.setName("Alvin");
        testProxy.setValue("1000");
        testProxy.getValue();
    }*/

}
