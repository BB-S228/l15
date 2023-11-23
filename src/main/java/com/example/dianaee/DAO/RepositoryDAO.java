package com.example.dianaee.DAO;

import java.util.List;

public interface RepositoryDAO<T> {
    public Long insert(T о) throws Exception;

    public void update(T o);

    public void delete(Long Id);

    public T findById(Long Id);

    public List<T> findAll();
}
