package Help;

import java.util.concurrent.ThreadLocalRandom;

public class Rand {

    private static final double ALPHA = 0.95;

    public int[] randRight(int knod, int length) {
        if (length <= knod + 1) return new int[0];

        int span = length - knod - 1;

        int kTarget = Math.max(1, (int) Math.round(ALPHA * Math.sqrt(length)));
        double p = Math.min(1.0, kTarget / (double) Math.max(1, (length - 1)));

        int target = (int) Math.round(span * p);
        if (target <= 0) return new int[0];
        if (target > span) target = span;

        return ThreadLocalRandom.current()
                .ints(knod + 1, length)
                .distinct()
                .limit(target)
                .toArray();
    }

    public int weight() {
        return ThreadLocalRandom.current().nextInt(1, 100_000);
    }

    public int randKnod(int length) {
        return ThreadLocalRandom.current().nextInt(length);
    }
}
