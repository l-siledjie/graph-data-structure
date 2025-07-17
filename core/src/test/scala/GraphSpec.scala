import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GraphSpec extends AnyFlatSpec with Matchers {

  "A DirectedGraph" should "allow adding and retrieving vertices" in {
    val graph = new DirectedGraph[Int]
    graph.addVertex(1)
    graph.getVertices should contain (1)
  }

  it should "allow adding edges between vertices" in {
    val graph = new DirectedGraph[Int]
    graph.addVertex(1)
    graph.addVertex(2)
    graph.addEdge(1, 2)
    graph.getEdges should contain ((1, 2))
  }

  it should "allow removing edges" in {
    val graph = new DirectedGraph[Int]
    graph.addVertex(1)
    graph.addVertex(2)
    graph.addEdge(1, 2)
    graph.removeEdge(1, 2)
    graph.getEdges should not contain ((1, 2))
  }

  it should "return neighbors of a vertex" in {
    val graph = new DirectedGraph[Int]
    val graph = new DirectedGraph[Int]()
    val updatedGraph = graph.addEdge(Edge(1, 2, 1))
    updatedGraph.neighbors(1) should contain((2, 1))
  }

  it should "correctly encode and decode to JSON" in {
    val graph = new DirectedGraph[Int]
    graph.addVertex(1)
    graph.addVertex(2)
    graph.addEdge(1, 2)
    val json = graph.toJson // Assume this method is implemented
    val decodedGraph = Graph.fromJson(json) // Assume this method is implemented
    decodedGraph.getEdges should equal (graph.getEdges)
  }

 


  
}