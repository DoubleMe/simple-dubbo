package com.chenhm.base.util;


/**
 * ReflectUtils
 *
 * @author qian.lei
 */
public final class ReflectUtils {

    /**
     * get name.
     * java.lang.Object[][].class => "java.lang.Object[][]"
     *
     * @param c class.
     * @return name.
     */
    public static String getName(Class<?> c) {
        if (c.isArray()) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append("[]");
                c = c.getComponentType();
            }
            while (c.isArray());

            return c.getName() + sb.toString();
        }
        return c.getName();
    }

}