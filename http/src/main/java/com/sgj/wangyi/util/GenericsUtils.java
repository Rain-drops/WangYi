package com.sgj.wangyi.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by John on 2016/5/4.
 */
public class GenericsUtils {

    public static Class getSuperClassGenricType(Class clazz){

        return getSuperClassGenricType(clazz, 0);
    }

    private static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if(!(genType instanceof ParameterizedType)){
            return Object.class;
        }

        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        if(index >= params.length || index < 0){
            return Object.class;
        }
        if(!(params[index] instanceof Class)){
            return Object.class;
        }
        return (Class) params[index];

    }
}
