package ru.beleychev.pianotask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author beleychev
 */
@JsonIgnoreProperties
public class SearchResponse {

    @JsonProperty("items")
    private List<Item> items;

    @JsonProperty("has_more")
    private boolean hasMore;

    @NotBlank
    private String title;

    public SearchResponse() {
        //default constructor for jackson
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

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
        SearchResponse that = (SearchResponse) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "items=" + items +
                ", hasMore=" + hasMore +
                ", title='" + title + '\'' +
                '}';
    }
}
