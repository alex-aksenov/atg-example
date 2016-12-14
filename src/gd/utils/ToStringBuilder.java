package gd.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class ToStringBuilder {

    private ToStringBuilder() {
    }

    public static String toString(Object obj) {

        String result;

        Boolean isJustIterable = obj instanceof Iterable ? Boolean.TRUE : null; // true = iterable; false = array;
        if (Boolean.TRUE != isJustIterable) {
            isJustIterable = obj instanceof Object[] ? Boolean.FALSE : null;
        }

        if (isJustIterable == null) { //nor iterable, not array
            result = writeObject(obj);
        } else {
            Iterator iterator = isJustIterable ? ((Iterable) obj).iterator() :
                    Arrays.asList((Object[]) obj).iterator();

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("[");
            String sep = "";
            while (iterator.hasNext()) {
                resultBuilder.append(sep).append(writeObject(iterator));
                sep = ", ";
            }
            resultBuilder.append("]");
            result = resultBuilder.toString();
        }

        return result;
    }

    public static String writeObject(Object obj) {
        StringBuilder result = new StringBuilder();

        String sep = "";
        for (Field field : obj.getClass().getDeclaredFields()) {
            result.append(sep).append(field.getName()).append(": ");

            Object fieldVal;
            try {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                fieldVal = field.get(obj);
                field.setAccessible(isAccessible);
            } catch (IllegalAccessException e) {
                fieldVal = null;
            }

            result.append(fieldVal);
            sep = ", ";
        }

        return result.toString();
    }
}
