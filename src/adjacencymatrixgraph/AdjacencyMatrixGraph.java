package adjacencymatrixgraph;

import java.sql.Array;
import java.util.*;

/**
 * Adjacency matrix implementation of the graph interface.
 */
public class AdjacencyMatrixGraph<V> implements Graph<V> {
    // List with all the vertices in the graph.
    private Map<V, Integer> vertices;
    // 2-dim array with all the edges in the graph.
    private Edge<V>[][] matrix;
    private final int matrixCapacity;
    private int matrixSize; // equal to vertices.size()

    //-----------------------------------------------------

    /**
     * Construct an empty AdjacencyMatrixGraph.
     */
    public AdjacencyMatrixGraph(int matrixCapacity) {
        vertices = new LinkedHashMap<>();
        @SuppressWarnings("unchecked")
        Edge<V>[][] emptyMatrix = new Edge[matrixCapacity][matrixCapacity];
        matrix = emptyMatrix;
        this.matrixCapacity = matrixCapacity;
        matrixSize = 0;
    }

    /**
     * Return a list with the vertices in the graph.
     */
    @Override
    public List<V> vertices() {
        return new ArrayList<>(vertices.keySet());
    }

    /**
     * Return a list with the edges in the graph.
     */
    @Override
    public List<Edge<V>> edges() {
        List<Edge<V>> edges = new ArrayList<>();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[i][j] != null) {
                    edges.add(matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * Return a list with the neighbors of the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public List<V> neighbors(V v) {
        assert vertices.containsKey(v);

        List<V> verts = new ArrayList<>();
        int index = vertices.get(v);
        for (Edge<V> edge : matrix[index]) {
            if (edge.getU().equals(v)) {
                verts.add(edge.getV());
            } else if (edge.getV().equals(v)) {
                verts.add(edge.getU());
            }
        }

        return verts;
    }

    /**
     * Return the degree for the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public int degree(V v) {
        return neighbors(v).size();
    }

    /**
     * Return the incident edges to the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public List<Edge<V>> incidentEdges(V v) {
        assert vertices.containsKey(v);

        List<Edge<V>> edges = new ArrayList<>();
        int index = vertices.get(v);
        for (Edge<V> edge : matrix[index]) {
            if (edge.getV().equals(v) || edge.getU().equals(v)) {
                edges.add(edge);
            }
        }

        return edges;
    }

    /**
     * Return true, if the specified vertices are neighbors.
     * Pre: The vertices are vertices in the graph.
     */
    @Override
    public boolean areAdjacent(V u, V v) {
        assert vertices.containsKey(u);
        assert vertices.containsKey(v);
        return neighbors(u).contains(v) || neighbors(v).contains(u);
    }

    /**
     * Print the vertices and the edges.
     */
    @Override
    public void printGraph() {
        for (V v : vertices.keySet()) {
            System.out.print("Vertex: " + v + "   ");
            int vRow = vertices.get(v);
            for (int col = 0; col < matrixSize; col++) {
                System.out.printf("%-12s", matrix[vRow][col]);
            }
            System.out.println();
        }
    }

    /**
     * Add a vertex to the graph.
     * Pre: The vertex is not in the graph before this addition.
     */
    @Override
    public void addVertex(V v) {
        assert !vertices.containsKey(v);
        vertices.put(v, matrixSize);
        matrixSize = vertices.size();
    }

    private void extendMatrix() {
        // NOT implemented!
    }

    /**
     * Add an edge with the specified weight between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     * Pre: The weight is not negative.
     */
    @Override
    public Edge<V> addEdge(V u, V v, int weight) {
        assert vertices.containsKey(u) && vertices.containsKey(v);
        assert weight >= 0;
        assert !areAdjacent(u, v);

        Edge<V> edge = new Edge<V>(u, v);
        int uIndex = vertices.get(u);
        int vIndex = vertices.get(v);
        matrix[vIndex][uIndex] = edge;
        matrix[uIndex][vIndex] = edge;

        return edge;
    }

    /**
     * Add an edge with weight 0 between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     */
    @Override
    public Edge<V> addEdge(V u, V v) {
        Edge<V> edge = addEdge(u, v, 0);
        return edge;
    }

    /**
     * Remove the specified vertex from the graph.
     * Pre: The vertex is in the graph, and the vertex has no incident edges.
     */
    @Override
    public void removeVertex(V v) {
        assert incidentEdges(v).isEmpty();
        vertices.remove(v);
    }

    /**
     * Remove the edge between the specified vertices from the graph.
     * Pre: The vertices are vertices in the graph,
     * and The graph has an edge between the vertices.
     */
    @Override
    public void removeEdge(V u, V v) {
        assert vertices.containsKey(u) && vertices.containsKey(v);
        assert areAdjacent(u, v);

        int uIndex = vertices.get(u);
        int vIndex = vertices.get(v);
        matrix[vIndex][uIndex] = null;
        matrix[uIndex][vIndex] = null;
    }
}
