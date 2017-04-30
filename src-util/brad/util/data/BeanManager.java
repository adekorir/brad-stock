package brad.util.data;

import brad.util.sys.BRADException;

import java.util.List;

public interface BeanManager<T extends Bean> {
    long create(T t) throws BRADException;
    T fetch(long id) throws BRADException;
    List<T> fetchAll(boolean condition) throws BRADException;
    void update(T t) throws BRADException;
    void delete(T t) throws BRADException;
}
