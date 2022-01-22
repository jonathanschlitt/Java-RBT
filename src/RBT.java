
class Node {
  int key;
  Node left;
  Node right;
  Node parent;
  int color;

  public Node(int key, Object left, Object right, Object parent, int color) {
    this.key = key;
    this.left = (Node) left;
    this.right = (Node) right;
    this.parent = (Node) parent;
    this.color = color;
  }

  public Node(Node left, Node right, int color) {
    this.left = left;
    this.right = right;
    this.color = color;
  }
}

public class RBT {
  Node root;
  Node TNULL;

  // public RBT(Node root) {
  // this.root = root;
  // }

  public RBT() {
    this.TNULL = new Node(null, null, 0);
    // this.TNULL.color = 0;
    // this.TNULL.left = null;
    // this.TNULL.right = null;
    this.root = TNULL;
  }

  // Node n1 = new Node(< Node Value >, null, null, null, 1);

  void rbInsert(Node z) {

    // Node node = new Node();
    // node.parent = null;
    // node.data = key;
    z.left = this.TNULL;
    z.right = this.TNULL;
    // node.color = 1;

    Node y = null;
    Node x = this.root;

    while (x != TNULL) {
      y = x;
      if (z.key < x.key) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    z.parent = y;
    if (y == null) {
      this.root = z;
    } else if (z.key < y.key) {
      y.left = z;
    } else {
      y.right = z;
    }

    if (z.parent == null) {
      z.color = 0;
      return;
    }

    if (z.parent.parent == null) {
      return;
    }

    // z.left = null;
    // z.right = null;

    z.color = 1;
    // System.out.println(z.parent.color);
    rbInsertFixup(z);
  }

  void rbInsertFixup(Node z) {
    Node y;
    while (z.parent.color == 1) {
      if (z.parent == z.parent.parent.left) {
        y = z.parent.parent.right;

        if (y.color == 1) {
          y.color = 0;
          z.parent.color = 0;
          z.parent.parent.color = 1;
          z = z.parent.parent;
        } else {
          if (z == z.parent.right) {
            z = z.parent;
            leftRotate(z);
          }
          z.parent.color = 0;
          z.parent.parent.color = 1;
          rightRotate(z.parent.parent);
        }

      } else {
        y = z.parent.parent.left;

        if (y.color == 1) {
          y.color = 0;
          z.parent.color = 0;
          z.parent.parent.color = 1;
          z = z.parent.parent;
        } else {
          if (z == z.parent.left) {
            z = z.parent;
            rightRotate(z);
          }
          z.parent.color = 0;
          z.parent.parent.color = 1;
          leftRotate(z.parent.parent);
        }
      }

      if (z == this.root) {
        break;
      }
    }

    this.root.color = 0;
  }

  public void leftRotate(Node x) {
    Node y = x.right;
    x.right = y.left;

    if (y.left != this.TNULL) {
      y.left.parent = x;
    }
    y.parent = x.parent;

    if (x.parent == null) {
      this.root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }

    y.left = x;
    x.parent = y;
  }

  public void rightRotate(Node x) {
    Node y = x.left;
    x.left = y.right;

    if (y.right != this.TNULL) {
      y.right.parent = x;
    }
    y.parent = x.parent;

    if (x.parent == null) {
      this.root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }

    y.right = x;
    x.parent = y;
  }
}
