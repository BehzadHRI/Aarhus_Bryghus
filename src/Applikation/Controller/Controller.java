package Applikation.Controller;

import Applikation.Model.*;
import storage.Storage;

import java.util.ArrayList;

public class Controller {
    public static Produktgruppe createProduktGruppe(String navn) {
        Produktgruppe pg = new Produktgruppe(navn);
        Storage.addProduktgruppe(pg);
        return pg;
    }

    public static ArrayList<Produktgruppe> getProduktGrupper() {
        return Storage.getProduktgrupper();
    }

    public static void removeProduktGruppe(Produktgruppe produktgruppe) {
        if (produktgruppe.getProdukter().isEmpty()) {
            Storage.removeProduktGruppe(produktgruppe);
        }
    }


    //----------------------

    public static Produkt createProdukt(String navn, Produktgruppe pg) {
        Produkt produkt = new Produkt(navn, pg);
        return produkt;
    }

    public static void removeProdukt(Produkt produkt, Produktgruppe produktgruppe){
        produktgruppe.removeProdukt(produkt);
    }


    //---------Salgstype-------------
    public static Salgstype createSalgstype(String navn) {
        Salgstype st = new Salgstype(navn);
        Storage.addSalgstype(st);
        return st;
    }

    public static ArrayList<Salgstype> getSalgstyper() {
        return Storage.getSalgstyper();
    }

    public static Pris createPrisForSalgsType(Salgstype st, Produkt produkt, int pris, int klip){
        Pris pr = st.createPris(pris, klip, produkt, st);
        return pr;
    }
//    public static void removeSalgstype(Salgstype salgstype) {
//        if (salgstype.getSalgstyper().isEmpty()) {
//            Storage.removeSalgstype(salgstype);
//        }
//    }

    public static Salgslinje createSalgsLinje(Salg salg, int antal, Pris pris){
        return salg.createSalgslinje(antal, pris);
    }

    public static int getSumforSalg(Salg salg){
        int sum = 0;
        for (Salgslinje sl : salg.getAntals()){
            sum += sl.getAntal()*sl.getPris().getPris();
        }
        return sum;
    }





    //---------------------------
    public static void init() {
        Produktgruppe klip = Controller.createProduktGruppe("diverse");
        Produktgruppe pg1 = Controller.createProduktGruppe("Flaske");
        Produktgruppe pg2 = Controller.createProduktGruppe("Fadøl, 40 cl");
        Produktgruppe pg3 = Controller.createProduktGruppe("Spiritus");
        Produktgruppe pg4 = Controller.createProduktGruppe("Fustage");
        Produktgruppe pg5 = Controller.createProduktGruppe("Kulsyre");
        Produktgruppe pg6 = Controller.createProduktGruppe("Malt");
        Produktgruppe pg7 = Controller.createProduktGruppe("Beklædning");
        Produktgruppe pg8 = Controller.createProduktGruppe("Anlæg");
        Produktgruppe pg9 = Controller.createProduktGruppe("Glas");
        Produktgruppe pg10 = Controller.createProduktGruppe("Sampakninger");
        Produktgruppe pg11 = Controller.createProduktGruppe("Rundvisning");

        Produkt p1 = Controller.createProdukt("Klippekort, 4 klip", klip);
        Produkt p2 = Controller.createProdukt("Klosterbryg", pg1);
        Produkt p3 = Controller.createProdukt("Klosterbryg", pg2);
        Produkt p4 = Controller.createProdukt("Whisky 45% 50cl rør", pg3);
        Produkt p5 = Controller.createProdukt("Klosterbryg, 20 liter", pg4);

        Salgstype st1 = Controller.createSalgstype("JuleFest");
        Salgstype st2 = Controller.createSalgstype("FredagsBar");
        Salgstype st3 = Controller.createSalgstype("Butik");

        Controller.createPrisForSalgsType(st3, p1,10 , 2);
        Controller.createPrisForSalgsType(st3, p2,100 , 2);
        Controller.createPrisForSalgsType(st3, p3,101 , 2);
        Controller.createPrisForSalgsType(st3, p4,90 , 2);
        Controller.createPrisForSalgsType(st3, p5,23 , 2);






    }

}
