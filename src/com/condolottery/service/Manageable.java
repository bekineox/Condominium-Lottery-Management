package com.condolottery.service;


public interface Manageable<T> {

   
    void add(T item);

    
    void displayAll();

    
    T searchById(String id);

    
    void update(T item);

    
    void delete(String id);
}
