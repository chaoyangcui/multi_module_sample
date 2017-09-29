package data.structure;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/29 10:39
 * Desc    Setting | Editor | File and Code Templates
 */
public class BinaryTreeExample {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);
        System.out.println(root.val);

        TreeNode<Integer> tree = root.convertToTree(new Integer[]{1, null, 2, 3, 4,5,6,7,8}, 0);
        System.out.println(tree);
    }


    static class TreeNode<T> {
        T val;
        TreeNode left;
        TreeNode right;

        TreeNode(T t) {
            this.val = t;
        }

        public TreeNode<T> convertToTree(T[] ts, int i) {
            if (ts == null || ts.length == 0 || i >= ts.length) {
                return null;
            }
            TreeNode<T> node = new TreeNode<>(ts[i]);
            if (node.val == null) return null;
            if (i >= 1 && ts[i - 1] == null) i = i - 1;
            int left = (2 * i) + 1;
            node.left = convertToTree(ts, left);
            int right = left + 1;
            node.right = convertToTree(ts, right);
            return node;
        }

        public TreeNode<T> convert2Tree(T[] array) {

            TreeNode<T> root = new TreeNode<>(array[0]);
            for (int i = 1; i < array.length; i++) {
                TreeNode<T> left = new TreeNode<>(array[i]);
            }

            return null;
        }
    }
}
