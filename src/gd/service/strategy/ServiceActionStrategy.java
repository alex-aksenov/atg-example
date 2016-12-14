package gd.service.strategy;

import gd.exception.ValidationException;

/**
 * @author Alex Aksenov on 14/12/2016.
 **/
public interface ServiceActionStrategy<T> {

    void executeSave(T dto) throws ValidationException;

}
