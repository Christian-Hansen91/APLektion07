package graphalgorithms;

import java.util.List;

public interface Graph<V> {
    /** Return a list with the vertices in the graph. */
    List<V> vertices();

    /** Return a list with the edges in the graph. */
    List<Edge<V>> edges();

    /**
     * Return a list with the neighbors of the specified vertex.
     * Pre: The vertex is in the graph.
     */
    List<V> neighbors(V v);

    /**
     * Return the degree for the specified vertex.
     * Pre: The vertex is in the graph.
     */
    int degree(V v);

    /**
     * Return the incident edges to the specified vertex.
     * Pre: The vertex is in the graph.
     */
    List<Edge<V>> incidentEdges(V v);

    /** Print the vertices and the edges. */
    void printGraph();

    /**
     * Add a vertex to the graph.
     * Pre: The vertex is not in the graph before this addition.
     */
    void addVertex(V v);

    /**
     * Add an edge with the specified weight between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     * Pre: The weight is positive.
     */
    Edge<V> addEdge(V u, V v, int weight);

    /**
     * Add an edge with weight 1 between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     */
    Edge<V> addEdge(V u, V v);

    /**
     * Remove the specified vertex from the graph.
     * Pre: The vertex is in the graph, and the vertex has no incident edges.
     */
    void removeVertex(V v);

    /**
     * Remove the edge between the specified vertices from the graph.
     * Pre: The vertices are vertices in the graph,
     *   and The graph has an edge between the vertices.
     */
    void removeEdge(V u, V v);

    /**
     * Return true, if the specified vertices are neighbors.
     * Pre: The vertices are vertices in the graph.
     */
    boolean areAdjacent(V u, V v);

}
