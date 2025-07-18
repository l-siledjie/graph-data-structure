import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GraphSpec extends AnyFlatSpec with Matchers {

  "A DirectedGraph" should "allow adding and retrieving vertices" in {
    val graph = new DirectedGraph[Int]()
    val updatedGraph = graph.addEdge(Edge(1, 2, 1)) // Add edge to create vertices
    updatedGraph.vertices should contain(1)
    updatedGraph.vertices should contain(2)
  }

  it should "allow adding edges between vertices" in {
    val graph = new DirectedGraph[Int]()
    val updatedGraph = graph.addEdge(Edge(1, 2, 1))
    updatedGraph.edges should contain(Edge(1, 2, 1))
  }

  it should "allow removing edges" in {
    val graph = new DirectedGraph[Int]()
    val updatedGraph = graph.addEdge(Edge(1, 2, 1))
    val graphAfterRemoval = updatedGraph.removeEdge(Edge(1, 2, 1))
    graphAfterRemoval.edges should not contain (Edge(1, 2, 1))
  }

  it should "return neighbors of a vertex" in {
    val graph = new DirectedGraph[Int]()
    val updatedGraph = graph.addEdge(Edge(1, 2, 1))
    updatedGraph.neighbors(1) should contain((2, 1))
  }

  it should "correctly encode and decode to JSON" in {
    val graph = new DirectedGraph[Int]()
    val updatedGraph = graph.addEdge(Edge(1, 2, 1))
    val json = updatedGraph.toJson // Assume this method is implemented
    val decodedGraph = Graph.fromJson(json) // Assume this method is implemented
    decodedGraph.edges should equal(updatedGraph.edges)
  }
  it should "detect cycles correctly" in {
    val graph = new DirectedGraph[Int]()
    graph.addEdge(Edge(1, 2, 1))
    graph.addEdge(Edge(2, 1, 1)) // Create a cycle
    GraphOps.hasCycle(graph) should be(true)

    val acyclicGraph = new DirectedGraph[Int]()
    acyclicGraph.addEdge(Edge(1, 2, 1))
    GraphOps.hasCycle(acyclicGraph) should be(false)
  }

  it should "find the shortest path using Dijkstra's algorithm" in {
    val graph = new DirectedGraph[Int]()
    graph.addEdge(Edge(1, 2, 1))
    graph.addEdge(Edge(1, 3, 4))
    graph.addEdge(Edge(2, 3, 2))

    val distances = GraphOps.dijkstra(graph, 1)
    distances(2) should equal(1) // Shortest path from 1 to 2
    distances(3) should equal(3) // Shortest path from 1 to 3 (1 -> 2 -> 3)
  }
}
