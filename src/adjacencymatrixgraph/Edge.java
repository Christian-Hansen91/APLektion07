package adjacencymatrixgraph;

public class Edge<V> {
    // vertices at the end of the edge
    private final V u; // not null
    private final V v; // not null
    // weight of the edge
    private int weight;

    /**
     * Construct an edge with the specified weight between the specified vertices.
     */
    public Edge(V u, V v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    /**
     * Construct an edge between the specified vertices with weight 0.
     */
    public Edge(V u, V v) {
        this(u, v, 0);
    }

    public V getU() {
        return u;
    }

    public V getV() {
        return v;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s-%s (w:%d)", u, v, weight);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge<?> edge)) return false;

        return (u.equals(edge.u) && v.equals(edge.v) ||
                u.equals(edge.v) && v.equals(edge.u));
    }
}
