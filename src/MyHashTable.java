import java.util.Objects;

public class MyHashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 11;

    HashNode<K, V>[] chainArray;
    private int size;
    private int M;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        int hash = 0;
        String name = ((MyTestingClass) key).getName();
        for (char c : name.toCharArray()) {
            hash = 31 * hash + (int) c;
        }
        return Math.abs(hash % M);
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];

        if (node == null) {
            chainArray[index] = new HashNode<>(key, value);
            size++;
        } else {
            while (node.next != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }

            node.next = new HashNode<>(key, value);
            size++;
        }
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> node = chainArray[index];

        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }

            prev = node;
            node = node.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }

        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                sb.append(node.key).append("=").append(node.value).append(", ");
                node = node.next;
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 2);
        }

        return sb.toString();
    }


    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public void BucketSizes() {
        for (int i = 0; i < chainArray.length; i++) {
            int count = 0;
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                count++;
            }
            System.out.println("Bucket " + i + " has " + count + " elements.");
        }
    }
}