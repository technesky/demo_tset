package com.sky.design.status;

import java.lang.reflect.Field;

/**
 * @program: demo_tset
 * @description: test
 * @author: suznhg
 * @create: 2018-07-13
 **/
public class StatusUtil {

    public static void execto(ResultInterFace resultInterFace, CommonResponse<?> response) {
        String type = getFieldByClasss("ip", response.getObj());
        if ("1".equals(type)) {
            resultInterFace.success();
        } else {
            resultInterFace.fail();
        }
    }


    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param fieldName
     * @param object
     * @return
     */
    private static String getFieldByClasss(String fieldName, Object object)  {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                // 这里甚么都不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会进入

            }
        }
        //设置对象的访问权限，保证对private的属性的访问
        field.setAccessible(true);
        Object o= null;
        try {
            o = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o.toString();

    }


}
