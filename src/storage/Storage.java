package storage;

import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();

    //----------------------------
    public static void addProduktgruppe(Produktgruppe pg){
        produktgrupper.add(pg);
    }

    public static ArrayList<Produktgruppe> getProduktgrupper(){
        return new ArrayList<>(produktgrupper);
    }

    public static void removeProduktGruppe(Produktgruppe produktgruppe){
        if (produktgrupper.contains(produktgruppe)){
            produktgrupper.remove(produktgruppe);
        }
    }

    //----------------------------
}
