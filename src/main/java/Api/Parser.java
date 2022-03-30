package Api;

import Model.SearchCeleb;
import Model.SearchMovie;
import Model.SearchTvTitle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Parser {
    protected static SearchMovie parseToMovie(Document doc, Boolean ignore) {
        SearchMovie intance = new SearchMovie();
        ArrayList<Elements> arrElements = new ArrayList<>();
        Elements title = doc.select("h1[data-testid=hero-title-block__title]");
        Elements year = doc.select("span[class=sc-52284603-2 iTRONr]");
        Elements ageRating = doc.select("span[class=sc-52284603-2 iTRONr]");
        Elements duration = doc.select("ul[class=ipc-inline-list ipc-inline-list--show-dividers sc-52284603-0 blbaZJ baseAlt] li");
        Elements imdbRating = doc.select("div[class=sc-7ab21ed2-2 kYEdvH] span[class=sc-7ab21ed2-1 jGRxWM]");
        Elements metaScore = doc.select("span[class=score-meta]");
        Elements popularity = doc.select("div[class=sc-edc76a2-1 gopMqI]");
        Elements director = doc.select("a[class=ipc-metadata-list-item__list-content-item ipc-metadata-list-item__list-content-item--link]");
        Elements writers = doc.select("ul[class=ipc-inline-list ipc-inline-list--show-dividers ipc-inline-list--inline ipc-metadata-list-item__list-content baseAlt] li");
        LinkedHashSet<String> tempList = new LinkedHashSet<>();
        for (Element element : writers) {
            tempList.add(element.text());
        }
        intance.setWriters(tempList);
        arrElements.add(title);
        arrElements.add(year);
        arrElements.add(ageRating);
        arrElements.add(duration);
        arrElements.add(imdbRating);
        arrElements.add(metaScore);
        arrElements.add(popularity);
        arrElements.add(director);
        arrElements.add(writers);

        return setInstance(arrElements, intance, ignore);
    }

    public static SearchTvTitle parseToTv(Document doc) {
        SearchMovie ins = parseToMovie(doc,false);
        return new SearchTvTitle(ins.getTitle(),ins.getYear(), ins.getAgeRating(), ins.getDuration(), ins.getImdbRating(),ins.getPopularity(),ins.getDirector(),ins.getWriters());
    }

    private static SearchMovie setInstance(ArrayList<Elements> arrElements, SearchMovie instance, Boolean ignore) {
        int ind = 1;
        for (Elements i : arrElements) {
            int durationIndex = 2;
            if (i.size() == 0 && ignore) {
                return null;
            }
            if (ind == 4){
                try{
                    if (i.size() > 3){
                        durationIndex = 3;
                    }
                    instance.setDuration(i.get(durationIndex).text());
                    ind++;
                    continue;
                }
                catch (IndexOutOfBoundsException e){
                    instance.setDuration(null);
                    ind++;
                    continue;
                }
            }
            if (ind == 3) {
                if (i.size() > 1) {
                    instance.setAgeRating(i.get(1).text());
                    ind++;
                    continue;
                } else {
                    instance.setAgeRating(null);
                    ind++;
                    continue;
                }
            }
            if (i.size() != 0) {
                instance.setWhat(ind, Arrays.asList(i.get(0).text()));
            }
            ind++;
        }

        return instance;
    }

    private static SearchTvTitle setInstanceForTv(ArrayList<Elements> arrElements, SearchTvTitle instance, Boolean ignore) {
        int ind = 1;
        for (Elements i : arrElements) {
            if (i.size() == 0 && ignore) {
                return null;
            }
            if (ind == 3) {
                if (i.size() > 1) {
                    instance.setAgeRating(i.get(1).text());
                    ind++;
                    continue;
                } else {
                    instance.setAgeRating(null);
                    ind++;
                    continue;
                }
            }
            if (i.size() != 0) {
                instance.setWhat(ind, Arrays.asList(i.get(0).text()));
            }
            ind++;
        }

        return instance;
    }


    protected static SearchCeleb parseToCeleb(Document doc, boolean ignore) {
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


        return setInstanceOfCeleb(instance, arrElements, ignore);
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

