package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Travel {
    public int id;
    public String name;
    public String continent;
    public String description;
    public List<String> places;
    public Travel(int id, String name, String continent, String description, List<String> places) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.description = description;
        this.places = places != null ? new ArrayList<>(places) : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return id == travel.id && Objects.equals(name, travel.name) && Objects.equals(continent, travel.continent) && Objects.equals(description, travel.description) && Objects.equals(places, travel.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, continent, description, places);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", description='" + description + '\'' +
                ", places=" + places +
                '}';
    }
}
