
// import java.util.Arrays;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
        }

        // System.out.println(nodes.toString());

        generateOutput(nodes, TNULL);

        // randomize(0);

    }

    public static void generateOutput(ArrayList<Node> nodes, Node TNULL) {
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

        // System.out.println(redNodes.size());

        // generating String for red Nodes

        String red = "";
        for (Node n : redNodes) {

            red += n.key + ", ";

        }

        red += red.substring(0, red.length() - 2) + " " + redStyling;

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

        System.out.println("Counter: " + count);

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

        String result = "\n\n" + begin + "\n\n" + red + "\n" + leafes + "\n\n" + relations + "\n" + end + "\n";

        System.out.println(result);

        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(result);
        } catch (Exception e) {
            System.out.println("Error writing file!");
        }

    }

    public static String generateRelations(ArrayList<Node> nodes, Node TNULL) {
        int countLeafes = 1;
        String result = "";

        for (Node n : nodes) {
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
            } else if (n.left == TNULL) {
                result += n.key + " -> " + "n" + countLeafes;
                result += ";\n";
                countLeafes += 1;
            } else if (n.right == TNULL) {
                result += n.key + " -> " + "n" + countLeafes;
                result += ";\n";
                countLeafes += 1;
            }

        }
        return result;

    }

    public static int[] generateRandomNumbers() {
        // int max = 100;
        // int min = 1;
        // int range = max - min + 1;
        // int rand = (int)(Math.random() * range) + min;

        int[] arr = { 22, 1, 5, 9, 36, 4, 88, 73, 99, 96, 100, 49, 67, 55, 17 };

        // Random random = new Random();

        // int[] arr = random.ints(15, 1, 100).toArray();

        return arr;
    }
}

// Node n1 = new Node(100, null, null, null, null);

// Node n2 = new Node(101, null, null, null, null);

// Node n3 = new Node(99, null, null, null, null);

// Tree t1 = new Tree(n1);

// t1.treeInsert(t1, n2);

// t1.treeInsert(t1, n3);

// System.out.println(t1.root.left.parent.key);

// Node n1 = new Node(1, null, null, null);

// Node n2 = new Node(0, null, null, null);

// Node n3 = new Node(3, null, null, null);

// Node n4 = new Node(4, null, null, null);

// Node n5 = new Node(5, null, null, null);

// Node n6 = new Node(6, null, null, null);

// rbt.rbInsert(n1);
// rbt.rbInsert(n2);
// rbt.rbInsert(n3);
// rbt.rbInsert(n4);
// rbt.rbInsert(n5);
// rbt.rbInsert(n6);

// System.out.println(rbt.root.key);
// System.out.println(rbt.root.left.left.key);
// System.out.println(rbt.root.left.parent.key);
