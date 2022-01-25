
/**
 * 
 * GitHub Repository: https://github.com/jonathanschlitt/Java-RBT
 * 
 */

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.concurrent.ThreadLocalRandom;
// import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] arr = generateRandomNumbers();

        System.out.println(Arrays.toString(arr));

        ArrayList<Node> nodes = new ArrayList<>();

        // create RBT

        RBT rbt = new RBT();

        Node TNULL = rbt.TNULL;

        for (int i = 0; i < arr.length; i++) {
            // System.out.println("Inserting Node " + i + " with value " + arr[i]);

            Node n = new Node(arr[i], null, null, null, 1);
            nodes.add(n);
            rbt.rbInsert(n);

            generateOutput(nodes, TNULL, i);
        }

    }

    public static void generateOutput(ArrayList<Node> nodes, Node TNULL, int step) {
        String begin = "digraph G {" + "\n" +
                "graph [ratio=.48; ordering=\"out\"];" + "\n" +
                "node [style=filled, color=black, shape=circle, width=.6" + "\n" +
                "fontname=Helvetica, fontweight=bold, fontcolor=white," + "\n" +
                "fontsize=24, fixedsize=true];" + "\n";

        String redStyling = "[fillcolor=red];";
        String nilStyling = "[label=\"NIL\", shape=record, width=.4,height=.25, fontsize=16];";

        String end = "}";

        // getting red Nodes

        ArrayList<Node> redNodes = new ArrayList<>();

        for (Node n : nodes) {
            if (n.color == 1) {
                redNodes.add(n);
            }
        }

        System.out.println(redNodes.size());

        // generating String for red Nodes

        String red = "";
        for (Node n : redNodes) {

            red += n.key + ", ";

        }

        if (red.length() > 2) {
            red += red.substring(0, red.length() - 2) + " " + redStyling;
        }

        // System.out.println(red);

        // counting nils
        int count = 0;
        for (Node n : nodes) {
            if (n.left == TNULL) {
                count++;
            }

            if (n.right == TNULL) {
                count++;
            }
        }

        // System.out.println(count);

        // generating leafes for output
        String leafes = "";

        // System.out.println("Counter: " + count);

        for (int i = 1; i <= count; i++) {
            if (i == count) {
                leafes += "n" + i;
            } else {
                leafes += "n" + i + ", ";
            }
        }

        leafes += "\n " + nilStyling;

        // System.out.println(leafes);

        String relations = generateRelations(nodes, TNULL);

        String resultForConsole = "\n\n" + begin + "\n\n" + red + "\n" + leafes + "\n\n" + relations + "\n" + end
                + "\n";

        String resultForFile = begin + "\n" + red + "\n" + leafes + "\n\n" + relations + "\n" + end;

        // displaying output on the console

        System.out.println(resultForConsole);

        // Writing results into dotfiles
        if (step < 9) {
            try (PrintWriter out = new PrintWriter("rbt_step_0" + (step + 1) + ".dot")) {
                out.println(resultForFile);
            } catch (Exception e) {
                System.out.println("Error writing file!");
            }
        } else {
            try (PrintWriter out = new PrintWriter("rbt_step_" + (step + 1) + ".dot")) {
                out.println(resultForFile);
            } catch (Exception e) {
                System.out.println("Error writing file!");
            }
        }

    }

    public static String generateRelations(ArrayList<Node> nodes, Node TNULL) {
        int countLeafes = 1;
        String result = "";

        for (Node n : nodes) {
            // System.out.println(n.left.key);

            if (n.left.key < n.right.key) {
                // relations to Nil Leafes
                if (n.left == TNULL && n.right == TNULL) {
                    // System.out.println(countLeafes);
                    result += n.key + " -> " + "n" + countLeafes + ", " + "n" + ((int) countLeafes + 1);
                    // System.out.println(countLeafes);
                    result += ";\n";
                    countLeafes += 2;
                } else {
                    if (n.left == TNULL) {
                        result += n.key + " -> " + "n" + countLeafes;
                        result += ";\n";
                        countLeafes += 1;
                    }

                    if (n.right == TNULL) {
                        result += n.key + " -> " + "n" + countLeafes;
                        result += ";\n";
                        countLeafes += 1;
                    }
                }

                // relations to nodes
                if (n.left != TNULL && n.right != TNULL) {

                    result += n.key + " -> " + n.left.key + ", " + n.right.key;
                    result += ";\n";
                } else {
                    if (n.left != TNULL) {
                        result += n.key + " -> " + n.left.key;
                        result += ";\n";
                    }

                    if (n.right != TNULL) {
                        result += n.key + " -> " + n.right.key;
                        result += ";\n";
                    }
                }
            } else {
                // relations to nodes
                if (n.left != TNULL && n.right != TNULL) {

                    result += n.key + " -> " + n.left.key + ", " + n.right.key;
                    result += ";\n";
                } else {
                    if (n.left != TNULL) {
                        result += n.key + " -> " + n.left.key;
                        result += ";\n";
                    }

                    if (n.right != TNULL) {
                        result += n.key + " -> " + n.right.key;
                        result += ";\n";
                    }
                }

                // relations to Nil Leafes
                if (n.left == TNULL && n.right == TNULL) {
                    // System.out.println(countLeafes);
                    result += n.key + " -> " + "n" + countLeafes + ", " + "n" + ((int) countLeafes + 1);
                    // System.out.println(countLeafes);
                    result += ";\n";
                    countLeafes += 2;
                } else {
                    if (n.left == TNULL) {
                        result += n.key + " -> " + "n" + countLeafes;
                        result += ";\n";
                        countLeafes += 1;
                    }

                    if (n.right == TNULL) {
                        result += n.key + " -> " + "n" + countLeafes;
                        result += ";\n";
                        countLeafes += 1;
                    }
                }

            }

        }
        return result;

    }

    public static int[] generateRandomNumbers() {

        // int[] arr = { 22, 1, 5, 9, 36, 4, 88, 73, 99, 96, 100, 49, 67, 55, 17 };

        // int[] arr = { 60, 44, 49, 17, 96, 22, 19, 46, 41, 54, 61, 11, 90, 48, 34 };

        // Random random = new Random();

        // int[] arr = random.ints(15, 1, 100).toArray();

        // int[] arr = IntStream.range(0, 15).map(i ->
        // ThreadLocalRandom.current().nextInt(0, 100)).toArray();

        int max = 100;
        int min = 1;
        int range = max - min + 1;

        int[] arr = new int[15];
        boolean isSame = false;
        for (int i = 0; i < 15; i++) {
            isSame = true;
            while (isSame == true) {
                isSame = false;
                arr[i] = (int) (Math.random() * range) + min;
                for (int j = 0; j < i; j++) {
                    if (arr[i] == arr[j]) {
                        isSame = true;
                        break;
                    }
                }
            }
        }

        return arr;
    }
}
