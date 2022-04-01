import Api.IMDBJ;
import Enums.Genre;
import Model.SearchTvTitle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Main {
    //TODO: IMDBJ has methods that have similar algorithms needs to be more generic and requires optimizations
    public static void main(String[] args) throws IOException, ParseException {
        IMDBJ api = new IMDBJ();
        api.getTop50TvShowsByGenre(Genre.COMEDY,51).forEach(System.out::println);
    }
}
