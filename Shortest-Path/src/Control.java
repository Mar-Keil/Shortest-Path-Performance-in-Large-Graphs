import java.util.List;
import java.util.Scanner;

import Help.*;
import Lists.BFS;
import Lists.Dijkstra;
import Search.SearchBFS;
import Search.SearchDijkstra;

public class Control {

    private static final int WARMUPS = 3;
    private static final int RUNS = 100;

    public void newTest() {

        BFS bfs = new BFS();
        Dijkstra dijkstra = new Dijkstra();
        Scanner sc = new Scanner(System.in);
        //Print print = new Print();
        SearchBFS searchBFS = new SearchBFS();
        SearchDijkstra searchDijkstra = new SearchDijkstra();
        Rand rand = new Rand();

        System.out.print("Enter number of nodes: ");
        int nodes = sc.nextInt();

        for (int i = 0; i < WARMUPS; i++) {
            List<Integer>[] bfsListWarm  = bfs.bfs(nodes);
            List<int[]>[] dijListWarm = dijkstra.dijkstra(bfsListWarm);
            int targetWarm = rand.randKnod(nodes);
            int bfsStepsWarm = searchBFS.search(bfsListWarm, targetWarm);
            if (bfsStepsWarm != -1) {
                searchDijkstra.search(dijListWarm, targetWarm);
            }
        }
        System.out.println("Warmups finished.");

        double buildBfs = 0.0;
        double buildDij = 0.0;
        double sumSearchBfsSec = 0.0;
        long sumSearchBfsSteps = 0;
        int okSearchBfs = 0;
        double sumSearchDijSec = 0.0;
        long sumSearchDijSteps = 0;
        int okSearchDij = 0;

        for (int run = 0; run < RUNS; run++) {

            double t0 = System.nanoTime();
            List<Integer>[] bfsList = bfs.bfs(nodes);
            double t1 = System.nanoTime();
            buildBfs += (t1 - t0) / 1_000_000_000.0;

            double t2 = System.nanoTime();
            List<int[]>[] dijkstraList = dijkstra.dijkstra(bfsList);
            double t3 = System.nanoTime();
            buildDij += (t3 - t2) / 1_000_000_000.0;

            int knod = rand.randKnod(nodes);

            double s0 = System.nanoTime();
            int bfsSteps = searchBFS.search(bfsList, knod);
            double s1 = System.nanoTime();
            boolean bfsFound = (bfsSteps != -1);
            if (bfsFound) {
                sumSearchBfsSec += (s1 - s0) / 1_000_000_000.0;
                sumSearchBfsSteps += bfsSteps;
                okSearchBfs++;
            }

            if (bfsFound) {
                double d0 = System.nanoTime();
                int dijSteps = searchDijkstra.search(dijkstraList, knod);
                double d1 = System.nanoTime();
                if (dijSteps != -1) {
                    sumSearchDijSec += (d1 - d0) / 1_000_000_000.0;
                    sumSearchDijSteps += dijSteps;
                    okSearchDij++;
                }
            }

            int finished = run + 1;
            if (finished % 5 == 0) {
                System.out.print(finished + " ");
            }
        }

        double avgBuildBfs = buildBfs / RUNS;
        double avgBbuildDij = buildDij / RUNS + avgBuildBfs;

        Double avgSearchBfsSec = (okSearchBfs > 0) ? (sumSearchBfsSec / okSearchBfs) : null;
        Double avgSearchDijSec = (okSearchDij > 0) ? (sumSearchDijSec / okSearchDij) : null;
        Double avgSearchBfsSteps = (okSearchBfs > 0) ? (sumSearchBfsSteps * 1.0 / okSearchBfs) : null;
        Double avgSearchDijSteps = (okSearchDij > 0) ? (sumSearchDijSteps * 1.0 / okSearchDij) : null;

        System.out.println("\n=== Average results over " + RUNS + " runs ===");
        System.out.printf("Build time BFS:          %.6f s%n", avgBuildBfs);
        System.out.printf("Build time Dijkstra:     %.6f s%n", avgBbuildDij);

        if (avgSearchBfsSec != null) {
            System.out.printf("BFS search:   Hits=%d, AvgTime=%.6f s, AvgSteps=%.2f%n",
                    okSearchBfs, avgSearchBfsSec, avgSearchBfsSteps);
        } else {
            System.out.println("BFS search:   No successful hits (all -1).");
        }

        if (avgSearchDijSec != null) {
            System.out.printf("Dijkstra search:   Hits=%d, AvgTime=%.6f s, AvgSteps=%.2f%n",
                    okSearchDij, avgSearchDijSec, avgSearchDijSteps);
        } else {
            System.out.println("Dijkstra search:   No successful hits or BFS failed before.");
        }
    }
}
