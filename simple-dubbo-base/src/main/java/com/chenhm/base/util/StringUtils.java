package com.chenhm.base.util;

import java.util.List;

/**
 * @author chen-hongmin
 * @since 2018/1/15 10:49
 */
public class StringUtils {

    public static String join(List<String> target,String split){
        if (target == null || target.isEmpty()){
            return "";
        }

        String mSplit = "";
        StringBuilder sb = new StringBuilder();

        for (String str : target){
            sb.append(mSplit).append(str);
            mSplit = split;
        }

        return sb.toString();
    }

    public static boolean isBlank(String value){

        if (value == null || value.length() == 0){
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String value){


        return !isBlank(value);
    }
}
