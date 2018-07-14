package com.sky.design.status;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @program: demo_tset
 * @description: test
 * @author: suznhg
 * @create: 2018-07-13
 **/
public class StatusUtil {

    public static void  execto(ResultInterFace resultInterFace,CommonResponse<?> response){
        String type=getFieldValueByFieldName("id",response.getObj());
        if ("1".equals(type)){
            resultInterFace.success();
        }else{
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
            return  field.get(object).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object getFieldValueByName(String fieldName, Class cls) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = cls.getMethod(getter, new Class[] {});
            Object value = method.invoke(cls.newInstance(), new Object[] {});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
