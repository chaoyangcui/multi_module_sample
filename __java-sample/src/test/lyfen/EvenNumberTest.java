import org.junit.Test;

/**
 * 奇偶数判断
 *
 * @author Eric Cui
 * @date 2017/12/27 16:25
 *
 */
public class EvenNumberTest {

    @Test
    public void test() {
        for (int i = 0; i < 50; i++) {
            if ((i & 1) == 0) {
                System.out.println(i);
            }
        }
    }

}
