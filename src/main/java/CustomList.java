public interface CustomList<E> extends Iterable<E> {

    boolean put(E element);

    E get(int index);

    E remove(int index);

    int size();

}
