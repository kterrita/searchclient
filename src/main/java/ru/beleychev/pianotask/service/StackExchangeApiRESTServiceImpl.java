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
    public SearchResponse getResponse(String title, Integer page) {
        return restClient.getQuestionsByTitle(title, page);
    }
}

