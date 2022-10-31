package Applikation.Controller;

import Applikation.Model.*;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

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



    public static Salg createSalg(LocalDateTime datotid) {
        Salg s = new Salg(datotid);
        Storage.addSalg(s);
        return s;
    }

    public static ArrayList<Salg> getSalg() {
        return Storage.getSalg();
    }






//    public static void removeSalgstype(Salgstype salgstype) {
//        if (salgstype.getSalgstyper().isEmpty()) {
//            Storage.removeSalgstype(salgstype);
//        }
//    }






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
        Produkt p3 = Controller.createProdukt("Sweet Georgia Pilsner", pg1);
        Produkt p4 = Controller.createProdukt("Extra Pilsner", pg1);
        Produkt p5 = Controller.createProdukt("Celebration", pg1);
        Produkt p6 = Controller.createProdukt("Blondie", pg1);
        Produkt p7 = Controller.createProdukt("ForårsBryg", pg1);
        Produkt p8 = Controller.createProdukt("India Pale Ale", pg1);
        Produkt p9 = Controller.createProdukt("Julebryg", pg1);
        Produkt p10 = Controller.createProdukt("Juletønden", pg1);
        Produkt p11 = Controller.createProdukt("Old Strong Ale", pg1);
        Produkt p12 = Controller.createProdukt("Fregatten Jylland", pg1);
        Produkt p13 = Controller.createProdukt("Inperial Strout", pg1);
        Produkt p14 = Controller.createProdukt("Tribute", pg1);
        Produkt p15 = Controller.createProdukt("Black Monster", pg1);


        Produkt p16 = Controller.createProdukt("Klosterbryg", pg2);
        Produkt p17 = Controller.createProdukt("Whisky 45% 50cl rør", pg3);
        Produkt p18 = Controller.createProdukt("Klosterbryg, 20 liter", pg4);


        Salgstype st1 = Controller.createSalgstype("JuleFest");
        Salgstype st2 = Controller.createSalgstype("FredagsBar");
        Salgstype st3 = Controller.createSalgstype("Butik");

        Salg s = Controller.createSalg(LocalDateTime.of(2022, 10, 29, 21, 33));

        //ProduktGruppe = Flaske & Salgstype = FredagsBar
        Controller.createPrisForSalgsType(st2, p1,130 , 0);
        Controller.createPrisForSalgsType(st2, p2,70 , 2);
        Controller.createPrisForSalgsType(st2, p3,70 , 2);
        Controller.createPrisForSalgsType(st2, p4,70 , 2);
        Controller.createPrisForSalgsType(st2, p5,70 , 2);
        Controller.createPrisForSalgsType(st2, p6,70 , 2);
        Controller.createPrisForSalgsType(st2, p7,70 , 2);
        Controller.createPrisForSalgsType(st2, p8,70 , 2);
        Controller.createPrisForSalgsType(st2, p9,70 , 2);
        Controller.createPrisForSalgsType(st2, p10,70 , 2);
        Controller.createPrisForSalgsType(st2, p11,70 , 2);
        Controller.createPrisForSalgsType(st2, p12,70 , 2);
        Controller.createPrisForSalgsType(st2, p13,70 , 2);
        Controller.createPrisForSalgsType(st2, p14,70 , 2);
        Controller.createPrisForSalgsType(st2, p15,100 , 3);

        //Produktgrupper = Flaske & Salgstype = Butik
        Controller.createPrisForSalgsType(st3, p1,130 , 0);
        Controller.createPrisForSalgsType(st3, p2,36 , 2);
        Controller.createPrisForSalgsType(st3, p3,36 , 2);
        Controller.createPrisForSalgsType(st3, p4,36 , 2);
        Controller.createPrisForSalgsType(st3, p5,36 , 2);
        Controller.createPrisForSalgsType(st3, p6,36 , 2);
        Controller.createPrisForSalgsType(st3, p7,36 , 2);
        Controller.createPrisForSalgsType(st3, p8,36 , 2);
        Controller.createPrisForSalgsType(st3, p9,36 , 2);
        Controller.createPrisForSalgsType(st3, p10,36 , 2);
        Controller.createPrisForSalgsType(st3, p11,36 , 2);
        Controller.createPrisForSalgsType(st3, p12,36 , 2);
        Controller.createPrisForSalgsType(st3, p13,36 , 2);
        Controller.createPrisForSalgsType(st3, p14,36 , 2);
        Controller.createPrisForSalgsType(st3, p15,60 , 3);


    }

}
