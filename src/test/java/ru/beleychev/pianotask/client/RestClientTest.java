package ru.beleychev.pianotask.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.beleychev.pianotask.domain.Item;
import ru.beleychev.pianotask.domain.Owner;
import ru.beleychev.pianotask.domain.SearchResponse;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 * @author beleychev
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClientTest {

    private static final String URL = "http://localhost/search";

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private RestClient restClient;
    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getQuestionsByTitleWillReturnNullIfNoOneFound() {
        mockRestServiceServer.expect(requestTo(getUri("notfound"))).andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess("{\n" + "\"items\":[], "
                                + "\"hasMore\":false}",
                        MediaType.APPLICATION_JSON_UTF8));

        SearchResponse searchResponse = restClient.getQuestionsByTitle("notfound", 1);

        assertNull(searchResponse);
    }

    @Test
    public void getQuestionsByTitleWillReturnSearchResponseIfExists() {
        mockRestServiceServer.expect(requestTo(getUri("found"))).andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess("{\n" + "\"items\":"
                                + "[{\"owner\":{ \"display_name\":\"user\"},"
                                + "\"link\":\"http://localhost/test\", "
                                + "\"creation_date\":1548311324,"
                                + "\"title\":\"found\","
                                + "\"is_answered\":false}],"
                                + "\"has_more\":true},",
                        MediaType.APPLICATION_JSON_UTF8));

        SearchResponse actual = restClient.getQuestionsByTitle("found", 1);

        SearchResponse expected = new SearchResponse();
        expected.setTitle("found");
        expected.setHasMore(Boolean.TRUE);
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setCreationDate(Instant.ofEpochSecond(1548311324L));
        item.setAnswered(Boolean.FALSE);
        item.setLink("http://localhost/test");
        item.setTitle("found");
        Owner owner = new Owner();
        owner.setDisplayName("user");
        item.setOwner(owner);
        items.add(item);
        expected.setItems(items);

        assertEquals(expected, actual);
    }

    private URI getUri(String title) {
        return UriComponentsBuilder
                .fromUriString(URL)
                .queryParam("page", 1)
                .queryParam("pagesize", 15)
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("intitle", title)
                .queryParam("site", "stackoverflow")
                .encode()
                .build()
                .toUri();
    }
}