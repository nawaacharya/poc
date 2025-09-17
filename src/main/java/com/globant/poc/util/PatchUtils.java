package com.globant.poc.util;

import java.lang.reflect.Field;

public class PatchUtils {

    public static <T> T patchUpdatedFields(T previous, T current) throws IllegalAccessException {
        for (Field field : previous.getClass().getDeclaredFields()) {
            if (!field.getName().equals("customerId") && !field.getName().equals("id")) {
                field.setAccessible(true);

                Object oldValue = field.get(previous);
                Object newValue = field.get(current);
                if (newValue != null && !newValue.equals(oldValue)) {
                    field.set(previous, newValue);
                }
            }

        }
        return previous;

    }
}
