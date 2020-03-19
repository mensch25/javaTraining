package com.company.utilities;


import java.lang.reflect.Field;
import java.util.Arrays;

class JsonParser {

    String classToJsonString(Object object) {
        StringBuilder result = new StringBuilder("{\n\"class\" : \"" + object.getClass().getSimpleName() + "\"");
        Class clazz = object.getClass();

        Field[] fields = clazz.getSuperclass().getDeclaredFields();
        addFields(result, fields, object);

        fields = clazz.getDeclaredFields();

        addFields(result, fields, object);

        result.append("\n}");
        return result.toString();
    }

    private void addFields(StringBuilder result, Field[] fields, Object object) {
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            try {
                result.append(",\n\"").
                        append(field.getName()).
                        append("\" : \"").
                        append(field.get(object)).
                        append("\"");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}


