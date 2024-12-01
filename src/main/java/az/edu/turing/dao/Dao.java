package az.edu.turing.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E,T>{

    E add(E entity);

    Optional<E> getById(T id);

    List<E> getAll();

    void update(E entity);

    void deleteById(T id);
}
