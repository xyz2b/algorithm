package bloomfilter;

public class BloomFilter<E> {
    private int[] arr;
    private int length;
    // 一组hash function
    private HashFunction<E>[] hashFunctions;

    public BloomFilter(int length, HashFunction<E>[] hashFunctions) {
        arr = new int[length];
        this.length = length;
        this.hashFunctions = hashFunctions;
    }

    // 将每个hash function计算的结果对应到arr的每一位上，将其置为1
    public void add(E e) {
        for(HashFunction<E> hashFunction : hashFunctions) {
            int index = hashFunction.hash(e) % length;
            arr[index] = 1;
        }
    }

    // 如果经过hash function计算之后，对应到arr的每一位上，有一位为0，说明这个元素不存在，但是如果都为1，也不能确定这个元素一定存在（不存在是一定的，存在是不一定的）
    public boolean get(E e) {
        for(HashFunction<E> hashFunction : hashFunctions) {
            int index = hashFunction.hash(e) % length;
            if(arr[index] == 0) {
                return false;
            }
        }
        return true;
    }
}
