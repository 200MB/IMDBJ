package Api;

import Enums.ElementStringModifier;
import Enums.SearchStrings;
import Model.SearchCeleb;
import Model.SearchMovie;
import Model.SearchTvTitle;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class IMDBJ {
    private String link;
    private ArrayList<Document> linkArrList;

    public ArrayList<SearchMovie> searchMovies(String name, int limit, Boolean ignore) throws IOException {
        link = SearchStrings.MOVIE.formatted(name);
        ArrayList<SearchMovie> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.MOVIE);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToMovie(doc, ignore));
        }
        System.out.println("Ready!");
        return tempList;

    }

    public ArrayList<SearchTvTitle> searchTvTitles(String name, int limit) throws IOException {
        link = SearchStrings.TVEP.formatted(name);
        ArrayList<SearchTvTitle> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.MOVIE);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTv(doc));
        }
        System.out.println("Ready!");
        return tempList;
    }

    public ArrayList<SearchCeleb> searchCelebs(String name, int limit) throws IOException {
        name = name.replace(' ', '+');
        link = SearchStrings.CELEBS.formatted(name);
        ArrayList<SearchCeleb> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.CELEBS);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToCeleb(doc, true));
        }
        System.out.println("Ready!");
        tempList.remove(null);
        return tempList;
    }

    public ArrayList<SearchMovie> getTop250Movies(int limit) throws IOException {
        if (limit > 250) {
            throw new IllegalArgumentException("Limit Cant be more than 250");
        }
        link = SearchStrings.TOP250;
        ArrayList<SearchMovie> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.TOP250);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToMovie(doc, false));
        }
        System.out.println("Ready!");
        return tempList;
    }

    public ArrayList<SearchTvTitle> getTop250TvShows(int limit) throws IOException {
        if (limit > 250) {
            throw new IllegalArgumentException("Limit Cant be more than 250");
        }
        link = SearchStrings.TOP250TV;
        getlinks(limit, ElementStringModifier.TOP250TV);
        ArrayList<SearchTvTitle> tempList = new ArrayList<>();
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTv(doc));
        }
        System.out.println("Ready!");
        return tempList;

    }

    public ArrayList<SearchMovie> getMostPopularMovies(int limit) throws IOException {
        if (limit > 100) {
            throw new IllegalArgumentException("Limit Cant be more than 100");
        }
        link = SearchStrings.MOST_POPULAR_MOVIES; //only link changes
        ArrayList<SearchMovie> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.TOP250);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToMovie(doc, false));
        }
        System.out.println("Ready!");
        return tempList;
    }

    public ArrayList<SearchTvTitle> getMostPopularTvShows(int limit) throws IOException {
        if (limit > 100) {
            throw new IllegalArgumentException("Limit Cant be more than 100");
        }
        link = SearchStrings.MOST_POPULAR_TV_SHOWS; //only link changes
        ArrayList<SearchTvTitle> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.TOP250);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTv(doc));
        }
        System.out.println("Ready!");
        return tempList;
    }


    private void getlinks(int limit, ElementStringModifier modifier) throws IOException {
        Document doc = Jsoup.connect(link).get();
        System.out.println(doc.title());
        Elements elements = getElements(modifier, doc);
        ArrayList<Document> linkList = new ArrayList<>();
        int index = 0;
        int celebCounter = 0;
        System.out.println("Fetching...");
        for (Element element : elements) {
            if (index == limit) {
                break;
            }
            if (modifier == ElementStringModifier.CELEBS) {
                if (celebCounter % 2 != 0) {
                    celebCounter++;
                    continue;
                }
            }
            System.out.println("https://www.imdb.com%s".formatted(element.attr("href")));
            Document tempDoc;
            try {
                tempDoc = Jsoup.connect("https://www.imdb.com%s".formatted(element.attr("href"))).get();
            } catch (HttpStatusException e) {
                continue;
            }
            linkList.add(tempDoc);
            index++;
            celebCounter++;
        }
        System.out.println("Done fetching!");
        linkArrList = linkList;
    }


    private Elements getElements(ElementStringModifier modifier, Document doc) {
        switch (modifier) {
            case TOP250:
            case TOP250TV:
                return doc.select("tbody[class=lister-list] tr td[class=titleColumn] a");
            case MOVIE:
            case CELEBS:
                return doc.select("table[class=findList] td[class=result_text] a");
        }
        return null;
    }


}
