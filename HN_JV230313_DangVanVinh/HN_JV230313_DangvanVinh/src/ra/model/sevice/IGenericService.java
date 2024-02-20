package ra.model.sevice;

import ra.model.model.Catalog;

import java.util.List;

public interface IGenericService<T, E> {
    List<T> getAll();

    void save(T t);

    T findById(E e);

    void delete(E e);

    boolean hasProductsInCatalog(Catalog catalogToDelete);

    Object findById();
}
