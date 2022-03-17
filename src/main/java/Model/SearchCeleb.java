package Model;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SearchCeleb {
    private ArrayList<String> professions;
    private String birthYear;
    private String birthCountry;
    private LinkedHashSet<String>fimlography;

    public LinkedHashSet<String> getFimlography() {
        return fimlography;
    }

    public void setFimlography(LinkedHashSet<String> fimlography) {
        this.fimlography = fimlography;
    }

    public ArrayList<String> getProfessions() {
        return professions;
    }

    public void setProfessions(ArrayList<String> professions) {
        this.professions = professions;
    }


    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public void setWhat(int index, String input) {
        switch(index){
            case 1:
                setBirthYear(input);
            case 2:
                setBirthCountry(input);
        }
    }

    @Override
    public String toString() {
        return "SearchCeleb{" +
                "professions=" + professions +
                ", birthYear='" + birthYear + '\'' +
                ", birthCountry='" + birthCountry + '\'' +
                ", fimlography=" + fimlography +
                '}';
    }
}