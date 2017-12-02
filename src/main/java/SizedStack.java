import java.util.Stack;

/**
 * Created by patrioshka on 11/23/17.
 */
public class SizedStack<T> extends Stack<T> {
    private final int maxSize;

    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public Object push(Object object) {
        while (this.size() > maxSize) {
            this.remove(0);
        }
        return super.push((T) object);
    }
}
