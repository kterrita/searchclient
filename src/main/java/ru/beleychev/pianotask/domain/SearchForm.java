package ru.beleychev.pianotask.domain;

import javax.validation.constraints.NotBlank;

/**
 * @author beleychev
 */
public class SearchForm {
    @NotBlank(message = "Search field cannot be empty.")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
