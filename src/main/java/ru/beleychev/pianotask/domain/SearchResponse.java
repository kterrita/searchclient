package ru.beleychev.pianotask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public SearchResponse() {
        //default constructor for jackson
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
                '}';
    }
}
