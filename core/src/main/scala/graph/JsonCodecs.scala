package graph

import zio.json._

object JsonCodecs {

  implicit def edgeCodec[V: JsonEncoder: JsonDecoder]: JsonCodec[Edge[V]] =
    DeriveJsonCodec.gen[Edge[V]]: JsonCodec[Edge[V]]

  implicit def directedGraphCodec[V: JsonEncoder: JsonDecoder]: JsonCodec[DirectedGraph[V]] =
    DeriveJsonCodec.gen[DirectedGraph[V]]: JsonCodec[DirectedGraph[V]]

  implicit def undirectedGraphCodec[V: JsonEncoder: JsonDecoder]: JsonCodec[UndirectedGraph[V]] =
    DeriveJsonCodec.gen[UndirectedGraph[V]]: JsonCodec[UndirectedGraph[V]]
}
