package Lists;

import Help.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dijkstra {

    public List<int[]>[] dijkstra(List<Integer>[] bfs) {

        int n = bfs.length;
        Rand rand = new Rand();

        List<int[]>[] list = new ArrayList[n];

        IntStream.range(0, n).
                parallel().
                forEach(i -> {
                    List<Integer> neighbors = bfs[i];

                    if (neighbors == null || neighbors.isEmpty()) {
                        list[i] = new ArrayList<>();
                    } else {

                        list[i] = neighbors.stream()
                                .map(v -> new int[]{v, rand.weight()})
                                .collect(Collectors.toCollection(ArrayList::new));
                    }
                });
        return list;
    }
}
