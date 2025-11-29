public class IntegerCacheDemo {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(10);
        Integer b = 10;
        System.out.println("a == b? " + (a == b));

        Integer c = 150;
        Integer d = 150;
        System.out.println("c == d? " + (c == d));
    }
}


