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
    Node root = new Node(0);
    root.children = new Node[2];
    root.children[0] = new Node(1);
    root.children[1] = new Node(2);
    root.children[0].children = new Node[2];
    root.children[0].children[0] = new Node(3);
    root.children[0].children[1] = new Node(4);
    root.children[0].children[1].children = new Node[2];
    root.children[0].children[1].children[1] = new Node(5);

    return Arrays.asList(
        new Object[][] {
          // null cases
          {"LCA should be null", new Graph(null), null, null, null},
          // base cases
          {"LCA should be 0", new Graph(root), root.children[0], root.children[1], root},
          {"LCA should be 0", new Graph(root), root.children[0].children[0], root.children[1], root},
          {"LCA should be 0", new Graph(root), root.children[0].children[1].children[1], root.children[1], root},
          {
            "LCA should be 1",
            new Graph(root),
            root.children[0].children[0],
            root.children[0].children[1].children[1],
            root.children[0]
          },
          {"LCA should be 1", new Graph(root), root.children[0].children[0], root.children[0].children[1], root.children[0]},
          // lca is one of the input nodes cases
          {"LCA should be 0", new Graph(root), root.children[0], root, root},
          {"LCA should be 1", new Graph(root), root.children[0].children[0], root.children[0], root.children[0]},
          {"LCA should be 1", new Graph(root), root.children[0], root.children[0].children[1], root.children[0]},
          // lca is both of the input nodes cases
          {"LCA should be 0", new Graph(root), root, root, root},
          {"LCA should be 3", new Graph(root), root.children[0].children[0], root.children[0].children[0], root.children[0].children[0]},
          // node is not in the graph case
          {"LCA should be null", new Graph(root), root.children[0].children[0], new Node(6), null},
          {"LCA should be null", new Graph(root), new Node(6), new Node(6), null},
          {"LCA should be null", new Graph(root), root.children[0].children[0], null, null},
        });
  }

  private final String message;
  private final Graph graph;
  private final Node node1;
  private final Node node2;
  private final Node expectedOutput;

  public GraphTest(
      String message,
      Graph graph,
      Node node1,
      Node node2,
      Node expectedOutput) {
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
