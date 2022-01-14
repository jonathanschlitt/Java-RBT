class Node {
  int key;
  Node left;
  Node right;
  Node parent;
  Node root;
  boolean color = true;

  public Node(int key, Node root, Node left, Node right, Node parent) {
    this.key = key;
    this.left = left;
    this.right = right;
    this.parent = parent;
    this.root = root;
  }
}

class Tree {
  Node root;

  public Tree(Node root) {
    this.root = root;
  }

  // Node iterativeTreeSearch(Node x, int k) {
  // while (x != null && k != x.key) {
  // if (k < x.key) {
  // x = x.left;
  // } else {
  // x = x.right;
  // }
  // }
  // return x;
  // }

  void treeInsert(Tree t, Node z) {
    Node y = null;
    Node x = t.root;

    while (x != null) {
      y = x;
      if (z.key < x.key) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    z.parent = y;
    if (y == null) {
      t.root = z;
    } else if (z.key < y.key) {
      y.left = z;
    } else {
      y.right = z;
    }
  }
}

public class RBT {
  private Node root;

  public RBT(Node root) {
    this.root = root;
  }

  public void leftRotate(RBT t, Node x) {
    Node y = x.right;
    x.right = y.left;

    if (y.left != null) {
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

  public void rightRotate(RBT t, Node x) {
    Node y = x.left;
    x.left = y.right;
    if (y.right != null) {
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

  void rbInsert(RBT t, Node z) {
    Node y = null;
    Node x = t.root;

    while (x != null) {
      y = x;
      if (z.key < x.key) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    z.parent = y;
    if (y == null) {
      t.root = z;
    } else if (z.key < y.key) {
      y.left = z;
    } else {
      y.right = z;
    }

    z.left = null;
    z.right = null;

    z.color = true;
    rbInsertFixup(t, z);
  }

  void rbInsertFixup(RBT t, Node z) {
    while (z.parent.color == true) {
      if (z.parent == z.parent.parent.left) {
        Node y = z.parent.parent.right;

        if (y.color == true) {
          z.parent.parent.color = false;
          y.color = false;
          z.parent.parent.color = true;
          z = z.parent.parent;
        } else if (z == z.parent.right) {
          z = z.parent;
          leftRotate(t, z);
        }
        z.parent.color = false;
        z.parent.parent.color = true;

      } else {
        z = z.parent;
        rightRotate(t, z);
      }
    }

    t.root.color = false;
  }
}
