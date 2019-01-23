package ru.beleychev.pianotask.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.beleychev.pianotask.domain.Item;
import ru.beleychev.pianotask.domain.SearchForm;
import ru.beleychev.pianotask.domain.SearchResponse;
import ru.beleychev.pianotask.service.StackExchangeApiRESTService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author beleychev
 */
@RestController
public class SearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    private final StackExchangeApiRESTService stackExchangeApiRESTService;

    public SearchController(StackExchangeApiRESTService stackExchangeApiRESTService) {
        this.stackExchangeApiRESTService = stackExchangeApiRESTService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("searchForm", new SearchForm());
        return modelAndView;
    }

    @PostMapping("/results")
    public ModelAndView results(@Valid SearchForm searchForm, BindingResult bindingResult) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return modelAndView;
        }
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setTitle(searchForm.getTitle());
        return results(searchResponse, "1");
    }

    @PostMapping("/results/{page}")
    public ModelAndView results(@Valid SearchResponse searchResponse,
            @PathVariable @Pattern(regexp = "[\\d]+") String page) {
        LOGGER.debug("Request for questions. Details: {}, page: {}", searchResponse.getTitle(), page);
        Integer intPage = Integer.valueOf(page);
        ModelAndView modelAndView;
        SearchResponse response = stackExchangeApiRESTService.getResponse(searchResponse.getTitle(), intPage);
        modelAndView = new ModelAndView("results");
        modelAndView.addObject("searchResponse", response);
        modelAndView.addObject("page", intPage);
        return modelAndView;
    }
}
