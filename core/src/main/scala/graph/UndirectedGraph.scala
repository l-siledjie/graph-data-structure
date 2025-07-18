package graph

case class UndirectedGraph[V](adjList: Map[V, Set[(V, Int)]]) extends Graph[V] {

  def vertices: Set[V] =
    adjList.keySet ++ adjList.values.flatten.map(_._1)

  def edges: Set[Edge[V]] =
    adjList.flatMap { case (v, neighbors) =>
      neighbors.collect {
        case (n, w) if v.hashCode() <= n.hashCode() => Edge(v, n, w)
      }
    }.toSet

  def neighbors(vertex: V): Set[(V, Int)] =
    adjList.getOrElse(vertex, Set.empty)

  def addEdge(edge: Edge[V]): UndirectedGraph[V] = {
    val updated1 = adjList.updatedWith(edge.from) {
      case Some(neighs) => Some(neighs + ((edge.to, edge.weight)))
      case None         => Some(Set((edge.to, edge.weight)))
    }
    val updated2 = updated1.updatedWith(edge.to) {
      case Some(neighs) => Some(neighs + ((edge.from, edge.weight)))
      case None         => Some(Set((edge.from, edge.weight)))
    }
    copy(adjList = updated2)
  }

  // 🔧 Corrigé pour matcher la signature attendue par Graph[V]
  def removeEdge(edge: Edge[V]): UndirectedGraph[V] = {
    val updated1 = adjList.updatedWith(edge.from)(.map(.filterNot(_._1 == edge.to)))
    val updated2 = updated1.updatedWith(edge.to)(.map(.filterNot(_._1 == edge.from)))
    copy(adjList = updated2)
  }
}