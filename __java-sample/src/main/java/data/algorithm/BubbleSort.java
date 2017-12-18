package data.algorithm;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/13 11:58
 * Desc    Setting | Editor | File and Code Templates
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] iarr = {22, 34, 3, 32, 82, 55, 89, 50, 37, 5, 64, 35, 9, 70};
        System.out.println(Arrays.toString(iarr));
        bubbleSort2(iarr);
    }

    public static void bubbleSort(int[] iarr) {
        for (int i = 0; i < iarr.length - 1; i++) {
            for (int j = iarr.length - 1; j > i; j--) {
                if (iarr[j - 1] < iarr[j]) {
                    swap(iarr, j - 1, j);
                }
            }
            System.out.println(Arrays.toString(iarr));
        }
    }
    public static void bubbleSort2(int[] iarr) {
        for (int i = 0; i < iarr.length - 1; i++) {
            for (int j = i + 1; j < iarr.length; j++) {
                if (iarr[i] > iarr[j]) {
                    swap(iarr, i, j);
                }
            }
            System.out.println(Arrays.toString(iarr));
        }
    }

    public static void swap(int[] iarr, int a, int b) {
        int tmp;
        tmp = iarr[a];
        iarr[a] = iarr[b];
        iarr[b] = tmp;
    }

}
