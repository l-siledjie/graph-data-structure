package graph

import zio.json._

object JsonCodecs {

  implicit def edgeCodec: JsonCodec[Edge[String]] =
    DeriveJsonCodec.gen[Edge[String]]

  implicit val directedGraphCodec: JsonCodec[DirectedGraph[String]] = {
    DeriveJsonCodec.gen[DirectedGraph[String]]
  }

  implicit def undirectedGraphCodec: JsonCodec[UndirectedGraph[String]] =
    DeriveJsonCodec.gen[UndirectedGraph[String]]
}
