package ru.beleychev.pianotask.service;

import ru.beleychev.pianotask.domain.Item;

import java.util.List;

/**
 * @author beleychev
 */
public interface StackExchangeApiRESTService {
    public List<Item> getItems(String title);
}
