package ba.fsre.mojanovaaplikacija.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Movie {
    public String name;
    public String genera;
    public Long year;
    public String director;
    public String image;

    public Movie() {}

    public Movie(String name, String genera, Long year, String director, String image) {
        this.name = name;
        this.genera = genera;
        this.year = year;
        this.director = director;
        this.image = image;
    }
}