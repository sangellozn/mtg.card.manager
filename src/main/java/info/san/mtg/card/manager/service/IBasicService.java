package info.san.mtg.card.manager.service;

import java.util.Collection;

/**
 * Basic service with all common operations.
 * 
 * @param <T> object type (usually a dto)
 * @param <I> the identifier type of the object. 
 */
public interface IBasicService<T, I> {
    
    T create(T item);
    
    void update(T item);
    
    void deleteById(I id);
    
    T getById(I id);

    Collection<T> findAll();
    
}
