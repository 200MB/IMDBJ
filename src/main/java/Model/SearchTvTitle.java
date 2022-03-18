package Model;

import java.util.LinkedHashSet;
import java.util.List;

public class SearchTvTitle {
    private String title;
    private String year;
    private String ageRating;
    private String duration; //in minutes
    private double imdbRating;
    private String popularity;
    private String director;
    private LinkedHashSet<String> writers;

    public SearchTvTitle(String title, String year, String ageRating, String duration, double imdbRating, String popularity, String director, LinkedHashSet<String> writers) {
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

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriters(LinkedHashSet<String> writers) {
        this.writers = writers;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public String getDuration() {
        return duration;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getDirector() {
        return director;
    }

    public LinkedHashSet<String> getWriters() {
        return writers;
    }

    public void setWhat(int i, List<?> list) {
        switch (i) {
            case 1:
                setTitle(String.valueOf(list.get(0)));
                break;
            case 2:
                setYear((String.valueOf(list.get(0))));
                break;
            case 3:
                setAgeRating(String.valueOf(list.get(0)));
                break;
            case 4:
                setDuration((String.valueOf(list.get(0))));
                break;
            case 5:
                setImdbRating(Double.parseDouble(String.valueOf(list.get(0))));
                break;
            case 6:
                setPopularity((String.valueOf(list.get(0))));
                break;
            case 7:
                setDirector(String.valueOf(list.get(0)));
                break;

        }
    }

    @Override
    public String toString() {
        return "SearchTitle{" +
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
