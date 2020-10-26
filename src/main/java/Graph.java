class Node {
  int value;
  Node[] children;

  Node(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}

public class Graph {
  Node root;

  public Graph(Node root) {
    this.root = root;
  }

  private boolean lowestCommonAncestorHelper(Node root, Node node1, Node node2, Node[] result) {
    if (root == null) return false;
    boolean rootIsEitherNode = root.value == node1.value || root.value == node2.value;
    boolean left = false;
    boolean right = false;
    if (root.children != null) {
      left = lowestCommonAncestorHelper(root.children[0], node1, node2, result);
      right = lowestCommonAncestorHelper(root.children[1], node1, node2, result);
    }
    if (left && right || rootIsEitherNode && (left || right)) result[0] = root;
    return left || right || rootIsEitherNode;
  }

  public Node lowestCommonAncestor(Node node1, Node node2) {
    if (node1 == null || node2 == null) return null;
    Node[] result = new Node[1];
    boolean inputNodeExists = lowestCommonAncestorHelper(this.root, node1, node2, result);
    if (inputNodeExists && node1.value == node2.value) return node1;
    return result[0];
  }

  @Override
  public String toString() {
    if (root == null) return null;
    return root.toString();
  }
}
