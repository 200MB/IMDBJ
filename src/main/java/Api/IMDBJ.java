package Api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IMDBJ {
    private String link;

    public void searchTitles(String name) throws IOException {
        link = SearchStrings.TITLE.formatted(name);
        search();
    }

    public void searchTvEpisodes(String name) throws IOException {
        link = SearchStrings.TVEP.formatted(name);
        search();
    }

    public void searchCelebs(String name) throws IOException {
        link = SearchStrings.CELEBS.formatted(name);
        search();
    }

    private void search() throws IOException {
        Document doc = Jsoup.connect(link).get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("table[class=findList] td[class=result_text] a"); //(div id)
        for (Element element : newsHeadlines) {
            System.out.println(element.text() + "-" + "https://www.imdb.com/%s".formatted(element.attr("href")));
        }
    }
}
