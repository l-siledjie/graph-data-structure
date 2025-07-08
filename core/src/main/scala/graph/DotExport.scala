package graph.extensions

import graph.{Graph, Edge, DirectedGraph}

object DotExport:

  // Déclaration correcte d'une méthode d'extension en Scala 3
  extension [V](graph: Graph[V])
    def toDot: String =
      val (graphType, arrow) = graph match
        case _: DirectedGraph[V] => ("digraph", "->")
        case _                   => ("graph", "--")

      val edgesStr = graph.edges.map(e =>
        s""""${e.from}" $arrow "${e.to}" [label="${e.weight}"];"""
      ).mkString("\n")

      s"""
         |$graphType G {
         |  $edgesStr
         |}
         |""".stripMargin
