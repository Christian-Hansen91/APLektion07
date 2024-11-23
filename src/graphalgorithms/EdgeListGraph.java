package graphalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Edge list implementation of the graph interface.
 */
public class EdgeListGraph<V> implements Graph<V> {
    // List with all the vertices in the graph.
    private List<V> vertices;
    // List with all the edges in the graph.
    private List<Edge<V>> edges;

    //-----------------------------------------------------

    /** Construct an empty EdgeListGraph. */
    public EdgeListGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /** Return a list with the vertices in the graph. */
    @Override
    public List<V> vertices() {
        return new ArrayList<>(vertices);
    }

    /** Return a list with the edges in the graph. */
    @Override
    public List<Edge<V>> edges() {
        return new ArrayList<>(edges);
    }

    /**
     * Return a list with the neighbors of the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public List<V> neighbors(V v) {
        assert vertices.contains(v);
        List<V> neighbors = new ArrayList<>();
        for (Edge<V> edge : edges) {
            if (edge.getU().equals(v)) {
                neighbors.add(edge.getV());
            } else if (edge.getV().equals(v)) {
                neighbors.add(edge.getU());
            }
        }
        return neighbors;
    }

    /**
     * Return the degree for the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public int degree(V v) {
        assert vertices.contains(v);
        int degree = 0;
        for (Edge<V> edge : edges) {
            if (edge.getU().equals(v) || edge.getV().equals(v)) {
                degree++;
            }
        }
        return degree;
    }

    /**
     * Return the incident edges to the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public List<Edge<V>> incidentEdges(V v) {
        assert vertices.contains(v);
        List<Edge<V>> incidentEdges = new ArrayList<>();
        for (Edge<V> edge : edges) {
            if (edge.getU().equals(v) || edge.getV().equals(v)) {
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }

    /**
     * Return true, if the specified vertices are neighbors.
     * Pre: The vertices are vertices in the graph.
     */
    @Override
    public boolean areAdjacent(V u, V v) {
        assert vertices.contains(u) && vertices.contains(v);
        Edge<V> temp = new Edge<>(u, v);
        for (Edge<V> edge : edges) {
            if (edge.equals(temp)) return true;
        }
        return false;
    }

    /** Print the vertices and the edges. */
    @Override
    public void printGraph() {
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges: " + edges);
    }

    /**
     * Add a vertex to the graph.
     * Pre: The vertex is not in the graph before this addition.
     */
    @Override
    public void addVertex(V v) {
        assert !vertices.contains(v);
        vertices.add(v);
    }

    /**
     * Add an edge with the specified weight between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     * Pre: The weight is not negative.
     */
    @Override
    public Edge<V> addEdge(V u, V v, int weight) {
        Edge<V> edge = new Edge<>(u, v, weight);
        assert !edges.contains(edge);
        assert weight >= 0;
        edges.add(edge);
        return edge;
    }

    /**
     * Add an edge with weight 0 between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     */
    @Override
    public Edge<V> addEdge(V u, V v) {
        return addEdge(u, v, 0);
    }

    /**
     * Remove the specified vertex from the graph.
     * Pre: The vertex is in the graph, and the vertex has no incident edges.
     */
    @Override
    public void removeVertex(V v) {
        assert vertices.contains(v);
        assert incidentEdges(v).isEmpty();
        vertices.remove(v);
    }

    /**
     * Remove the edge between the specified vertices from the graph.
     * Pre: The vertices are vertices in the graph,
     *   and The graph has an edge between the vertices.
     */
    @Override
    public void removeEdge(V u, V v) {
        assert vertices.contains(u) && vertices.contains(v);
        Edge<V> temp = new Edge<>(u, v);
        for (int i = 0; i < edges.size(); i++) {
            Edge<V> edge = edges.get(i);
            if (edge.equals(temp)) {
                edges.remove(edge);
                return;
            }
        }
        assert false;
    }
}
