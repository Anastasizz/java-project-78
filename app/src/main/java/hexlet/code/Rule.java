package hexlet.code;

@FunctionalInterface
public interface Rule {
    boolean apply(String str);
}
