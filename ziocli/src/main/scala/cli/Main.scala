package cli

import scala.Console
import zio._
import zio.Console._

import graph.{Graph, DirectedGraph, UndirectedGraph}

object Main extends ZIOAppDefault{
  val program: ZIO[Any, Throwable, Unit] = for {
    _ <- Console.printLine("Welcome to Functional Graphs CLI!")
    // e.g. read a command, build a graph, run algorithms...
    _ <- Console.printLine("Type 'help' for commands")
    // simple REPL:
    _ <- inputLoop(DirectedGraph.empty[Int])
  } yield ()

  def inputLoop(graph: Graph[Int]): ZIO[Any, Throwable, Unit] =
    for {
      line <- Console.readLine.map(_.trim)
      _ <- line match {
        case "exit" => Console.printLine("Goodbye!")
        case "vertices" =>
          Console.printLine(s"Vertices: ${graph.vertices.mkString(", ")}") *> inputLoop(graph)
        case cmd if cmd.startsWith("add ") =>
          // parse and update
          // e.g. "add 1 2 3" -> from=1,to=2,weight=3
          val parts = cmd.split("\\s+")
          newGraph = graph.addEdge(parts(1).toInt, parts(2).toInt, parts(3).toDouble)
          inputLoop(newGraph)
        case _ => Console.printLine("Unknown command.") *> inputLoop(graph)
      }
    } yield ()

  override def run = program.catchAll(err => Console.printLineError(s"Error: ${err.getMessage}"))
}
