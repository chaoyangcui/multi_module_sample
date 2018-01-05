package jvm;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/5 17:18
 * Description
 */
public class StringTest {

    public static void main(String[] args) {

        String s1 = "Good";
        s1 = s1 + "morning";
        System.out.println(s1 == s1.intern()); // Prints true for jdk7, false - for jdk6.

        /*String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);*/
    }

}
