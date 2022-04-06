package Api;

import Model.SearchCeleb;
import Model.SearchMovie;
import Model.SearchTvTitle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

public class Parser {
    protected static SearchMovie parseToMovie(Document doc) {
        SearchMovie intance = new SearchMovie();
        Element title = doc.selectFirst("h1[data-testid=hero-title-block__title]");
        Element year = doc.selectFirst("span[class=sc-52284603-2 iTRONr]");
        Elements ageRating = doc.select("span[class=sc-52284603-2 iTRONr]");
        Elements duration = doc.select("ul[class=ipc-inline-list ipc-inline-list--show-dividers sc-52284603-0 blbaZJ baseAlt] li");
        Element imdbRating = doc.selectFirst("div[class=sc-7ab21ed2-2 kYEdvH] span[class=sc-7ab21ed2-1 jGRxWM]");
        Element metaScore = doc.selectFirst("span[class=score-meta]");
        Element popularity = doc.selectFirst("div[class=sc-edc76a2-1 gopMqI]");
        Element director = doc.selectFirst("a[class=ipc-metadata-list-item__list-content-item ipc-metadata-list-item__list-content-item--link]");
        Elements writers = doc.select("ul[class=ipc-inline-list ipc-inline-list--show-dividers ipc-inline-list--inline ipc-metadata-list-item__list-content baseAlt] li");
        LinkedHashSet<String> tempList = new LinkedHashSet<>();
        for (Element element : writers) {
            tempList.add(element.text());
        }
        intance.setWriters(tempList);
        intance.setTitle(title == null ? null : title.text());
        intance.setYear(year == null ? null : year.text());
        intance.setImdbRating(imdbRating == null ? null : imdbRating.text());
        intance.setMetaScore(metaScore == null ? null : metaScore.text());
        intance.setPopularity(popularity == null ? null : popularity.text());
        intance.setDirector(director == null ? null : director.text());
        intance.setAgeRating(ageRating == null ? null : ageRating.size() > 1 ? ageRating.get(1).text() : "UxR");
        intance.setDuration(duration == null ? null : duration.size() > 2 ? duration.get(2).text() : "UxR");
        if (!isMovie(doc)) {
            try {
                intance.setAgeRating(ageRating.get(1).text());
            } catch (IndexOutOfBoundsException e) {
                intance.setAgeRating(null);
            }
            try {
                intance.setDuration(duration.get(3).text());
            } catch (IndexOutOfBoundsException e) {
                intance.setDuration(null);
            }
            return intance;
        }

        return intance;
    }

    public static SearchTvTitle parseToTv(Document doc) {
        SearchMovie ins = parseToMovie(doc);
        return new SearchTvTitle(ins.getTitle(), ins.getYear(), ins.getAgeRating(), ins.getDuration(), ins.getImdbRating(), ins.getPopularity(), ins.getDirector(), ins.getWriters());
    }

    public static Boolean isMovie(Document doc) {
        if (doc.selectFirst("ul[class=ipc-inline-list ipc-inline-list--show-dividers sc-52284603-0 blbaZJ baseAlt] li") != null) {
            return false;
        }
        return true;
    }


    protected static SearchCeleb parseToCeleb(Document doc) {
        SearchCeleb instance = new SearchCeleb();
        Elements professions = doc.select("div[class=infobar] a[href]");
        Elements birthYear = doc.select("time[datetime]");
        Elements birthCountry = doc.select("div[id=name-born-info] a");
        Elements filmography = doc.select("div[class=filmo-category-section] b a");
        ArrayList<String> profs = new ArrayList<>();
        LinkedHashSet<String> filmographyList = new LinkedHashSet<>();
        ArrayList<Elements> arrElements = new ArrayList<>();
        for (Element i : professions) {
            profs.add(i.text());
        }
        for (Element i : filmography) {
            filmographyList.add(i.text());
        }
        instance.setProfessions(profs);
        instance.setFimlography(filmographyList);
        arrElements.add(birthYear);
        arrElements.add(birthCountry);


        return setInstanceOfCeleb(instance, arrElements, true);
    }

    private static SearchCeleb setInstanceOfCeleb(SearchCeleb instance, ArrayList<Elements> arrElements, boolean ignore) {
        int ind = 1;
        for (Elements i : arrElements) {
            if (i.size() == 0 && ignore) {
                return null;
            }
            if (ind == 2) {
                instance.setBirthCountry(i.get(2).text());
                break;
            }
            instance.setWhat(ind, i.get(0).text());
            ind++;
        }
        return instance;
    }
}

