package com.chenhm.rpc.proxy;

import com.chenhm.base.bytecode.ClassGenerator;
import com.chenhm.base.util.ReflectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chen-hongmin
 * @since 2018/1/8 17:48
 */
public class Proxy {

    public InvocationHandler handler;


    public Proxy() {

    }

    public Proxy newInstance(InvocationHandler handler) {
        this.handler = handler;
        return this;
    }

    /**
     * 获取代理类
     *
     * @param inf interface class array
     * @return Proxy instance.
     */
    public static Proxy getProxy(Class<?> inf) {

        if (inf == null || !inf.isInterface()) {
            return null;
        }

        ClassGenerator generator = ClassGenerator.newInstance(Proxy.class);
        generator.addInterface(inf.getName());

        Method[] methods = inf.getMethods();

        for (int i = 0; i < methods.length; i++) {

            Method method = methods[i];
            Class<?>[] pts = method.getParameterTypes();
            Class<?> rt = method.getReturnType();

            StringBuilder body = new StringBuilder("Object[] args = new Object[").append(pts.length).append("];");

            for (int j = 0; j < pts.length; j++) {
                body.append(" args[").append(j).append("] = arg" + j + ";");
            }
            body.append(" Object ret = handler.invoke(this, methods[" + i + "], args);");
            if (!Void.TYPE.equals(rt)) {
                body.append(" return ").append(asArgument(rt, "ret")).append(";");
            }
            generator.addMethod(method, body.toString());
        }
        generator.addField("public java.lang.reflect.Method[] methods;");

        Class<?> aClass = generator.toClass();
        try {
            Object proxy = aClass.newInstance();
            aClass.getField("methods").set(proxy, methods);

            return (Proxy) proxy;
        } catch (InstantiationException e) {
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }

    private static String asArgument(Class<?> cl, String name) {
        if (cl.isPrimitive()) {

            if (Boolean.TYPE == cl) {
                return name + "==null?false:((Boolean)" + name + ").booleanValue()";
            }
            if (Byte.TYPE == cl) {
                return name + "==null?(byte)0:((Byte)" + name + ").byteValue()";
            }
            if (Character.TYPE == cl) {
                return name + "==null?(char)0:((Character)" + name + ").charValue()";
            }
            if (Double.TYPE == cl) {
                return name + "==null?(double)0:((Double)" + name + ").doubleValue()";
            }
            if (Float.TYPE == cl) {
                return name + "==null?(float)0:((Float)" + name + ").floatValue()";
            }
            if (Integer.TYPE == cl) {
                return name + "==null?(int)0:((Integer)" + name + ").intValue()";
            }
            if (Long.TYPE == cl) {
                return name + "==null?(long)0:((Long)" + name + ").longValue()";
            }
            if (Short.TYPE == cl) {
                return name + "==null?(short)0:((Short)" + name + ").shortValue()";
            }
            throw new RuntimeException(name + " is unknown primitive type.");
        }
        return "(" + ReflectUtils.getName(cl) + ")" + name;
    }
}
