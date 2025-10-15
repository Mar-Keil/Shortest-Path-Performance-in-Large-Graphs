package Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import Help.Rand;

public class BFS {

    public List<Integer>[] bfs(int length) {

        Rand rand = new Rand();

        @SuppressWarnings("unchecked")
        List<Integer>[] list = new ArrayList[length];

        IntStream.range(0, length)
                .parallel()
                .forEach(i -> list[i] = new ArrayList<>());

        IntStream.range(0, length)
                .parallel()
                .forEach(i -> {
                    int[] right = rand.randRight(i, length);
                    List<Integer> li = list[i];
                    for (int v : right) {
                        if (v >= 0 && v < length && v != i && !li.contains(v)) {
                            li.add(v);
                        }
                    }
                });

        // make edges bilateral (SEQUENTIAL to avoid collision)
        for (int i = 0; i < length; i++) {
            List<Integer> li = list[i];
            for (int v : li) {
                if (v >= 0 && v < length && v != i) {
                    List<Integer> lv = list[v];
                    if (!lv.contains(i)) {
                        lv.add(i);
                    }
                }
            }
        }

        IntStream.range(0, length)
                .parallel()
                .forEach(i -> list[i].sort(Integer::compare));

        return list;
    }
}
