import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BinaryTreeTest {
  // ~ Constructor ........................................................
  @Test
  public void testConstructor() {
    new BinaryTree(new TreeNode(0));
  }

  // ~ Parameters .........................................................
  @Parameters(name = "{index}: root = {1}, node1 = {2}, node2 = {3}, lca({2}, {3}) = {4}")
  public static Collection<Object[]> data() {
    /*
           0
          / \
         1   2
        / \
       3   4
            \
             5
    */
    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(1);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.left.right.right = new TreeNode(5);

    return Arrays.asList(
        new Object[][] {
          // null cases
          {"LCA should be null", new BinaryTree(null), null, null, null},
          // base cases
          {"LCA should be 0", new BinaryTree(root), root.left, root.right, root},
          {"LCA should be 0", new BinaryTree(root), root.left.left, root.right, root},
          {"LCA should be 0", new BinaryTree(root), root.left.right.right, root.right, root},
          {
            "LCA should be 1",
            new BinaryTree(root),
            root.left.left,
            root.left.right.right,
            root.left
          },
          {"LCA should be 1", new BinaryTree(root), root.left.left, root.left.right, root.left},
          // lca is one of the input nodes cases
          {"LCA should be 0", new BinaryTree(root), root.left, root, root},
          {"LCA should be 1", new BinaryTree(root), root.left.left, root.left, root.left},
          {"LCA should be 1", new BinaryTree(root), root.left, root.left.right, root.left},
          // lca is both of the input nodes cases
          {"LCA should be 0", new BinaryTree(root), root, root, root},
          {"LCA should be 3", new BinaryTree(root), root.left.left, root.left.left, root.left.left},
          // node is not in the graph case
          {"LCA should be null", new BinaryTree(root), root.left.left, new TreeNode(6), null},
          {"LCA should be null", new BinaryTree(root), new TreeNode(6), new TreeNode(6), null},
          {"LCA should be null", new BinaryTree(root), root.left.left, null, null},
        });
  }

  private final String message;
  private final BinaryTree binaryTree;
  private final TreeNode node1;
  private final TreeNode node2;
  private final TreeNode expectedOutput;

  public BinaryTreeTest(
      String message,
      BinaryTree binaryTree,
      TreeNode node1,
      TreeNode node2,
      TreeNode expectedOutput) {
    this.message = message;
    this.binaryTree = binaryTree;
    this.node1 = node1;
    this.node2 = node2;
    this.expectedOutput = expectedOutput;
  }

  // ~ Public Methods ......................................................
  @Test
  public void testLowestCommonAncestor() {
    TreeNode lca = binaryTree.lowestCommonAncestor(node1, node2);
    assertEquals(message, expectedOutput, lca);
  }
}
