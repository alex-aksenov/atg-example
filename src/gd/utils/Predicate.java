package gd.utils;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public interface Predicate<T> {
    boolean evaluate(T next);
}
