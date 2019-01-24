package ru.beleychev.pianotask.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SearchForm that = (SearchForm) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "title='" + title + '\'' +
                '}';
    }
}
