import Api.IMDBJ;
import Model.SearchTvTitle;

import java.io.IOException;


public class Main {
    //TODO: IMDBJ has methods that have similar algorithms needs to be more generic and requires optimizations
    public static void main(String[] args) throws IOException {
        IMDBJ api = new IMDBJ();
        for (SearchTvTitle title : api.getMostPopularTvShows(5)){
            System.out.println(title);
        }
    }
}
