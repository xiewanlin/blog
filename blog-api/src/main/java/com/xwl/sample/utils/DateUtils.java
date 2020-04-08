package com.xwl.sample.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_HHMMSS = "HHmmss";

    /**
     * 日期字符转换
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String convertToString(Date date, String pattern) {
        if (date != null && !StringUtils.isEmpty(pattern)) {

            SimpleDateFormat sf = new SimpleDateFormat(pattern);

            return sf.format(date);
        }

        return null;
    }

}
