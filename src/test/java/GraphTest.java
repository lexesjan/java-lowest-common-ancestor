import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GraphTest {
  // ~ Constructor ........................................................
  @Test
  public void testConstructor() {
    new Graph(new Node(0));
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
    Node[] nodes0 = new Node[7];
    for (int i = 0; i < nodes0.length; i++) nodes0[i] = new Node(i);
    Node root0 = nodes0[0];
    root0.children = new Node[] {nodes0[1], nodes0[2]};
    nodes0[1].children = new Node[] {nodes0[3], nodes0[4]};
    nodes0[4].children = new Node[] {nodes0[5]};

    /*
           0 ---
         / | \  \
        1  |  2  5
         \ | /   |
           3     |
             \  /
              4
    */
    Node[] nodes1 = new Node[7];
    for (int i = 0; i < nodes1.length; i++) nodes1[i] = new Node(i);
    Node root1 = nodes1[0];
    root1.children = new Node[] {nodes1[1], nodes1[3], nodes1[2], nodes1[5]};
    nodes1[1].children = new Node[] {nodes1[3]};
    nodes1[2].children = new Node[] {nodes1[3]};
    nodes1[3].children = new Node[] {nodes1[4]};
    nodes1[5].children = new Node[] {nodes1[4]};

    return Arrays.asList(
        new Object[][] {
          // null cases
          // root0
          {"LCA should be null", new Graph(null), null, null, null},
          // base cases
          // root0
          {"LCA should be 0", new Graph(root0), nodes0[1], nodes0[2], root0},
          {"LCA should be 0", new Graph(root0), nodes0[3], nodes0[2], root0},
          {"LCA should be 0", new Graph(root0), nodes0[5], nodes0[2], root0},
          {"LCA should be 1", new Graph(root0), nodes0[3], nodes0[5], nodes0[1]},
          {"LCA should be 1", new Graph(root0), nodes0[3], nodes0[4], nodes0[1]},
          // root1
          {"LCA should be 0", new Graph(root1), nodes1[1], nodes1[2], nodes1[0]},
          {"LCA should be 0", new Graph(root1), nodes1[5], nodes1[1], nodes1[0]},
          {"LCA should be 0", new Graph(root1), nodes1[2], nodes1[5], nodes1[0]},
          // lca is one of the input nodes cases
          // root0
          {"LCA should be 0", new Graph(root0), nodes0[1], root0, root0},
          {"LCA should be 1", new Graph(root0), nodes0[3], nodes0[1], nodes0[1]},
          {"LCA should be 1", new Graph(root0), nodes0[1], nodes0[4], nodes0[1]},
          // root1
          {"LCA should be 1", new Graph(root1), nodes1[1], nodes1[4], nodes1[1]},
          {"LCA should be 3", new Graph(root1), nodes1[3], nodes1[4], nodes1[3]},
          {"LCA should be 1", new Graph(root1), nodes1[1], nodes1[3], nodes1[1]},
          {"LCA should be 3", new Graph(root1), nodes1[3], nodes1[4], nodes1[3]},
          {"LCA should be 2", new Graph(root1), nodes1[2], nodes1[3], nodes1[2]},
          {"LCA should be 2", new Graph(root1), nodes1[2], nodes1[4], nodes1[2]},
          // lca is both of the input nodes cases
          // root0
          {"LCA should be 0", new Graph(root0), root0, root0, root0},
          {"LCA should be 3", new Graph(root0), nodes0[3], nodes0[3], nodes0[3]},
          // root1
          {"LCA should be 4", new Graph(root1), nodes1[4], nodes1[4], nodes1[4]},
          {"LCA should be 3", new Graph(root1), nodes1[3], nodes1[4], nodes1[3]},
          {"LCA should be 5", new Graph(root1), nodes1[5], nodes1[5], nodes1[5]},
          // node is not in the graph case
          // root0
          {"LCA should be null", new Graph(root0), nodes0[3], nodes0[6], null},
          {"LCA should be null", new Graph(root0), nodes0[6], nodes0[6], null},
          {"LCA should be null", new Graph(root0), nodes0[3], null, null},
          // root1
          {"LCA should be null", new Graph(root1), nodes1[3], nodes1[6], null},
          {"LCA should be null", new Graph(root1), nodes1[6], nodes1[6], null},
          {"LCA should be null", new Graph(root1), nodes1[3], null, null},
        });
  }

  private final String message;
  private final Graph graph;
  private final Node node1;
  private final Node node2;
  private final Node expectedOutput;

  public GraphTest(String message, Graph graph, Node node1, Node node2, Node expectedOutput) {
    this.message = message;
    this.graph = graph;
    this.node1 = node1;
    this.node2 = node2;
    this.expectedOutput = expectedOutput;
  }

  // ~ Public Methods ......................................................
  @Test
  public void testLowestCommonAncestor() {
    Node lca = graph.lowestCommonAncestor(node1, node2);
    assertEquals(message, expectedOutput, lca);
  }
}
