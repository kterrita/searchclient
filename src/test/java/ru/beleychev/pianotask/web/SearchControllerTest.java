package ru.beleychev.pianotask.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import ru.beleychev.pianotask.domain.SearchForm;
import ru.beleychev.pianotask.domain.SearchResponse;
import ru.beleychev.pianotask.service.StackExchangeApiRESTService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author beleychev
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {

    @Autowired
    private SearchController searchController;

    @MockBean
    private StackExchangeApiRESTService stackExchangeApiRESTService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void index() {
        ModelAndView expected = new ModelAndView();
        expected.setViewName("index");
        expected.addObject("searchForm", new SearchForm());

        ModelAndView actual = searchController.index();

        assertEquals(expected.getModel().get("searchForm"), actual.getModel().get("searchForm"));
    }

    @Test
    public void resultsWillReturnViewWithErrorObject() {
        SearchForm searchForm = new SearchForm();
        searchForm.setTitle("title");
        String error = "There is no title.";

        BindingResult bindingResult = mock(BindingResult.class);
        List<ObjectError> objectErrors = new ArrayList<>();
        ObjectError objectError = new ObjectError("searchForm", error);
        objectErrors.add(objectError);
        when(bindingResult.hasErrors()).thenReturn(Boolean.TRUE);
        when(bindingResult.getAllErrors()).thenReturn(objectErrors);

        ModelAndView result = searchController.results(searchForm, bindingResult);

        assertNotNull(result.getModel().get("error"));
    }

    @Test
    public void resultsWillReturnSearchResponseIfExists() {
        SearchForm searchForm = new SearchForm();
        searchForm.setTitle("toFind");

        SearchResponse expected = new SearchResponse();
        expected.setItems(Collections.emptyList());
        expected.setHasMore(Boolean.TRUE);
        expected.setTitle("toFind");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(Boolean.FALSE);
        when(stackExchangeApiRESTService.getResponse(anyString(), anyInt())).thenReturn(expected);

        ModelAndView actual = searchController.results(searchForm, bindingResult);

        assertEquals(expected, actual.getModel().get("searchResponse"));
    }
}