package ru.beleychev.pianotask.service;

import org.springframework.stereotype.Service;
import ru.beleychev.pianotask.client.RestClient;
import ru.beleychev.pianotask.domain.Item;
import ru.beleychev.pianotask.domain.SearchResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author beleychev
 */
@Service
public class StackExchangeApiRESTServiceImpl implements StackExchangeApiRESTService {

    private final RestClient restClient;

    public StackExchangeApiRESTServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Item> getItems(String title, Integer page) {
        SearchResponse searchResponse = restClient.getQuestionsByTitle(title, page);
        if (searchResponse == null) {
            return new ArrayList<>(0);
        }
        return searchResponse.getItems();
    }
}

