package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:
 */
public class NovelSpiderUtil {
    private NovelSpiderUtil() {
    }

    public static Date getDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
