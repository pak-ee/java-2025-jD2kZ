public class JITCacheDemo {
    public static double process(int iterations) {
        double result = 0;
        for (int i = 0; i < iterations; i++) {
            result += i * 0.001;
        }
        return result;
    }

    public static void main(String[] args) {
        long total = 0;
        for (int i = 0; i < 1_000_000; i++) {
            long start = System.nanoTime();
            double res = process(1000);
            total += (System.nanoTime() - start);

            if (i == 9_999) {
                System.out.println("Среднее время за первые 10 000 вызовов: " + (total / 10_000) + " нс");
            }
        }
        System.out.println("Среднее время за 1 000 000 вызовов: " + (total / 1_000_000) + " нс");
    }
}



