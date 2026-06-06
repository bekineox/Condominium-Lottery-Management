package com.condolottery.service;

/**
 * Generic interface defining standard management operations.
 * All service classes implement this interface.
 *
 * @param <T> the type of entity managed by the service
 */
public interface Manageable<T> {

    /**
     * Adds a new item to the collection and persists it.
     * @param item the item to add
     */
    void add(T item);

    /**
     * Displays all items currently in the collection.
     */
    void displayAll();

    /**
     * Searches for an item by its unique ID.
     * @param id the unique identifier to search for
     * @return the found item, or null if not found
     */
    T searchById(String id);

    /**
     * Updates an existing item in the collection.
     * @param item the item with updated fields
     */
    void update(T item);

    /**
     * Deletes an item from the collection by its ID.
     * @param id the unique identifier of the item to delete
     */
    void delete(String id);
}
