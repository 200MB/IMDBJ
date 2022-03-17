package Api;

import Model.SearchTitle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class IMDBJ {
    private String link;
    private ArrayList<Document> linkArrList;

    public ArrayList<SearchTitle> searchTitles(String name,int limit,Boolean ignore) throws IOException {
        link = SearchStrings.TITLE.formatted(name);
        ArrayList<SearchTitle> tempList = new ArrayList<>(); getlinks(limit);
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTitle(doc,ignore));
        }
        return tempList;

    }

    public void searchTvEpisodes(String name) throws IOException {
        link = SearchStrings.TVEP.formatted(name);
    }

    public void searchCelebs(String name) throws IOException {
        link = SearchStrings.CELEBS.formatted(name);
    }

    public void searchByKeywords(String[] args) {

    }

    private void getlinks(int limit) throws IOException {
        Document doc = Jsoup.connect(link).get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("table[class=findList] td[class=result_text] a"); //(div id)
        ArrayList<Document> linkList = new ArrayList<>();
        int index = 0;
        for (Element element : newsHeadlines) {
            if (index == limit){
                break;
            }
            Document tempDoc = Jsoup.connect("https://www.imdb.com/%s".formatted(element.attr("href"))).get();
            linkList.add(tempDoc);
            index++;
        }
        System.out.println("Done fetching!");
        linkArrList = linkList;
    }
}
