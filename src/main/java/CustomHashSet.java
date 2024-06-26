import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet implements CustomSet{
    private static class Entry {
        Object key;
        Entry next;
    }

    private Entry[] buckets;

    private int size;

    public CustomHashSet(int capacity) {
        buckets = new Entry[capacity];
        size = 0;
    }

    private int hashFunction(int hashCode) {
        int index = hashCode;
        if (index < 0) {
            index = -index;
        }
        return index % buckets.length;
    }
    @Override
    public boolean add(Object element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(element)) {
                return false;
            }
            current = current.next;
        }

        Entry entry = new Entry();
        entry.key = element;
        entry.next  = buckets[index];
        buckets[index] = entry;
        size++;
        return true;
    }
    @Override
    public boolean contains(Object element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    @Override
    public Iterator iterator() {
        return new CustomHashSetIterator();
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean remove(Object element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(element)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    class CustomHashSetIterator implements Iterator {

        private int currentBucket;
        private int previousBucket;
        private Entry currentEntry;
        private Entry previousEntry;

        public CustomHashSetIterator() {
            currentEntry = null;
            previousEntry = null;
            currentBucket = -1;
            previousBucket = -1;
        }

        @Override
        public boolean hasNext() {
            if (currentEntry != null && currentEntry.next != null) {
                return true;
            }

            for (int index = currentBucket+1; index < buckets.length; index++) {
                if (buckets[index] != null) { return true; }
            }
            return false;
        }

        @Override
        public Object next() {
            previousEntry = currentEntry;
            previousBucket = currentBucket;

            if (currentEntry == null || currentEntry.next == null) {
                currentBucket++;
                while (currentBucket < buckets.length &&
                        buckets[currentBucket] == null) {
                    currentBucket++;
                }

                if (currentBucket < buckets.length) {
                    currentEntry = buckets[currentBucket];
                }

                else {
                    throw new NoSuchElementException();
                }
            }

            else {
                currentEntry = currentEntry.next;
            }
            return currentEntry.key;

        }
    }
}
