package algorithm;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.Base;

/**
 * @author Eric
 * Date 2017/5/4 10:11
 * Desc
 */
public class BinarySearchTree extends Base {

    private static Node<Integer, Object> root;

    private BinarySearchTree() {
        root = null;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        try {
            // insert
            binarySearchTree.insert(BinarySearchTree.root, new Node<>(12));
            for (int i = 0; i < 20; i++) {
                int key = (int) (Math.random() * 30);
                System.out.println(key);
                binarySearchTree.insert(BinarySearchTree.root, new Node<>(key));
                // binarySearchTree.insert(key);
            }
            binarySearchTree.insert(BinarySearchTree.root, new Node<>(8));
            System.out.println(OBJECT_MAPPER.writeValueAsString(BinarySearchTree.root));

            // search
            // Node<Integer, Object> node = binarySearchTree.search(BinarySearchTree.root, 1);
            // System.out.println(OBJECT_MAPPER.writeValueAsString(node));

            // search min'est node
            // Node<Integer, Object> minNode = binarySearchTree.minNode(BinarySearchTree.root);
            // System.out.println(OBJECT_MAPPER.writeValueAsString(minNode));

            // delete min
            // binarySearchTree.delMin();
            // System.out.println("root:" + OBJECT_MAPPER.writeValueAsString(BinarySearchTree.root));

            // binarySearchTree.delete(8);
            // System.out.println(OBJECT_MAPPER.writeValueAsString(BinarySearchTree.root));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private Node insert(Node<Integer, Object> root, Node<Integer, Object> node) {
        if (node == null) return null;
        if (BinarySearchTree.root == null) {
            BinarySearchTree.root = node;
            return node;
        }
        int res = node.key - root.key;
        if (res == 0) return null;
        if (res > 0) {
            if (root.right == null) {
                root.right = node;
                return root;
            } else {
                return insert(root.right, node);
            }
        } else {
            if (root.left == null) {
                root.left = node;
                return root;
            } else {
                return insert(root.left, node);
            }
        }
    }

    private Node<Integer, Object> search(Node<Integer, Object> root, int key) {
        if (root == null) return null;
        int res = key - root.key;
        if (res == 0) return root;
        if (res > 0) {
            return search(root.right, key);
        } else {
            return search(root.left, key);
        }
    }

    private Node<Integer, Object> minNode(Node<Integer, Object> root) {
        if (root == null) return null;
        if (root.left == null) {
            return root;
        }
        return minNode(root.left);
    }

    private void delMin() {
        BinarySearchTree.root = delMin(BinarySearchTree.root);
    }
    private Node<Integer, Object> delMin(Node<Integer, Object> node) {
        if (node.left == null) return node.right;
        node.left = delMin(node.left);
        return node;
    }

    private void delete(int key) {
        BinarySearchTree.root = delete(BinarySearchTree.root, key);
    }
    private Node<Integer, Object> delete(Node<Integer, Object> node, int key) {
        if (node == null) return null;
        if (key == node.key) {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                /*Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;*/
                Node<Integer, Object> minNode = minNode(node);
                Node<Integer, Object> temp = delMin(node);
                minNode.left = temp.left;
                minNode.right = temp.right;
                return minNode;
            }
        } else if (key > node.key) {
            node.right = delete(node.right, key);
            return node;
        } else if (key < node.key) {
            node.left = delete(node.left, key);
            return node;
        }

        return node;
    }


    /**
     * 节点
     * @param <K>
     * @param <V>
     */
    static class Node<K extends V, V> {
        K key;
        V val;
        Node left;
        Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }
        public Node(K key) {
            this.key = key;
            this.val = key;
            this.left = null;
            this.right = null;
        }

        public K getKey() {
            return key;
        }
        public void setKey(K key) {
            this.key = key;
        }
        public V getVal() {
            return val;
        }
        public void setVal(V val) {
            this.val = val;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
    }
}
