package com.utilities;


import java.lang.reflect.Field;
import java.util.Arrays;

class JsonParser {

    static String classToJsonString(Object object) {
        StringBuilder result = new StringBuilder("{\n\"class\" : \"" + object.getClass().getSimpleName() + "\"");
        Field[] fields = object.getClass()
                                .getSuperclass()
                                .getDeclaredFields();
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

        fields = object.getClass()
                .getDeclaredFields();

        for (Field field : fields){
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
        }

        result.append("\n}");
        return result.toString();
    }
}
