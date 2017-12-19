import org.joda.time.DateTime;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/10/24 19:46
 * Desc  : 描述信息
 * @author cuiguiyang
 */
public class JoadUtil {

    public static void main(String[] args) {
        DateTime urrDateTime = DateTime.now();
        urrDateTime = urrDateTime.plusHours(3);
        DateTime.Property property= urrDateTime.hourOfDay();
        System.out.println(property.get());

        System.out.println(DateTime.now().getMillis() / 1000);
    }

}
