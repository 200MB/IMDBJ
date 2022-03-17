package Api;

import Model.SearchCeleb;
import Model.SearchTitle;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class IMDBJ {
    private String link;
    private ArrayList<Document> linkArrList;

    public ArrayList<SearchTitle> searchTitles(String name, int limit, Boolean ignore) throws IOException {
        link = SearchStrings.TITLE.formatted(name);
        ArrayList<SearchTitle> tempList = new ArrayList<>();
        getlinks(limit);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTitle(doc, ignore));
        }
        System.out.println("Ready!");
        return tempList;

    }

    public ArrayList<SearchTitle> searchTvTitles(String name, int limit) throws IOException {
        link = SearchStrings.TVEP.formatted(name);
        ArrayList<SearchTitle> tempList = new ArrayList<>();
        getlinks(limit);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToTitle(doc, false));
        }
        System.out.println("Ready!");
        return tempList;
    }

    public LinkedHashSet<SearchCeleb> searchCelebs(String name, int limit) throws IOException {
        name = name.replace(' ', '+');
        link = SearchStrings.CELEBS.formatted(name);
        LinkedHashSet<SearchCeleb> tempList = new LinkedHashSet<>();
        getlinksForCelebs(limit);
        System.out.println("Parsing...");
        for (Document doc : linkArrList) {
            tempList.add(Parser.parseToCeleb(doc, true));
        }
        System.out.println("Ready!");
        tempList.remove(null);
        return tempList;
    }

    public void searchByKeywords(String[] args) {

    }

    private void getlinks(int limit) throws IOException {
        Document doc = Jsoup.connect(link).get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("table[class=findList] td[class=result_text] a"); //(div id)
        ArrayList<Document> linkList = new ArrayList<>();
        int index = 0;
        System.out.println("Fetching...");
        for (Element element : newsHeadlines) {
            if (index == limit) {
                break;
            }
            System.out.println("https://www.imdb.com%s".formatted(element.attr("href")));
            Document tempDoc;
            try {
                tempDoc = Jsoup.connect("https://www.imdb.com%s".formatted(element.attr("href"))).get();
            }
            catch (HttpStatusException e){
                continue;
            }
            linkList.add(tempDoc);
            index++;
        }
        System.out.println("Done fetching!");
        linkArrList = linkList;
    }

    private void getlinksForCelebs(int limit) throws IOException {
        Document doc = Jsoup.connect(link).get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("table[class=findList] td[class=result_text] a"); //(div id)
        ArrayList<Document> linkList = new ArrayList<>();
        int index = 0;
        System.out.println("Fetching...");
        for (int i = 0;i< newsHeadlines.size();i++) {
            if (i % 2 != 0){
                continue;
            }
            Element element = newsHeadlines.get(i);
            if (index == limit) {
                break;
            }
            System.out.println("https://www.imdb.com%s".formatted(element.attr("href")));
            Document tempDoc;
            try {
                 tempDoc = Jsoup.connect("https://www.imdb.com%s".formatted(element.attr("href"))).get();
            }
            catch (HttpStatusException e){
                continue;
            }
            linkList.add(tempDoc);
            index++;
        }
        System.out.println("Done fetching!");
        linkArrList = linkList;

    }
}
