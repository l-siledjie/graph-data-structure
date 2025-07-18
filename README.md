

## Project Overview

This project is a *functional graph library* developed using *Scala 3, following **functional programming* paradigms. The library supports *directed* and *undirected graphs, each with **weighted edges. The project includes several graph algorithms (e.g., DFS, BFS, Dijkstra, and cycle detection), JSON serialization for graph structures, and **GraphViz export* for visualization. Additionally, the library is integrated into a *ZIO 2 CLI application*, enabling interactive manipulation of graphs.

### Components:

- *Core Library*: Implements the graph data structure, algorithms, JSON encoding/decoding, and GraphViz export.
- *ZIO Application*: An interactive command-line interface (CLI) to manipulate and visualize graphs.
- *Unit Tests*: Comprehensive test suite covering functionality, algorithms, and serialization.

---

## Instructions

### Prerequisites

- *Java 17+*: Required for building the project.
- *sbt*: The Scala build tool, used for project management and dependency handling.

### Installation and Setup

1. Clone the repository:
    bash
    git clone https://github.com/your-repository/graph-data-structure.git
    cd graph-data-structure
    

2. Build the project:
    bash
    sbt compile
    

### Running the Application

- To run the core project and interact with the graph:
    bash
    sbt "project ziocli" run
    

- For testing the functionality:
    bash
    sbt test
    

---

## Design Decisions

- *Functional Programming Principles: The library adheres to functional programming paradigms, ensuring **immutability* of data structures and *pure functions* for graph algorithms (DFS, BFS, etc.).
- *Recursion: All traversal algorithms (DFS, BFS) are implemented as **tail-recursive* functions, ensuring efficient stack usage.
- *Graph Representation: Graphs are represented as **immutable maps*, ensuring thread-safety and predictability.
- *Type Safety*: The graph interface is parameterized to allow flexible vertex types (e.g., String, Int, custom case classes).
- *Separation of Concerns: Graph manipulation is separated from input/output (I/O) concerns. The **ZIO application* handles user interaction, while the core library focuses on graph data structures and algorithms.

---

##Usage Examples

### Graph Creation

  import graph._
  
  ##val directedGraph = DirectedGraph.empty[String]
    .addEdge("A", "B", 1.5)
    .addEdge("B", "C", 2.0)
  
  val undirectedGraph = UndirectedGraph.empty[String]
    .addEdge("A", "B", 1.5)
    .addEdge("B", "A", 2.0)
