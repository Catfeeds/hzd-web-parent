package com.hzcf.platform.api.annotation.biz;


import com.hzcf.platform.common.util.log.Log;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;
import javassist.bytecode.annotation.Annotation;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by leijiaming on 2017/2/18
 */
public class CreateTemper {

    private static Log logger = Log.getLogger(CreateTemper.class);

    public static Class<?> getNewClass(Class<?> vcl, java.lang.annotation.Annotation ann)
            throws InvocationTargetException, IOException, CannotCompileException, NotFoundException, IllegalAccessException {
        return getNewClass(vcl, ann, null);
    }

    private static Class<?> getNewClass(Class<?> vcl, java.lang.annotation.Annotation ann, Map<String, Object> value)
            throws InvocationTargetException, IOException, CannotCompileException, NotFoundException, IllegalAccessException {

        String className = getClassName(vcl, ann, value);
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            try {
                ClassPool pool = ClassPool.getDefault();
                ClassLoader loader = pool.getClassLoader();

                CtClass ctClass = pool.makeClass(className);
                //添加字段
                CtField field = new CtField(pool.getCtClass(vcl.getName()), "value", ctClass);

                FieldInfo fieldInfo = field.getFieldInfo();

                ConstPool cp = fieldInfo.getConstPool();
                //获取注解信息
                AnnotationsAttribute attribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);

                Annotation annotation;
                if (value == null || value.isEmpty()) {
                    annotation = getAnnotation(pool, cp, ann);
                } else {
                    annotation = getAnnotation(pool, cp, ann, value);
                }
                attribute.setAnnotation(annotation);
                fieldInfo.addAttribute(attribute);

                ctClass.addField(field);

                clazz = ctClass.toClass(loader);
            } catch (NotFoundException | CannotCompileException
                    | IllegalAccessException | IOException | InvocationTargetException en) {
                logger.e("dynamic create org.cf.myfen.webapp.annotation.validation.Temper has an error", en);
                throw en;
            }
        }
        return clazz;
    }


    public static Object createTemper(Object val, Class<?> vcl, java.lang.annotation.Annotation ann) {
        try {
            Class<?> clazz = getNewClass(vcl, ann, null);
            Object o = clazz.newInstance();
            Field fv = clazz.getDeclaredField("value");
            fv.setAccessible(true);
            fv.set(o, val);
            return o;
        } catch (Exception e) {
            return new Object();
        }
    }

    private static String getClassName(Class<?> vcl, java.lang.annotation.Annotation ann, Map<String, Object> value) {
        String suf = null;
        try {
            StringBuilder sb = new StringBuilder(ann.annotationType().getName());// 注解类型
            Method[] fields = ann.annotationType().getDeclaredMethods();
            for (Method m : fields) {
                Object _val;
                if (value == null || value.isEmpty())
                    _val = m.invoke(ann);
                else
                    _val = value.get(m.getName());
                sb.append(m.getName());
                if (_val instanceof Object[]) {
                    Object[] arr = (Object[]) _val;
                    sb.append(arr.length);
                } else {
                    sb.append(_val);
                }
            }
            sb.append(vcl.getName());// 参数类型
           /* if (sb.length() > 0)
                suf = Md5Util.md5(sb.toString());*/
        } catch (Exception e) {
            //
        }
        return "org.cf.myfen.webapp.annotation.validation.Temper" + (suf == null ? "" : suf);
    }

    private static Annotation getAnnotation(ClassPool pool, ConstPool cp, java.lang.annotation.Annotation ann) throws IllegalAccessException,
            NotFoundException, IOException, InvocationTargetException {

        Method[] methods = ann.annotationType().getDeclaredMethods();

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        AnnotationsWriter writer = new AnnotationsWriter(bo, cp);
        ConstPool constPool = writer.getConstPool();
        Annotation annotation = new Annotation(ann.annotationType().getName(), constPool);
        for (Method m : methods) {
            if (m == null)
                continue;
            CtClass rType = pool.get(m.getReturnType().getName());
            String name = m.getName();
            Object o = m.invoke(ann);
            if (o == null)
                continue;
            annotation.addMemberValue(name, createMemberValue(constPool, rType, o));
        }
        return annotation;
    }

    private static Annotation getAnnotation(ClassPool pool, ConstPool cp, java.lang.annotation.Annotation ann, Map<String, Object> value) throws IllegalAccessException,
            NotFoundException, IOException, InvocationTargetException {

        Method[] methods = ann.annotationType().getDeclaredMethods();

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        AnnotationsWriter writer = new AnnotationsWriter(bo, cp);
        ConstPool constPool = writer.getConstPool();
        Annotation annotation = new Annotation(ann.annotationType().getName(), constPool);
        for (Method m : methods) {
            if (m == null)
                continue;
            CtClass rType = pool.get(m.getReturnType().getName());
            String name = m.getName();
            Object o = value.get(name);
            if (o == null)
                o = m.invoke(ann);
            if (o == null)
                continue;
            annotation.addMemberValue(name, createMemberValue(constPool, rType, o));
        }
        return annotation;
    }

    private static MemberValue createMemberValue(ConstPool cp, CtClass type, Object val)
            throws NotFoundException {
        if (type == CtClass.booleanType)
            return new BooleanMemberValue((boolean) val, cp);
        else if (type == CtClass.byteType)
            return new ByteMemberValue((byte) val, cp);
        else if (type == CtClass.charType)
            return new CharMemberValue((char) val, cp);
        else if (type == CtClass.shortType)
            return new ShortMemberValue((short) val, cp);
        else if (type == CtClass.intType)
            return new IntegerMemberValue(cp, (int) val);
        else if (type == CtClass.longType)
            return new LongMemberValue((long) val, cp);
        else if (type == CtClass.floatType)
            return new FloatMemberValue((float) val, cp);
        else if (type == CtClass.doubleType)
            return new DoubleMemberValue((double) val, cp);
        else if (type.getName().equals("java.lang.Class")) {
            String cn;
            if (CtClass.class.isInstance(val)){
                CtClass _val = (CtClass) val;
                cn = _val.getName();
            } else {
                cn = ((Class) val).getName();
            }
            return new ClassMemberValue(cn, cp);
        } else if (type.getName().equals("java.lang.String"))
            return new StringMemberValue((String) val, cp);
        else if (type.isArray()) {
            CtClass at = type.getComponentType();

            MemberValue member = createMemberValue(cp, at, at);
            ArrayMemberValue amv = new ArrayMemberValue(member, cp);
            Object[] array = (Object[]) val;
            MemberValue[] values = new MemberValue[array.length];
            for (int i = 0; i < array.length; i++) {
                values[i] = createMemberValue(cp, at, array[i]);
            }
            amv.setValue(values);
            return amv;
        } else if (type.isInterface()) {
            Annotation info = new Annotation(cp, type);
            return new AnnotationMemberValue(info, cp);
        } else {
            EnumMemberValue emv = new EnumMemberValue(cp);
            emv.setType(type.getName());
            emv.setValue(val.toString());
            return emv;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends java.lang.annotation.Annotation> T getNewAnnotation(Class<?> vcl, T ann, Map<String, Object> value) {
        try {
            Class<?> clazz = getNewClass(vcl, ann, value);
            Field fv = clazz.getDeclaredField("value");
            return (T) fv.getAnnotation(ann.annotationType());
        } catch (Exception e) {
            return ann;
        }
    }

}
