package com.chenhm.base.bytecode;

import com.chenhm.base.util.ReflectUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chen-hongmin
 * @since 2018/1/8 17:55
 */
public class ClassGenerator {

    private ClassPool classPool;
    /**
     * 目标类
     */
    private CtClass targetClass;


    /**
     * 父类
     */
    private String superClass;

    /**
     * 实现的接口集合
     */
    private Set<String> mInterfaces;

    /**
     * 方法列表
     */
    private Set<String> methods;

    /**
     * 字段
     */
    private Set<String> fields;

    private ClassGenerator(){

    }

    /**
     * 创建一个类
     * @param className
     * @return
     */
    public static ClassGenerator newInstance(String className){

        ClassGenerator generator = new ClassGenerator();
        ClassPool classPool = ClassPool.getDefault();
        generator.setClassPool(classPool);
        CtClass ctClass = classPool.makeClass(className);
        generator.setTargetClass(ctClass);

        return generator;
    }

    /**
     * 获取一个类
     * @param clazz
     * @return
     */
    public static ClassGenerator newInstance(Class<?> clazz){

        ClassGenerator generator = new ClassGenerator();
        ClassPool classPool = ClassPool.getDefault();
        generator.setClassPool(classPool);
        CtClass ctClass = classPool.makeClass(clazz.getName() + "0");

        generator.setTargetClass(ctClass);
        generator.setSuperClass(clazz.getName());

        return generator;
    }

    private void setClassPool(ClassPool classPool) {
        this.classPool = classPool;
    }

    public CtClass getTargetClass() {
        return targetClass;
    }

    private void setTargetClass(CtClass targetClass) {
        this.targetClass = targetClass;
    }


    public void setReName(String newClassName) {

        targetClass.replaceClassName(targetClass.getName(),newClassName);
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public Set<String> getmInterfaces() {
        return mInterfaces;
    }

    public void setmInterfaces(Set<String> mInterfaces) {
        this.mInterfaces = mInterfaces;
    }

    public void addInterface(String interfaceName) {

        if (mInterfaces == null){
            mInterfaces = new HashSet<>();
        }
        mInterfaces.add(interfaceName);
    }

    public Set<String> getMethods() {
        return methods;
    }

    public void addMethod(String name, int mod, Class<?> rt, Class<?>[] pts, Class<?>[] ets, String body) {

        StringBuilder sb = new StringBuilder();
        sb.append(modifier(mod)).append(' ').append(ReflectUtils.getName(rt)).append(' ').append(name);
        sb.append('(');
        for (int i = 0; i < pts.length; i++) {
            if (i > 0){
                sb.append(',');
            }
            sb.append(ReflectUtils.getName(pts[i]));
            sb.append(" arg").append(i);
        }
        sb.append(')');
        if (ets != null && ets.length > 0) {
            sb.append(" throws ");
            for (int i = 0; i < ets.length; i++) {
                if (i > 0){
                    sb.append(',');
                }
                sb.append(ReflectUtils.getName(ets[i]));
            }
        }
        sb.append('{').append(body).append('}');

        methods.add(sb.toString());
    }

    public void addMethod(Method method,String body) {

        if (methods == null){
            methods = new HashSet<>();
        }

        String name = method.getName();
        int modifiers = method.getModifiers();
        Class<?> returnType = method.getReturnType();
        Class<?>[] pts = method.getParameterTypes();
        Class<?>[] ets = method.getExceptionTypes();

        addMethod(name,modifiers,returnType,pts,ets,body);
    }

    public Set<String> getFields() {
        return fields;
    }

    public void addField(String field) {

        if (fields == null){
            fields = new HashSet<>();
        }

        fields.add(field);
    }

    public Class<?> toClass(){

        Class<?> rt = null;
        try{

            if (mInterfaces != null && !mInterfaces.isEmpty()){

                for (String mInterface : mInterfaces){
                    CtClass aClass = classPool.getCtClass(mInterface);
                    targetClass.addInterface(aClass);
                }
            }

            if (superClass != null){
                CtClass aClass = classPool.getCtClass(superClass);
                targetClass.setSuperclass(aClass);
            }

            if (fields != null && !fields.isEmpty()){
                for (String field : fields){
                    CtField make = CtField.make(field, targetClass);
                    targetClass.addField(make);
                }
            }
            if (methods != null && !methods.isEmpty()){
                for (String method : methods){
                    CtMethod mCtm = CtMethod.make(method,targetClass);
                    targetClass.addMethod(mCtm);
                }
            }

            rt = targetClass.toClass();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rt;
    }

    private static String modifier(int mod) {
        if (Modifier.isPublic(mod)){
            return "public";
        }
        if (Modifier.isProtected(mod)){
            return "protected";
        }
        if (Modifier.isPrivate(mod)){
            return "private";
        }
        return "";
    }
}
