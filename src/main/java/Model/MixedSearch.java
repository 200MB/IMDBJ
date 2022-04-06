package Model;

import java.util.ArrayList;

public class MixedSearch {
    ArrayList<SearchMovie> movies = new ArrayList<>();
    ArrayList<SearchTvTitle> tv = new ArrayList<>();

    public ArrayList<SearchMovie> getMovies() {
        return movies;
    }

    public ArrayList<SearchTvTitle> getTv() {
        return tv;
    }
}
