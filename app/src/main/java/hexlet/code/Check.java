package hexlet.code;

@FunctionalInterface
public interface Check<T> {
    boolean apply(T value);
}
