package ru.beleychev.pianotask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;

/**
 * @author beleychev
 */
@JsonIgnoreProperties
public class Item {

    @JsonProperty("link")
    private String link;

    @JsonProperty("creation_date")
    private Instant creationDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("is_answered")
    private boolean isAnswered;

    @JsonProperty("owner")
    private Owner owner;

    public Item() {
        //default constructor for jackson
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return isAnswered == item.isAnswered &&
                Objects.equals(link, item.link) &&
                Objects.equals(creationDate, item.creationDate) &&
                Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, creationDate, title, isAnswered);
    }

    @Override
    public String toString() {
        return "Item{" +
                "link='" + link + '\'' +
                ", creationDate=" + creationDate +
                ", title='" + title + '\'' +
                ", isAnswered=" + isAnswered +
                '}';
    }
}
