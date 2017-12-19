package date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/7 13:20
 * Desc    Setting | Editor | File and Code Templates
 */
public class DateTimeTest {

    // Thu Dec  7 00:23:07 EST 2017
    private static void privateMethod() {
        System.out.println("private method");
    }
    private DateTimeTest() {
    }

    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault());
        System.out.println(TimeZone.getTimeZone("EST"));
        System.out.println(TimeZone.getTimeZone("America"));

        DateTime dateTime = DateTime.now();
        dateTime = DateTime.parse("2017-12-07 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:SS"));

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:SS z");

        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        System.out.println(dateFormat.format(dateTime.toDate()));
        // dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        // System.out.println(dateFormat.format(dateTime.toDate()));
        // dateFormat.setTimeZone(TimeZone.getTimeZone("America/California"));
        // System.out.println(dateFormat.format(dateTime.toDate()));


        // System.out.println(DateTime.now(DateTimeZone.forTimeZone(TimeZone.getTimeZone("EST"))).toDate());
    }
}
