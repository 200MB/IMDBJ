package Api;

import Enums.ElementStringModifier;
import Enums.Genre;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.*;

public class IMDBJ {
    private String link;
    private ArrayList<Document> linkArrList;
    private boolean ignoreLimit;

    /**
     *
     * @param name keyword for movie titles
     * @param limit amount to extract (everything above 20th search is usually just an undocumented page)
     * @param ignore if the scrapper should ignore the movie page that doesn't have every info to set class fields
     * @return information about each movie
     * @throws IOException
     */
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

    /**
     *
     * @param name keyword for the TvShow titles
     * @param limit amount to extract
     * @return information about each Tv show
     * @throws IOException
     */
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

    /**
     *
     * @param name keyword for celeb names
     * @param limit amount to extract (everything above 10th search is usually just an undocumented page)
     * @return (limit) amount of celebrity information
     * @throws IOException
     */
    public ArrayList<SearchCeleb> searchCelebs(String name, int limit) throws IOException {
        name = name.replace(' ', '+');
        link = SearchStrings.CELEBS.formatted(name);
        ArrayList<SearchCeleb> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.CELEBS);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToCeleb(doc));
        }
        System.out.println("Ready!");
        tempList.remove(null);
        return tempList;
    }

    /**
     *
     * @param limit amount to extract (can't exceed 250)
     * @return information about (limit) amount of titles from top 250 movies
     * @throws IOException
     */
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
    /**
     *
     * @param limit amount to extract (can't exceed 250)
     * @return information about (limit) amount of titles from top 250 Tv Shows
     * @throws IOException
     */
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
    /**
     *
     * @param limit amount to extract (can't exceed 100)
     * @return information about (limit) amount of titles from most Popular Movies
     * @throws IOException
     */
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
    /**
     *
     * @param limit amount to extract (can't exceed 100)
     * @return information about (limit) amount of titles from most popular Tv Shows
     * @throws IOException
     */
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

    /**
     *
     * @param genre movie genre
     * @param limit amount to extract (can't exceed 50)
     * @return information about (limit) amount of titles from top50Movies By genre
     * @throws IOException
     */
    public ArrayList<SearchMovie> getTop50MoviesByGenre(Genre genre, int limit) throws IOException {
        if (limit > 50) {
            throw new IllegalArgumentException("Limit Cant be more than 50");
        }
        link = SearchStrings.MOVIES_BY_GENRE.formatted(genre);
        ArrayList<SearchMovie> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.TOP50_GENRE);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToMovie(doc, false));
        }
        System.out.println("Ready!");
        return tempList;
    }

    /**
     *
     * @param genre genre
     * @param limit amount to extract (can't exceed 50)
     * @return information about (limit) amount of titles from top50TvShows By genre
     * @throws IOException
     */
    public ArrayList<SearchTvTitle> getTop50TvShowsByGenre(Genre genre, int limit) throws IOException {
        if (limit > 50) {
            throw new IllegalArgumentException("Limit Cant be more than 50");
        }
        link = SearchStrings.TV_BY_GENRE.formatted(genre);
        ArrayList<SearchTvTitle> tempList = new ArrayList<>();
        getlinks(limit, ElementStringModifier.TOP50_GENRE);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTv(doc));
        }
        System.out.println("Ready!");
        return tempList;
    }


    /**
     *
     * @param limit amount to extract
     * @param modifier tells code which cssquery to scrap after loading a page
     * @throws IOException
     */
    private void getlinks(int limit, ElementStringModifier modifier) throws IOException {
        ignoreLimit = false;
        if (limit == -1) {
            ignoreLimit = true;
        }
        Document doc = Jsoup.connect(link).get();
        System.out.println(doc.title());
        Elements elements = getElements(modifier, doc);
        ArrayList<Document> linkList = new ArrayList<>();
        int index = 0;
        int celebCounter = 0;
        System.out.println("Fetching...");
        assert elements != null;
        for (Element element : elements) {
            if (index == limit && !ignoreLimit) {
                break;
            }
            if (modifier == ElementStringModifier.CELEBS) {
                if (celebCounter % 2 != 0) {
                    celebCounter++;
                    continue;
                }
            }
            System.out.printf("https://www.imdb.com%s%n", element.attr("href"));
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

    /**
     *
     * @param month month
     * @param limit amount to extract
     * @return movies that will come out in (month)
     * @throws IOException
     * @throws ParseException
     */
    public ArrayList<SearchMovie> comingSoon(Month month, int limit) throws IOException, ParseException {
        String format;
        ArrayList<SearchMovie> tempList = new ArrayList<>();
        Calendar cal = setTime(month);
        String tempMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        format = tempMonth.length() == 2 ? tempMonth : "0" + tempMonth;
        link = SearchStrings.COMING_SOON.formatted(String.valueOf(Year.now()), format);
        getlinks(limit, ElementStringModifier.COMING_SOON);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToMovie(doc, false));
        }
        System.out.println("Ready!");
        return tempList;
    }

    private Calendar setTime(Month month) throws ParseException {
        Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(month.name());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    private Elements getElements(ElementStringModifier modifier, Document doc) {
        return switch (modifier) {
            case TOP250, TOP250TV -> doc.select("tbody[class=lister-list] tr td[class=titleColumn] a");
            case MOVIE, CELEBS -> doc.select("table[class=findList] td[class=result_text] a");
            case COMING_SOON -> doc.select("td[class=overview-top] h4 a");
            case TOP50_GENRE -> doc.select("div[class=lister-item-content] h3 a");
        };
    }


}
