package Model;

import java.util.LinkedHashSet;
import java.util.List;

public class SearchMovie {
    private String title;
    private String year;
    private String ageRating;
    private String duration;
    private double imdbRating;
    private int metaScore;
    private String popularity;
    private String director;
    private LinkedHashSet<String> writers;

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

    public void setMetaScore(int metaScore) {
        this.metaScore = metaScore;
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

    public int getMetaScore() {
        return metaScore;
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
            case 1 -> setTitle(String.valueOf(list.get(0)));
            case 2 -> setYear((String.valueOf(list.get(0))));
            case 3 -> setAgeRating(String.valueOf(list.get(0)));
            case 4 -> setDuration((String.valueOf(list.get(0))));
            case 5 -> setImdbRating(Double.parseDouble(String.valueOf(list.get(0))));
            case 6 -> setMetaScore(Integer.parseInt(String.valueOf(list.get(0))));
            case 7 -> setPopularity((String.valueOf(list.get(0))));
            case 8 -> setDirector(String.valueOf(list.get(0)));
        }
    }


    @Override
    public String toString() {
        return "SearchMovie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", ageRating=" + ageRating +
                ", duration=" + duration +
                ", imdbRating=" + imdbRating +
                ", metaScore=" + metaScore +
                ", popularity=" + popularity +
                ", director=" + director +
                ", writers=" + writers +
                '}';
    }


}
