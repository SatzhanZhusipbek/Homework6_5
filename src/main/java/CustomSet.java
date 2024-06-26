import java.util.Iterator;

public interface CustomSet {

    boolean add(Object element);

    boolean contains(Object element);

    Iterator iterator();

    int size();

    boolean remove(Object element);


}
