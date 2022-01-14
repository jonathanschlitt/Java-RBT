import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(generateRandomNumbers()));
    }

    public static int[] generateRandomNumbers() {
        Random random = new Random();

        int[] arr = random.ints(15, 1, 100).toArray();

        return arr;
    }
}
