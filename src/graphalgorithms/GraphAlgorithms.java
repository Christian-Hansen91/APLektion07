package graphalgorithms;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class GraphAlgorithms {
    public static void main(String[] args) {
        Graph<Integer> g = new EdgeListGraph<>();

        g.addVertex(15);
        g.addVertex(38);
        g.addVertex(6);
        g.addVertex(123);
        g.addVertex(66);

        g.addEdge(15, 38, 10);
        g.addEdge(15, 6, 23);
        g.addEdge(15, 66, 90);
        g.addEdge(38, 123, 55);
        g.addEdge(38, 66, 2);
        g.addEdge(6, 123, 7);
        g.addEdge(6, 66, 8);
        g.addEdge(123, 66, 76);
        //g.addEdge(38, 6, 9);

        g.printGraph();
        System.out.println();

        System.out.println("DFS: Depth-First traversal starting in 123:");
        System.out.println(dfs(g, 123));
        System.out.println();

        System.out.println("DFS traversal using a stack starting in 123:");
        System.out.println(dfsStack(g, 123));
        System.out.println();

        System.out.println("BFS: Breth-First traversal starting in 123:");
        System.out.println(bfs(g, 123));
        System.out.println();

        System.out.println("Graph is connected?");
        System.out.println(isGraphConnected(g));
        System.out.println();

        System.out.println("Vertex 123 and 15 are connected:");
        System.out.println(hasGraphPath(g, 123, 15));
        System.out.println();

        System.out.println("Minimum spanning tree:");
        System.out.println(mst(g));
        System.out.println();

        System.out.println("Dijkstra starting in 123:");
        System.out.println(dijkstra(g, 123));
        System.out.println();
    }

    /**
     * Return a list with the vertices of the specified graph
     * found by a Depth-First traversal (DFS) of the graph starting at the specified vertex.
     * Throw exception if the vertex is not in the graph.
     */
    public static <V> List<V> dfs(Graph<V> graph, V v) {
        assert graph.vertices().contains(v);
        List<V> visited = new ArrayList<>();
        List<V> dfsVertices = dfs(graph, v, visited);

        return dfsVertices;
    }

    private static <V> List<V> dfs(Graph<V> graph, V v, List<V> visited) {
        visited.add(v);
        for (V neighbor : graph.neighbors(v)) {
            if (!visited.contains(neighbor)) dfs(graph, neighbor, visited);
        }
        return visited;
    }

    /**
     * Return a list with the vertices of the specified graph
     * found by a Depth-First traversal (DFT) of the graph starting at the specified vertex.
     * Throw exception if the vertex is not in the graph.
     */
    public static <V> List<V> dfsStack(Graph<V> graph, V v) {
        Stack<V> stack = new Stack<>();
        List<V> visited = new ArrayList<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            V current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (V neighbor : graph.neighbors(current)) {
                    if (!visited.contains(neighbor)) stack.push(neighbor);
                }
            }
        }

        return visited;
    }

    /**
     * Return a list with the vertices of the specified graph
     * found by a Breath-First traversal (BFT) of the graph stating at the specified vertex.
     * Throw exception if the vertex is not in the graph.
     */
    public static <V> List<V> bfs(Graph<V> graph, V v) {
        List<V> visited = new ArrayList<>();
        Queue<V> vQueue = new LinkedList<>();
        vQueue.add(v);
        while (!vQueue.isEmpty()) {
            V current = vQueue.remove();
            if (!visited.contains(current)) {
                visited.add(current);
                for (V neighbor : graph.neighbors(current)) {
                    if (!visited.contains(neighbor)) vQueue.add(neighbor);
                }
            }
        }

        return visited;
    }

    /**
     * Return true if the graph is connected, otherwise false.
     */
    public static <V> boolean isGraphConnected(Graph<V> graph) {
        assert graph.vertices().size() > 0;
        int size = graph.vertices().size();
        return size == bfs(graph, graph.vertices().get(0)).size();
    }

    /**
     * Return true if the graph has a path between the specified vertices.
     * Throw exception if the vertices are not in the graph.
     */
    public static <V> boolean hasGraphPath(Graph<V> graph, V v1, V v2) {
        assert graph.vertices().contains(v1) && graph.vertices().contains(v2);
        List<V> v1Paths = dfs(graph, v1);
        return v1Paths.contains(v2);
    }

    /**
     * Return a minimum spanning tree (MST).
     */
    public static <V> List<Edge<V>> mst(Graph<V> graph) {
        List<Set<V>> setList = new ArrayList<>();
        for (V vertex : graph.vertices()) {
            Set<V> set = new HashSet<>();
            set.add(vertex);
            setList.add(set);
        }
        List<Edge<V>> edges = new ArrayList<>();
        Queue<Edge<V>> allEdges = new PriorityQueue<>((Edge<V> e1, Edge<V> e2) -> e1.getWeight() - e2.getWeight());
        allEdges.addAll(graph.edges());
        Edge<V> current;
        List<V> visited = new ArrayList<>();

        while (edges.size() < graph.vertices().size() - 1) {
            current = allEdges.poll();
            V v = current.getV();
            V u = current.getU();
            int iV = 0, iU = 0, temp = 0;
            boolean found = false;
            while (!found && temp < setList.size()) {
                if (setList.get(temp).contains(v)) iV = temp;
                if (setList.get(temp).contains(u)) iU = temp;
                temp++;
            }
            if (iV != iU) {
                setList.get(iV).add(u);
                setList.remove(iU);
                edges.add(current);
            }
        }
        return edges;
    }

    /**
     * Return a map containing (vertex, weight) pairs,
     * where weight is the total weight of the shortest path
     * from the specified vertex v to the vertex in the pair.
     */
    public static <V> Map<V, Integer> dijkstra(Graph<V> graph, V v) {
        Map<V, Integer> shortest = new HashMap<>();
        for (V vert : graph.vertices()) {
            shortest.put(vert, Integer.MAX_VALUE);
        }
        shortest.put(v, 0);
        List<V> queue = new ArrayList<>();
        queue.addAll(graph.vertices());
        queue.sort((V v1, V v2) -> shortest.get(v1) - shortest.get(v2));
        System.out.println(queue);
        while (!queue.isEmpty()) {
            V current = queue.remove(0);
            for (V neighbor : graph.neighbors(current)) {
                Edge<V> edge = null;
                for (Edge<V> e : graph.incidentEdges(current)) {
                    if (e.getV().equals(neighbor) || e.getU().equals(neighbor)) {
                        edge = e;
                    }
                }
                if (edge.getWeight() + shortest.get(current) < shortest.get(neighbor)) {
                    shortest.put(neighbor, shortest.get(current) + edge.getWeight());
                }
            }

            queue.sort((V v1, V v2) -> shortest.get(v1) - shortest.get(v2));

        }
        return shortest;
    }
}
