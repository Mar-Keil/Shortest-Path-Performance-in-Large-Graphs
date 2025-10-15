package Search;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class SearchDijkstra {

    public int search(List<int[]>[] graph, int target) {

        if (target == 0) {
            return 1;
        }

        final int n = graph.length;
        final long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
        }
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{0, 0});

        int visitedCount = 0;

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long du = cur[1];

            if (visited[u]) continue;
            visited[u] = true;
            visitedCount++;

            if (u == target) {
                return visitedCount;
            }

            List<int[]> neighbors = graph[u];
            if (neighbors == null || neighbors.isEmpty()) continue;

            for (int[] edge : neighbors) {
                int v = edge[0];
                int w = edge[1];
                if (v < 0 || v >= n) continue;
                if (w < 0) continue;

                long nd = du + w;
                if (!visited[v] && nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[]{v, nd});
                }
            }
        }

        return -1;
    }
}
