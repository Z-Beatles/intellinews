package com.fintech.intellinews.util;

/**
 * @author waynechu
 * Created 2017-11-11 22:58
 */
public class StringUtil {

    public static String spiltString(String source){
        int length = source.length();
        String[] temp = new String[length];

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%");
        for (int i = 0; i < length; i++) {
            temp[i] = source.substring(i, i + 1);
            stringBuilder.append(temp[i]);
            stringBuilder.append("%");
        }
        return stringBuilder.toString();
    }
}
