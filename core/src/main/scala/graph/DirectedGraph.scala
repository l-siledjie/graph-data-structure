package graph

case class DirectedGraph[V](adjList: Map[V, Set[(V, Int)]]) extends Graph[V] {

  def vertices: Set[V] =
    adjList.keySet ++ adjList.values.flatten.map(_._1)

  def edges: Set[Edge[V]] =
    adjList.flatMap { case (from, neighbors) =>
      neighbors.map { case (to, weight) => Edge(from, to, weight) }
    }.toSet

  def neighbors(vertex: V): Set[(V, Int)] =
    adjList.getOrElse(vertex, Set.empty)

  def addEdge(edge: Edge[V]): DirectedGraph[V] = {
    val updated = adjList.updatedWith(edge.from) {
      case Some(neighs) => Some(neighs + ((edge.to, edge.weight)))
      case None         => Some(Set((edge.to, edge.weight)))
    }
    copy(adjList = updated)
  }

  def removeEdge(from: V, to: V): DirectedGraph[V] = {
    val updated = adjList.updatedWith(from) {
      case Some(neighs) => Some(neighs.filterNot(_._1 == to))
      case None         => None
    }
    copy(adjList = updated)
  }

  // ✅ Implémentation requise par le trait Graph[V]
  override def removeEdge(edge: Edge[V]): DirectedGraph[V] =
    removeEdge(edge.from, edge.to)
}