package temp;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * common-lang3的datautil工具的使用
 * @author liuhaiyan
 * @date 2019-12-17 19:14
 */
public class DataUtilDemo {
    public static void main(String[] args) {
        Date now = new Date();

        Date time = DateUtils.truncate(now, Calendar.DAY_OF_MONTH);
        Date year = DateUtils.truncate(now, Calendar.YEAR);
        Date hour = DateUtils.truncate(now, Calendar.HOUR);
        Date minute  = DateUtils.truncate(now, Calendar.MINUTE);

        System.out.println("now is " + now + "time is " + time);
        System.out.println("year is " + year + "\n hour is " + hour + "\n minute is " + minute);
    }
}
