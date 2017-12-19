package binary;

/**
 * Created by Intellij IDEA.
 * Date  : 2017/11/30 23:32
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class Binary {

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(Integer.toBinaryString(-5));
        printBinary(2);

    }

    private static void printBinary(int i) {
        System.out.println(
                String.format("%32s", Integer.toBinaryString(i))
                        .replace(" ", "0"));
    }

}
