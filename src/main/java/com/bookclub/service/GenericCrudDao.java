package com.bookclub.service;

import java.util.List;

public interface GenericCrudDao<E, K> {

    void add(E entity);

    void update(E entity);

    boolean remove(K entity);

    List<E> list(K key); // Return a list of objects of type E.

    E find(K key); // Return an object of type E by type K value.

    List<E> list();
}