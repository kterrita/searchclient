package ru.beleychev.pianotask.service;

import ru.beleychev.pianotask.domain.Item;

import java.util.List;

/**
 * @author beleychev
 */
public interface StackExchangeApiRESTService {

    /**
     * Returns list of items by title
     *
     * @param title title to search
     * @param page  page to return
     * @return list of items by title
     */
    List<Item> getItems(String title, Integer page);
}
