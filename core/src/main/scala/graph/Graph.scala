package graph

val base: Int = 4

trait Graph[V] {
  def vertices: Set[V]
  def edges: Set[Edge[V]]
  def neighbors(vertex: V): Set[(V, Int)]
  def addEdge(edge: Edge[V]): Graph[V]
  def removeEdge(edge: Edge[V]): Graph[V]
}
