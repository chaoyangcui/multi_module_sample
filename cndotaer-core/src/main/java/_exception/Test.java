package _exception;

import com.google.common.base.Throwables;

/**
 * Created by cuiguiyang on 2017/4/25 20:58.
 * Desc
 */
public class Test {

    public static void main(String[] args) {
        try {
            System.out.println();
            throw new IndexOutOfBoundsException("index out of bounds.");
        } catch (Exception e) {
            // e.printStackTrace();
            String errorMsg = Throwables.getStackTraceAsString(e);
            System.out.println(errorMsg);
        }
    }

}
