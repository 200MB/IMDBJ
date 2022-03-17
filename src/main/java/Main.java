import Api.IMDBJ;
import Model.SearchTitle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        IMDBJ api = new IMDBJ();
        ArrayList<SearchTitle> list = api.searchTitles("spiderman", 5, true);
        for (SearchTitle i : list) {
            System.out.println(i);
        }
    }
}
