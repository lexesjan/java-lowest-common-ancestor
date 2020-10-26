import java.util.HashSet;
import java.util.Set;

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

  private int lowestCommonAncestorHelper(
      Node root, Node node1, Node node2, Set<Node> visited, Node[] result) {
    if (root == null) return 0;
    visited.add(root);
    boolean rootIsEitherNode = root.value == node1.value || root.value == node2.value;
    int numberOfInputNodesFound = rootIsEitherNode ? 1 : 0;
    if (root.children != null) {
      for (Node child : root.children) {
        numberOfInputNodesFound += lowestCommonAncestorHelper(child, node1, node2, visited, result);
      }
    }
    if (numberOfInputNodesFound >= 2 && result[0] == null) result[0] = root;
    return numberOfInputNodesFound;
  }

  public Node lowestCommonAncestor(Node node1, Node node2) {
    if (node1 == null || node2 == null) return null;
    Node[] result = new Node[1];
    Set<Node> visited = new HashSet<>();
    lowestCommonAncestorHelper(this.root, node1, node2, visited, result);
    if (!visited.contains(node1) || !visited.contains(node2)) return null;
    if (visited.contains(node1) && node1.value == node2.value) return node1;
    return result[0];
  }

  @Override
  public String toString() {
    if (root == null) return null;
    return root.toString();
  }
}
