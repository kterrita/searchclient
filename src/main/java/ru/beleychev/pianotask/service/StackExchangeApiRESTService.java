package ru.beleychev.pianotask.service;

import ru.beleychev.pianotask.domain.SearchResponse;

/**
 * @author beleychev
 */
public interface StackExchangeApiRESTService {

    /**
     * Returns search response by title
     *
     * @param title title to search
     * @param page  page to return
     * @return list of items by title
     */
    SearchResponse getResponse(String title, Integer page);
}
