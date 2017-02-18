package com.hzcf.platform.api.annotation.biz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by leijiaming on 2017/2/18
 */
public class ExtendsOverProcessor {

    private Map<Class<? extends ExtendsOverHandler>, ExtendsOverHandler<Annotation>> extendsCache = new HashMap<>();

    public void setExtendsCache(Map<Class<? extends ExtendsOverHandler>, ExtendsOverHandler<Annotation>> extendsCache) {
        this.extendsCache = extendsCache;
    }

    /**
     * 获取要复写的验证
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public List<FieldTypeValue> getExtendsOverField(Object obj) throws IllegalAccessException {
        if (obj == null)
            return null;
        ExtendsOver ex = obj.getClass().getAnnotation(ExtendsOver.class);
        if (ex == null)
            return null;

        Class<? extends ExtendsOverHandler>[] hds = ex.handler();
        Class<? extends Annotation>[] ans = ex.annotation();

        Class<?> ocl = obj.getClass();
        Class<?> origin = ocl;      // 原始类型
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(ocl.getDeclaredFields()));

        while (ocl.getSuperclass() != Object.class) {
            ocl = ocl.getSuperclass();
            fields.addAll(Arrays.asList(ocl.getDeclaredFields()));
        }

        List<FieldTypeValue> cache = new ArrayList<>();
        for (Field f : fields) {
            if (f == null)
                continue;
            ex = f.getAnnotation(ExtendsOver.class);
            if (ex != null) {
                hds = ex.handler();
                ans = ex.annotation();
            }

            Annotation[] annotations = f.getDeclaredAnnotations();
            for (Annotation ann : annotations) {
                if (ann == null)
                    continue;

                for (int i = 0; i < ans.length; i++) {

                    Class<? extends Annotation> tac = ans[i];
                    if (tac == null)
                        continue;
                    if (i >= hds.length)
                        continue;
                    Class<? extends ExtendsOverHandler> ehc = hds[i];
                    if (ehc == null)
                        continue;

                    if (tac.isInstance(ann)) {
                        ExtendsOverHandler<Annotation> handler = extendsCache.get(ehc);
                        if (handler == null)
                            return null;
                        f.setAccessible(true);

                        Annotation _new = handler.createNew(ann, origin, f.getName(), f.getType());
                        cache.add(new FieldTypeValue(_new, f.get(obj), f.getType()));
                    }
                }
            }
        }
        return cache;
    }


    public static class FieldTypeValue {
        private Annotation annotation;
        private Object value;
        private Class<?> clazz;

        private FieldTypeValue(Annotation annotation, Object value, Class<?> clazz) {
            setAnnotation(annotation);
            setValue(value);
            setClazz(clazz);
        }

        public Annotation getAnnotation() {
            return annotation;
        }

        public void setAnnotation(Annotation annotation) {
            this.annotation = annotation;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }
    }

}
