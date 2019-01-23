package ru.beleychev.pianotask.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.beleychev.pianotask.domain.SearchForm;
import ru.beleychev.pianotask.service.StackExchangeApiRESTService;

import javax.validation.Valid;

/**
 * @author beleychev
 */
@Controller
public class SearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    private final StackExchangeApiRESTService stackExchangeApiRESTService;

    public SearchController(StackExchangeApiRESTService stackExchangeApiRESTService) {
        this.stackExchangeApiRESTService = stackExchangeApiRESTService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:search";
    }

    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("searchForm", new SearchForm());
        return modelAndView;
    }

    @PostMapping("/results")
    public ModelAndView results(@Valid SearchForm searchForm, BindingResult bindingResult) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("search");
            return modelAndView;
        }
        return new ModelAndView("results");
    }
}