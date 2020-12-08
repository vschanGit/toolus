package model.persistence;

import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 *
 * @param <E>
 */
public interface PersistenceStrategy<E> {

    void save(List<E> userStory) throws PersistenceException;

    List<E> load() throws PersistenceException;
}
