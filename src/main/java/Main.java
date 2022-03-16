import Api.IMDBJ;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        IMDBJ api = new IMDBJ();
        api.searchByKeywords(new String[]{"avengers","tony-stark"});
    }
}
