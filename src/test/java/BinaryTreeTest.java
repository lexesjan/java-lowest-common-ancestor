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
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {{new BinaryTree(new TreeNode(0)), null, null, null}});
  }

  private final BinaryTree binaryTree;
  private final TreeNode node1;
  private final TreeNode node2;
  private final TreeNode expectedOutput;

  public BinaryTreeTest(
      BinaryTree binaryTree, TreeNode node1, TreeNode node2, TreeNode expectedOutput) {
    this.binaryTree = binaryTree;
    this.node1 = node1;
    this.node2 = node2;
    this.expectedOutput = expectedOutput;
  }

  // ~ Public Methods ......................................................
  @Test
  public void testLowestCommonAncestor() {
    TreeNode lca = binaryTree.lowestCommonAncestor(node1, node2);
    if (expectedOutput == null) assertNull("Least common ancestor should not be found", lca);
    else assertEquals("Should match expected output", lca.value, expectedOutput.value);
  }
}

