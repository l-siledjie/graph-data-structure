package graph

import scala.collection.mutable

object GraphOps {
  def dfs[V](graph: Graph[V], start: V): List[V] = {
    def dfsHelper(vertex: V, visited: Set[V]): List[V] = {
      if (visited.contains(vertex)) {
        List.empty
      } else {
        val newVisited = visited + vertex
        vertex :: graph.neighbors(vertex).map(_._1).flatMap(neighbor => dfsHelper(neighbor, newVisited))
      }
    }
    dfsHelper(start, Set.empty)
  }

  def bfs[V](graph: Graph[V], start: V): List[V] = {
    def bfsHelper(queue: List[V], visited: Set[V]): List[V] = {
      queue match {
        case Nil => List.empty
        case vertex :: rest =>
          if (visited.contains(vertex)) {
            bfsHelper(rest, visited)
          } else {
            val newVisited = visited + vertex
            vertex :: bfsHelper(rest ++ graph.neighbors(vertex).map(_._1).toList, newVisited)
          }
      }
    }
    bfsHelper(List(start), Set.empty)
  }

  def hasCycle[V](graph: Graph[V]): Boolean = {
    def dfs(vertex: V, visited: Set[V], recStack: Set[V]): Boolean = {
      if (recStack.contains(vertex)) return true
      if (visited.contains(vertex)) return false

      val newVisited = visited + vertex
      val newRecStack = recStack + vertex

      graph.neighbors(vertex).exists { case (neighbor, _) => dfs(neighbor, newVisited, newRecStack) }
    }

    graph.vertices.exists(vertex => dfs(vertex, Set.empty, Set.empty))
  }

  def dijkstra[V](graph: Graph[V], start: V): Map[V, Int] = {
    val distances = mutable.Map[V, Int]().withDefaultValue(Int.MaxValue)
    distances(start) = 0
    val priorityQueue = mutable.PriorityQueue[(Int, V)]()(Ordering.by(-_._1))
    priorityQueue.enqueue((0, start))

    while (priorityQueue.nonEmpty) {
      val (currentDistance, currentVertex) = priorityQueue.dequeue()
      if (currentDistance <= distances(currentVertex)) {
        graph.neighbors(currentVertex).foreach { case (neighbor, weight) =>
          val newDistance = currentDistance + weight
          if (newDistance < distances(neighbor)) {
            distances(neighbor) = newDistance
            priorityQueue.enqueue((newDistance, neighbor))
          }
        }
      }
    }
    distances.toMap
  }
}
