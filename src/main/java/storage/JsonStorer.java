package storage;

public interface JsonStorer<T> {

    /**
     * Converts JSON storer into its respective functional java type {@code T}.
     */
    public T toJavaType();
}
