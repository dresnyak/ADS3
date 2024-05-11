import java.util.Random;

public class TestingMyHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(100);

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(10000);
            String name = "Name" + random.nextInt(10000);
            table.put(new MyTestingClass(name, id), i);
        }
        table.BucketSizes();
    }

}
