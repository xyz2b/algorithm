package dynamicprogramming;

public class Test {

    public long add(long x, long y) {
        long z = x + y;
        return z;
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.add(1, 2);
    }
}
