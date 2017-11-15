package com.fintech.intellinews.util;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author waynechu
 * Created 2017-10-25 11:43
 */
public class RegexUtil {

    private RegexUtil() {
    }

    private static final String REGEX_EMAIL = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String REGEX_MOBILE = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])[0-9]{8}$";
    private static final String REGEX_STARTWITH = "^[a-z]";

    private static Pattern patternEmail = Pattern.compile(REGEX_EMAIL);

    public static boolean matchEmail(String str) {
        return patternEmail.matcher(str).matches();
    }

    private static Pattern patternMobile = Pattern.compile(REGEX_MOBILE);

    public static boolean matchMobile(String str) {
        return patternMobile.matcher(str).matches();
    }

    private static Pattern patternStartWith = Pattern.compile(REGEX_STARTWITH);

    public static boolean matchStartWith(String str) {
        return patternStartWith.matcher(str).matches();
    }

    public static boolean match(String pattern, String str) {
        return Pattern.compile(pattern).matcher(str).matches();
    }

    public static String replacePattern(String str, Function<String, String> function) {
        Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, function.apply(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
