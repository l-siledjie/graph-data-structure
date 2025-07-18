package graph

import scala.collection.mutable

class AdjacencyListGraph[V] extends Graph[V] {
  private val adjacencyList: mutable.Map[V, mutable.ListBuffer[(V, Int)]] = mutable.Map()

  def vertices: Set[V] = adjacencyList.keys.toSet

  def edges: Set[Edge[V]] = adjacencyList.flatMap { case (vertex, neighbors) =>
    neighbors.map { case (neighbor, weight) => Edge(vertex, neighbor, weight) }
  }.toSet

  def neighbors(vertex: V): Set[(V, Int)] =
    adjacencyList.get(vertex).map(_.toSet).getOrElse(Set.empty)

  def addEdge(edge: Edge[V]): Graph[V] = {
    adjacencyList.getOrElseUpdate(edge.from, mutable.ListBuffer()) += ((edge.to, edge.weight))
    this
  }

  def removeEdge(edge: Edge[V]): Graph[V] = {
    adjacencyList.get(edge.from).foreach(_.filterInPlace { case (v, _) => v != edge.to })
    this
  }
}
