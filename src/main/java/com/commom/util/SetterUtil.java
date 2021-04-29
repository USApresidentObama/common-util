package com.commom.util;


import com.commom.test.User;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetterUtil {

    public static void main(String[] args) {
        buildSimpleSetterGetter(User.class, "user", "customerServiceCenterBizDto", false);
    }

    public static void buildSimpleSetterGetter(Class<?> clazz1, String src, String target, boolean isHump) {
        List<Field> declaredFields = getDeclaredFields(clazz1);
        for (Field f : declaredFields) {
            if (f.getName().equals("serialVersionUID")) {
                continue;
            }
            String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
            String setPrefix = ".set";
            String getPrefix = ".get";
            if (f.getType().equals(Boolean.class)) {
                setPrefix = ".is";
                getPrefix = ".";
            }
            if (StringUtil.isBlank(target)) {
                System.out.println(src + setPrefix + fieldName + "();");
            } else {
                System.out.println(src + setPrefix + fieldName + "(" + target + getPrefix
                        + getNeedFieldName(fieldName, isHump) + "());");
            }
        }
    }
    
    private static List<Field> getDeclaredFields(Class<?> clazz1) {
        if (clazz1 == null || clazz1 == Object.class) {
            return null;
        }
        List<Field> list = new ArrayList<Field>();
        Collections.addAll(list, clazz1.getDeclaredFields());
        List<Field> parentFieldList = getDeclaredFields(clazz1.getSuperclass());
        if (parentFieldList != null && !parentFieldList.isEmpty()) {
            list.addAll(parentFieldList);
        }
        return list;
    }

    /**
     * 是否转换驼峰结构
     * 
     * @param fieldName
     * @param isHump
     * @return
     */
    private static String getNeedFieldName(String fieldName, boolean isHump) {
        if (!isHump) {
            return fieldName;
        }

        boolean isNeedChange = false;
        char[] strChar = fieldName.toCharArray();
        StringBuilder newField = new StringBuilder();
        for (int i = 0; i < strChar.length; i++) {
            char cByte = strChar[i];
            if (cByte == '_') {
                isNeedChange = true;
                continue;
            }
            if (isNeedChange) {
                isNeedChange = false;
                newField.append(Character.toUpperCase(cByte));
                continue;
            }
            newField.append(cByte);
        }

        return newField.toString();
    }
}
