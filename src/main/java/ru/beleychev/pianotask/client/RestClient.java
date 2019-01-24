package ru.beleychev.pianotask.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.beleychev.pianotask.domain.SearchResponse;

import java.net.URI;
import java.util.Objects;

/**
 * @author beleychev
 */
@Component
public class RestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    @Value("${api.exchange.url}")
    private String apiExchangeUrl;

    @Value("${page.size}")
    private String pageSize;

    @Value("${order}")
    private String order;

    @Value("${sort}")
    private String sort;

    @Value("${site}")
    private String site;

    private final RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SearchResponse getQuestionsByTitle(String title, Integer page) {
        URI uri = initUri(title, page);
        LOGGER.debug("Get request to uri: {}", uri);
        ResponseEntity<SearchResponse> response = restTemplate.getForEntity(uri, SearchResponse.class);
        SearchResponse searchResponse = response.getBody();
        Objects.requireNonNull(searchResponse);
        if (CollectionUtils.isEmpty(searchResponse.getItems())) {
            LOGGER.warn("There are no questions with title {} returned", title);
            return null;
        }
        searchResponse.setTitle(title);
        return searchResponse;
    }

    private URI initUri(String title, Integer page) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(apiExchangeUrl)
                .queryParam("page", page)
                .queryParam("pagesize", pageSize)
                .queryParam("order", order)
                .queryParam("sort", sort)
                .queryParam("intitle", title)
                .queryParam("site", site)
                .encode()
                .build();

        return uriComponents.toUri();
    }
}
