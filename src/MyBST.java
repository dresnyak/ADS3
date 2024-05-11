import java.util.function.Consumer;

public class MyBST<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public MyBST() {
        this.root = null;
        size = 0;
    }

    public void put(T value) {
        putRecursive(root, value);
    }

    private Node<T> putRecursive(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = putRecursive(node.left, value);
        } else {
            node.right = putRecursive(node.right, value);
        }

        return node;
    }
    public T get(T key) {
        Node<T> current = root;
        while (current != null) {
            int comparison = key.compareTo(current.value);
            if (comparison == 0) {
                return current.value;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }
    public void delete(T key) {
        root = deleteRecursive(root, key);
    }

    private Node<T> deleteRecursive(Node<T> node, T key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.value) == 0) {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node<T> smallestRightNode = node.right;
            smallestRightNode.left = deleteRecursive(smallestRightNode.left, smallestRightNode.value);
            smallestRightNode.right = node.right;
            return smallestRightNode;
        }

        if (key.compareTo(node.value) < 0) {
            node.left = deleteRecursive(node.left, key);
        } else {
            node.right = deleteRecursive(node.right, key);
        }

        return node;
    }

    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    public int size() {
        return size;
    }
}