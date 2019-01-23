package ru.beleychev.pianotask.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.beleychev.pianotask.domain.SearchForm;
import ru.beleychev.pianotask.service.StackExchangeApiRESTService;

import javax.validation.Valid;

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
    public ResponseEntity<ModelAndView> results(@Valid SearchForm searchForm, BindingResult bindingResult) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("index");
            return new ResponseEntity<>(modelAndView, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ModelAndView("index"), HttpStatus.OK);
    }
}
