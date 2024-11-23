package graphalgorithms;

public class GraphDemo {

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

        g.printGraph();
        System.out.println();

        System.out.println("Grad for knuden med indhold 6: ");
        System.out.println(g.degree(6));

        System.out.println("Nabo knuder for knuden med indhold 6: ");
        System.out.println(g.neighbors(6));

        System.out.println("Tilstødende kanter for knuden med indhold 6: ");
        System.out.println(g.incidentEdges(6));
        System.out.println();

        System.out.println("Er knuderne med indhold 6 og 66 naboer? ");
        System.out.println(g.areAdjacent(66, 6));
        System.out.println("Er knuderne med indhold 6 og 38 naboer? ");
        System.out.println(g.areAdjacent(6, 38));
        System.out.println();

        g.removeEdge(6, 15);
        g.removeEdge(6, 123);
        g.removeEdge(6, 66);
        System.out.println("Efter kanterne til 6 er fjernet:");
        g.printGraph();
        System.out.println();

        g.removeVertex(6);
        System.out.println("Efter vertex 6 er fjernet:");
        g.printGraph();
        System.out.println();
    }
}
