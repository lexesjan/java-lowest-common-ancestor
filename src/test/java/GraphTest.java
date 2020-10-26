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
    Node[] nodes = new Node[7];
    for (int i = 0; i < nodes.length; i++) nodes[i] = new Node(i);
    Node root = nodes[0];
    root.children = new Node[] {nodes[1], nodes[2]};
    nodes[1].children = new Node[] {nodes[3], nodes[4]};
    nodes[4].children = new Node[] {null, nodes[5]};

    return Arrays.asList(
        new Object[][] {
          // null cases
          {"LCA should be null", new Graph(null), null, null, null},
          // base cases
          {"LCA should be 0", new Graph(root), nodes[1], nodes[2], root},
          {"LCA should be 0", new Graph(root), nodes[3], nodes[2], root},
          {"LCA should be 0", new Graph(root), nodes[5], nodes[2], root},
          {"LCA should be 1", new Graph(root), nodes[3], nodes[5], nodes[1]},
          {"LCA should be 1", new Graph(root), nodes[3], nodes[4], nodes[1]},
          // lca is one of the input nodes cases
          {"LCA should be 0", new Graph(root), nodes[1], root, root},
          {"LCA should be 1", new Graph(root), nodes[3], nodes[1], nodes[1]},
          {"LCA should be 1", new Graph(root), nodes[1], nodes[4], nodes[1]},
          // lca is both of the input nodes cases
          {"LCA should be 0", new Graph(root), root, root, root},
          {"LCA should be 3", new Graph(root), nodes[3], nodes[3], nodes[3]},
          // node is not in the graph case
          {"LCA should be null", new Graph(root), nodes[3], nodes[6], null},
          {"LCA should be null", new Graph(root), nodes[6], nodes[6], null},
          {"LCA should be null", new Graph(root), nodes[3], null, null},
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
