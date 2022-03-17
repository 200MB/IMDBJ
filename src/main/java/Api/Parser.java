package Api;

import Model.SearchTitle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Parser {
    protected static SearchTitle parseToTitle(Document doc, Boolean ignore) {
        SearchTitle intance = new SearchTitle();
        ArrayList<Elements> arrElements = new ArrayList<>();
        Elements title = doc.select("h1[data-testid=hero-title-block__title]");
        Elements year = doc.select("span[class=sc-52284603-2 iTRONr]");
        Elements ageRating = doc.select("span[class=sc-52284603-2 iTRONr]");
        Elements duration = doc.select("ul[class=ipc-inline-list ipc-inline-list--show-dividers sc-52284603-0 blbaZJ baseAlt]");
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


        return setInstance(arrElements,intance,ignore);
    }

    private static SearchTitle setInstance(ArrayList<Elements>arrElements,SearchTitle instance,Boolean ignore) {
        int ind = 1;
        for (Elements i : arrElements) {
            if (i.size() == 0 && ignore){
                return null;
            }
            if (i.size() != 0) {
                if (i.size() > 1 && ind == 3) {
                    instance.setAgeRating(i.get(1).text());
                    ind++;
                    continue;
                }
                instance.setWhat(ind, Arrays.asList(i.get(0).text()));
            }
            if (i.size() == 1 && ignore && ind == 3){
                return null;
            }
            ind++;
        }
        return instance;
    }
}
