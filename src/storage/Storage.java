package storage;

import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;
import Applikation.Model.Salg;
import Applikation.Model.Salgstype;

import java.util.ArrayList;

public class Storage {


    private static ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();
    private static ArrayList<Salgstype> salgstyper = new ArrayList<>();
    private static ArrayList<Salg> salg = new ArrayList<>();


    //-------------Produktgrupper---------------
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

    //------------Salgstyper----------------

    public static void addSalgstype(Salgstype st){
        salgstyper.add(st);
    }

    public static ArrayList<Salgstype> getSalgstyper(){
        return new ArrayList<>(salgstyper);
    }

/*    public static void removeSalgstype(Salgstype salgstype){
        if (salgstyper.contains(salgstype)){
            salgstyper.remove(salgstype);
        }
    }*/


    public static void addSalg(Salg s){
        salg.add(s);
    }

    public static ArrayList<Salg> getSalg(){
        return new ArrayList<>(salg);
    }

}
