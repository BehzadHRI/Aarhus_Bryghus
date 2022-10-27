package Applikation.Controller;

import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;
import storage.Storage;

import java.util.ArrayList;

public class Controller {
    public static Produktgruppe createProduktGruppe(String navn){
        Produktgruppe pg = new Produktgruppe(navn);
        Storage.addProduktgruppe(pg);
        return pg;
    }

    public static ArrayList<Produktgruppe> getProduktGrupper() {
        return Storage.getProduktgrupper();
    }





    //---------------------------
    public static void init(){
        Produktgruppe pg1 = Controller.createProduktGruppe("Øl");
        Produktgruppe pg2 = Controller.createProduktGruppe("Julebryg");
        Produktgruppe pg3 = Controller.createProduktGruppe("Vin");


    }

}
