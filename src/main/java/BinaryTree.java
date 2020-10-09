class TreeNode {
  int value;
  TreeNode left;
  TreeNode right;

  TreeNode(int value) {
    this.value = value;
  }
}

public class BinaryTree {
  TreeNode root;

  private boolean nodeExists(TreeNode root, TreeNode node) {
    if (root == null) return false;
    if (root.value == node.value) return true;
    boolean left = nodeExists(root.left, node);
    boolean right = nodeExists(root.right, node);
    return left || right;
  }

  private TreeNode lowestCommonAncestorHelper(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null || root.value == node1.value || root.value == node2.value) return root;
    TreeNode left = lowestCommonAncestorHelper(root.left, node1, node2);
    TreeNode right = lowestCommonAncestorHelper(root.right, node1, node2);
    if (left != null && right != null) return root;
    return left != null ? left : right;
  }

  public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
    if (node1 == null || node2 == null || !nodeExists(root, node1) || !nodeExists(root, node2))
      return null;
    return lowestCommonAncestorHelper(this.root, node1, node2);
  }
}
