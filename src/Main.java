import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(generateRandomNumbers()));
        Node n1 = new Node(100, null, null, null, null);

        Node n2 = new Node(101, null, null, null, null);

        Node n3 = new Node(99, null, null, null, null);

        Tree t1 = new Tree(n1);

        t1.treeInsert(t1, n2);

        t1.treeInsert(t1, n3);

        System.out.println(t1.root.left.parent.key);
    }

    public static int[] generateRandomNumbers() {
        Random random = new Random();

        int[] arr = random.ints(15, 1, 100).toArray();

        return arr;
    }
}
