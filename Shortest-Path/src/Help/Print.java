package Help;

import java.util.List;

public class Print {

    public void printBFS(List<Integer>[] adj) {
        for (int i = 0; i < adj.length; i++) {
            List<Integer> neighbors = adj[i];
            System.out.println(i + " -> " + (neighbors == null ? "[]" : neighbors));
        }
        System.out.println("Nodes: " + adj.length);
    }

    public void printDijkstra(List<int[]>[] adj) {
        for (int i = 0; i < adj.length; i++) {
            List<int[]> neighbors = adj[i];
            System.out.print(i + " -> ");
            if (neighbors == null || neighbors.isEmpty()) {
                System.out.println("[]");
                continue;
            }
            System.out.print("[");
            for (int p = 0; p < neighbors.size(); p++) {
                int[] pair = neighbors.get(p);
                System.out.print("{" + pair[0] + ", " + pair[1] + "}");
                if (p < neighbors.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    public void printTime(double bfs, double dijkstra) {
        System.out.println();
        System.out.println("BFS: " + bfs);
        System.out.println("Dijkstra: " + (dijkstra + bfs));
    }
}

