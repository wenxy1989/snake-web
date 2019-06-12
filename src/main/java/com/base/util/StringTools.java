package com.base.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class StringTools extends StringUtils {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_TO_DAY = "YEAR_DAY";
    public static final String YEAR_TO_MINUTE = "YEAR_MINUTE";
    public static final String YEAR_TO_SECOND = "YEAR_SECOND";

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }


    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static double getLength(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        //进位取整
        return Math.ceil(valueLength);
    }


    /**
     * convert string to boolean
     *
     * @param str String
     *            str yes or no
     * @return boolean true or false
     */
    public static boolean strToBoolean(String str) {
        if (str.equalsIgnoreCase("YES"))
            return true;
        return false;
    }

    /**
     * convert boolean to string
     *
     * @param bResult boolean  true or false
     * @return String yes or no
     */
    public static String booleanToString(boolean bResult) {
        if (bResult == true)
            return "YES";
        return "NO";
    }

    /**
     * str2DateConvert
     *
     * @param str String,String scope
     * @return date
     */
    public static Date str2DateConvert(String str, String scope)
            throws ParseException {
        String STR_FORMAT = null;
        Date date = null;
        if (str == null || str.trim().equals(""))
            return null;
        if (scope.equals(YEAR_TO_DAY))
            STR_FORMAT = YYYY_MM_DD;
        else if (scope.equals(YEAR_TO_MINUTE))
            STR_FORMAT = YYYY_MM_DD_HH_MM;
        else
            STR_FORMAT = YYYY_MM_DD_HH_MM_SS;
        SimpleDateFormat sdf = new SimpleDateFormat(STR_FORMAT);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            throw new ParseException(
                    "You should pass the String like this:2005-05-25 12:12", 1);
        }
        return date;
    }

    /**
     * str2DateConvert
     *
     * @param cal Calendar ,int minutes
     * @return date
     */
    public static Date makeDateFromCalAndMin(Calendar cal, int minutes)
            throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        cal.add(cal.DATE, +minutes / (24 * 60));
        cal.add(cal.HOUR, +minutes % (24 * 60) / 60);
        cal.add(cal.MINUTE, +minutes % (24 * 60) % 60);
        try {
            date = sdf.parse(sdf.format(cal.getTime()));
        } catch (Exception exp) {
            // do nothing
        }
        cal.add(cal.DATE, -minutes / (24 * 60));
        cal.add(cal.HOUR, -minutes % (24 * 60) / 60);
        cal.add(cal.MINUTE, -minutes % (24 * 60) % 60);
        return date;
    }

    /**
     * getTimeStr
     *
     * @param date
     * @return date
     */
    public static String getTimeStr(Date date) {
        String str = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            str = sdf.format(date);
        }
        return str;
    }

    /**
     * getTimeStr
     *
     * @param email
     * @return email
     */
    public static boolean isValidEmailAddress(String email) {
        boolean valid = false;
        if (email != null) {
            email = org.apache.commons.lang.StringUtils.trim(email);
            int atIndex = email.indexOf("@");
            int lastPointIndex = email.lastIndexOf(".");

            if (atIndex > 0 && (email.length() - lastPointIndex) > 1
                    && (lastPointIndex - atIndex) > 1)
                valid = true;
        }
        return valid;
    }

    /**
     * 验证字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String valueOf(Long l) {
        String str = "";
        if (null != l) {
            str = String.valueOf(l);
        }
        return str;
    }

    public static String valueOf(Integer i) {
        String str = "";
        if (null != i) {
            str = String.valueOf(i);
        }
        return str;
    }

    public static String valueOf(Double d) {
        String str = "";
        if (null != d) {
            str = String.valueOf(d);
        }
        return str;
    }

    public static String valueOf(String s) {
        if (null == s) {
            s = "";
        }
        return s;
    }

    public static String valueOf(String[] array) {
        String _value = "";
        if (null != array && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                String str = array[i];
                if (i != 0) {
                    _value += ",";
                }
                _value += str;
            }
        } else {
            _value = "";
        }
        return _value;
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    public static boolean notEmpty(String str) {
        return null != str && !"".equals(str.trim());
    }

    public static String columnToAttribute(String columnName) {
        StringBuffer sb = new StringBuffer();
        String[] words = columnName.split("_");
        for (int i = 0; i < words.length; i++) {
            String str = words[1];
            if (i > 0) {
                sb.append(str.substring(0, 1).toUpperCase());
                sb.append(str.substring(1));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String attributeToColumn(String attributeName) {
        String columnName = null;
        if (attributeName.indexOf("_") > -1) {
            columnName = attributeName.toLowerCase();
        } else {
            columnName = attributeName.toLowerCase() + "_";
        }
        return columnName;
    }

    public static String getFirstLarge(String str) {
        String large = "module";
        if (notEmpty(str)) {
            large = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return large;
    }

    public static String getFirstLittle(String str) {
        String little = "module";
        if (notEmpty(str)) {
            little = str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return little;
    }

    public static String parsePath(String template, String application) {
        return parsePath(template, application, "");
    }

    public static String parsePath(String template, String application, String model) {
        return template.replaceAll("\\@\\{large\\}", getFirstLarge(model))
                .replaceAll("\\@\\{little\\}", getFirstLittle(model))
                .replaceAll("\\@\\{app\\}", application);

    }
}