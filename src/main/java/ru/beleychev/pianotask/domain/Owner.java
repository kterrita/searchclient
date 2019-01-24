package ru.beleychev.pianotask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author beleychev
 */
@JsonIgnoreProperties
public class Owner {

    @JsonProperty("display_name")
    private String displayName;

    public Owner() {
        //default constructor for jackson
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Owner owner = (Owner) o;
        return Objects.equals(displayName, owner.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}
