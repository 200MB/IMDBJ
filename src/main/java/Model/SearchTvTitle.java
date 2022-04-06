package Model;

import java.util.LinkedHashSet;

public class SearchTvTitle {
    private String title;
    private String year;
    private String ageRating;
    private String duration; //in minutes
    private String imdbRating;
    private String popularity;
    private String director;
    private LinkedHashSet<String> writers;


    public SearchTvTitle(String title, String year, String ageRating, String duration, String imdbRating, String popularity, String director, LinkedHashSet<String> writers) {
        this.title = title;
        this.year = year;
        this.ageRating = ageRating;
        this.duration = duration;
        this.imdbRating = imdbRating;
        this.popularity = popularity;
        this.director = director;
        this.writers = writers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    @Override
    public String toString() {
        return "SearchMovie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", ageRating=" + ageRating +
                ", duration=" + duration +
                ", imdbRating=" + imdbRating +
                ", popularity=" + popularity +
                ", director=" + director +
                ", writers=" + writers +
                '}';
    }


}
