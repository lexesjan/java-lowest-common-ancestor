class TreeNode {
  int value;
  TreeNode left;
  TreeNode right;

  TreeNode(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}

public class BinaryTree {
  TreeNode root;

  public BinaryTree(TreeNode root) {
    this.root = root;
  }

  private boolean lowestCommonAncestorHelper(
      TreeNode root, TreeNode node1, TreeNode node2, TreeNode[] result) {
    if (root == null) return false;
    boolean rootIsEitherNode = root.value == node1.value || root.value == node2.value;
    boolean left = lowestCommonAncestorHelper(root.left, node1, node2, result);
    boolean right = lowestCommonAncestorHelper(root.right, node1, node2, result);
    if (left && right || rootIsEitherNode && (left || right)) result[0] = root;
    return left || right || rootIsEitherNode;
  }

  public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
    if (node1 == null || node2 == null) return null;
    if (node1.value == node2.value) return node1;
    TreeNode[] result = new TreeNode[1];
    lowestCommonAncestorHelper(this.root, node1, node2, result);
    return result[0];
  }

  @Override
  public String toString() {
    if (root == null) return null;
    return root.toString();
  }
}
