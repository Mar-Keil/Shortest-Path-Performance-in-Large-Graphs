package Search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class SearchBFS {

    public int search(List<Integer>[] list, int target) {

        // Sonderfall: Startknoten 0 ist das Ziel
        if (target == 0) {
            return 1; // 0 besucht und sofort gefunden
        }

        boolean[] visited = new boolean[list.length];
        Deque<Integer> q = new ArrayDeque<>();

        visited[0] = true;
        int visitedCount = 1;
        q.add(0);

        while (!q.isEmpty()) {
            int u = q.poll();
            List<Integer> neighbors = list[u];
            if (neighbors == null || neighbors.isEmpty()) continue;

            for (int v : neighbors) {
                if (v < 0 || v >= list.length) continue;
                if (!visited[v]) {
                    visited[v] = true;
                    visitedCount++;

                    if (v == target) {
                        return visitedCount; // Schritte bis zum Fund
                    }
                    q.add(v);
                }
            }
        }

        return -1; // nicht gefunden
    }
}