import Api.IMDBJ;
import Model.SearchCeleb;
import Model.SearchTitle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;


public class Main {
    public static void main(String[] args) throws IOException {
        IMDBJ api = new IMDBJ();
//        ArrayList<SearchTitle> list = api.searchTitles("antman", 5,false);
//        for (SearchTitle i : list) {
//            System.out.println(i);
//        }
//        LinkedHashSet<SearchCeleb> list = api.searchCelebs("", 25);
//        for (SearchCeleb i : list) {
//            System.out.println(i);
//        }
    }
}
