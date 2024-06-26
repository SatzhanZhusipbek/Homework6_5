import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<E> implements CustomList<E> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;

    private int size;

    public CustomArrayList() {
        this.elements = new Object[INITIAL_CAPACITY];
    }

    public CustomArrayList(int capacity) {
        elements = new Object[capacity];
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }
    @Override
    public boolean put(E element) {
        ensureCapacity(this.size + 1);
        elements[this.size++] = element;
        return true;
    }
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (E) item;
    }

    private void ensureCapacity(int needCapacity) {
        if (needCapacity > elements.length) {
            int newSize = elements.length * 2;
            elements = Arrays.copyOf(elements, newSize);
        }
    }


    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    private class CustomIterator<E> implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public E next() {
            E value = (E) elements[current++];
            return value;
        }
    }
}
