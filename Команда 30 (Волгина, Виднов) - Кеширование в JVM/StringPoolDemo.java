public class StringPoolDemo {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";

        System.out.println("s1 == s2? " + (s1 == s2));

        String s3 = new String("hello");
        System.out.println("s1 == s3? " + (s1 == s3));

        String s4 = s3.intern();
        System.out.println("s1 == s4? " + (s1 == s4));
    }
}




