package bloomfilter;

public interface HashFunction<E> {
    int hash(E e);
}
