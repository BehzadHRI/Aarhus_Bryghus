package Applikation.Controller;

import Applikation.Model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    public static Produktgruppe createProduktGruppe(String navn, boolean kanUdlejes) {
        Produktgruppe pg = new Produktgruppe(navn, kanUdlejes);
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


    //---------------------- produkter metoder

    public static Produkt createProdukt(String navn, Produktgruppe pg) {
        Produkt produkt = new Produkt(navn, pg);
        return produkt;
    }

    public static void removeProdukt(Produkt produkt, Produktgruppe produktgruppe) {
        produktgruppe.removeProdukt(produkt);
    }


    //---------Salgstype-------------
    public static Salgstype createSalgstype(String navn) {
        Salgstype st = new Salgstype(navn);
        Storage.addSalgstype(st);
        return st;
    }

    public static ArrayList<Pris> getPriserforProdGruppeOgSalgstype(Produktgruppe pg, Salgstype salgstype){
        ArrayList<Pris> result = new ArrayList<>();
        for (Pris pris : salgstype.getPriser()){
            if(pris.getProdukt().getProduktgruppe() == pg){
                result.add(pris);
            }
        }
        return result;
    }

    public static ArrayList<Salgstype> getSalgstyper() {
        return Storage.getSalgstyper();
    }

    public static Pris createPrisForSalgsType(Salgstype st, Produkt produkt, int pris, int klip) {
        Pris pr = st.createPris(pris, klip, produkt, st);
        return pr;
    }
//    public static void removeSalgstype(Salgstype salgstype) {
//        if (salgstype.getSalgstyper().isEmpty()) {
//            Storage.removeSalgstype(salgstype);
//        }
//    }

    public static void removePrisFraSalgstype(Salgstype salgstype, Pris pris) {
        salgstype.removePris(pris);
    }

    public static Salg createSalg(Salg salg) {
        Storage.addSalg(salg);
        return salg;
    }

    public static void setDatoTidforSalg(Salg salg, LocalDateTime tid) {
        salg.setDatoTid(tid);
    }


    public static Salgslinje createSalgsLinjeforSalg(Salg salg, int antal, Pris pris){

        return salg.createSalgslinje(antal, pris);
    }

    public static void removeSalgslinjefraSalg(Salg salg, Salgslinje salgslinje) {
        salg.removeSalgslinje(salgslinje);
    }

    public static Salgslinje createSalgslinjeForUdlejning(Udlejning udlejning, int antal, Pris pris){
        return Controller.createSalgsLinjeforSalg(udlejning,antal,pris);
    }

    public static void removeSalgslinjeFraUdlejning(Salg salg, Salgslinje salgslinje){
        salg.removeSalgslinje(salgslinje);
    }



    public static void setRabatforSalg(Salg salg, int rabat){
        salg.setRabat(rabat);
    }

    public static void setBetalingsmetode(Salg salg, String betalingsmetode) {
        salg.setBetalingsMetode(betalingsmetode);
    }

    public static ArrayList<Salg> getSalgPåDato(LocalDate dato) {
        int year = dato.getYear();
        int month = dato.getMonthValue();
        int day = dato.getDayOfMonth();

        ArrayList<Salg> result = new ArrayList<>();
        for (Salg sa : Storage.getSalg()) {
            if (sa.getDatoTid().getDayOfMonth() == day && sa.getDatoTid().getMonthValue() == month && sa.getDatoTid().getYear() == year) {
                result.add(sa);
            }
        }
        return result;
    }

    public static int getPrisforDagensSalg(LocalDate date) {
        int pris = 0;
        ArrayList<Salg> result = getSalgPåDato(date);
        for (Salg sa : result) {
            pris += sa.getSamletPris();
        }
        return pris;
    }

    public static void brugKlipPåSalg(Salg salg) {
        salg.brugKlip();
    }

    public static int getSolgteKlipIPeriode(LocalDate fraDatoInp, LocalDate tilDatoInp) {
        int result = 0;
        LocalDateTime fraDato = LocalDateTime.of(fraDatoInp.getYear(), fraDatoInp.getMonthValue(), fraDatoInp.getDayOfMonth(), 0, 0);
        LocalDateTime tilDato = LocalDateTime.of(tilDatoInp.getYear(), tilDatoInp.getMonthValue(), tilDatoInp.getDayOfMonth(), 0, 0);
        for (Salg salg : Storage.getSalg()) {
            for (Salgslinje salgslinje : salg.getSalgslinjer()) {
                if (salgslinje.getPris().getProdukt().getNavn().equals("Klippekort, 4 klip")
                        && salg.getDatoTid().isBefore(tilDato.plusDays(1))
                        && salg.getDatoTid().isAfter(fraDato.minusDays(1))) {
                    result += 4;
                }
            }
        }
            return result;
    }

    public static int getBrugteKlipPeriode(LocalDate fraDatoInp, LocalDate tilDatoInp){
        int result = 0;
        LocalDateTime fraDato = LocalDateTime.of(fraDatoInp.getYear(), fraDatoInp.getMonthValue(), fraDatoInp.getDayOfMonth(), 0, 0);
        LocalDateTime tilDato = LocalDateTime.of(tilDatoInp.getYear(), tilDatoInp.getMonthValue(), tilDatoInp.getDayOfMonth(), 0, 0);
        for (Salg salg : Storage.getSalg()) {
                if (    salg.isKlipBrugt()
                        && salg.getDatoTid().isBefore(tilDato.plusDays(1))
                        && salg.getDatoTid().isAfter(fraDato.minusDays(1))) {
                    result +=salg.getFuldklip();
            }
        }
        return result;
    }

    public static ArrayList<Udlejning> getAktiveUdlejninger(){
        ArrayList<Udlejning> result = new ArrayList<>();
        for (Salg salg : Storage.getSalg()){
            if(salg.getClass() == Udlejning.class){
                result.add((Udlejning) salg);
            }
        }
        return result;
    }

    public static void setKundeForUdlejning(Udlejning udlejning, Kunde kunde){
        udlejning.setKunde(kunde);
    }

    public static int beregnPantforUdlejning(Udlejning udlejning){
        return udlejning.beregnPant();
    }

    public static void returnereSalgslinjeForUdlejning(Salgslinje salgslinje, int antal, Udlejning udlejning){
        udlejning.angivReturProd(salgslinje, antal);
    }

    //---------------------------
    public static void init() {
        Salgstype fredagsBar = Controller.createSalgstype("FredagsBar");
        Salgstype butik = Controller.createSalgstype("Butik");

        Produktgruppe klip = Controller.createProduktGruppe("diverse", false);
        Produktgruppe flaske = Controller.createProduktGruppe("Flaske", false);
        Produktgruppe fadøl40cl = Controller.createProduktGruppe("Fadøl, 40 cl", false);
        Produktgruppe spiritus = Controller.createProduktGruppe("Spiritus", false);
        Produktgruppe fustage = Controller.createProduktGruppe("Fustage", true);
        Produktgruppe kulsyre = Controller.createProduktGruppe("Kulsyre", true);
        Produktgruppe malt = Controller.createProduktGruppe("Malt", false);
        Produktgruppe beklædning = Controller.createProduktGruppe("Beklædning", false);
        Produktgruppe anlæg = Controller.createProduktGruppe("Anlæg", true);
        Produktgruppe glas = Controller.createProduktGruppe("Glas", false);
        Produktgruppe sampakninger = Controller.createProduktGruppe("Sampakninger", false);
        Produktgruppe rundvisning = Controller.createProduktGruppe("Rundvisning", false);

        Produkt p1 = Controller.createProdukt("Klippekort, 4 klip", klip);
        Pris pr1 = Controller.createPrisForSalgsType(fredagsBar, p1, 130, 0);
        Pris pr111 = Controller.createPrisForSalgsType(butik, p1, 130, 0);


        Produkt p2 = Controller.createProdukt("Klosterbryg", flaske);
        Produkt p3 = Controller.createProdukt("Sweet Georgia Brown", flaske);
        Produkt p4 = Controller.createProdukt("Extra Pilsner", flaske);
        Produkt p5 = Controller.createProdukt("Celebration", flaske);
        Produkt p6 = Controller.createProdukt("Blondie", flaske);
        Produkt p7 = Controller.createProdukt("India Pale Ale", flaske);
        Produkt p8 = Controller.createProdukt("Julebryg", flaske);
        Produkt p9 = Controller.createProdukt("Juletønden", flaske);
        Produkt p10 = Controller.createProdukt("Old Strong Ale", flaske);
        Produkt p11 = Controller.createProdukt("Fregatten Jylland", flaske);
        Produkt p12 = Controller.createProdukt("Imperial Stout", flaske);
        Produkt p13 = Controller.createProdukt("Tribute ", flaske);
        Produkt p14 = Controller.createProdukt("Black Monster ", flaske);
        Produkt p15 = Controller.createProdukt("Forårsbryg", flaske);

        Pris pr2 = Controller.createPrisForSalgsType(fredagsBar, p2, 70, 2);
        Pris pr3 = Controller.createPrisForSalgsType(butik, p2, 36, 0);
        Pris pr4 = Controller.createPrisForSalgsType(fredagsBar, p3, 70, 2);
        Pris pr5 = Controller.createPrisForSalgsType(butik, p3, 36, 0);
        Pris pr6 = Controller.createPrisForSalgsType(fredagsBar, p4, 70, 2);
        Pris pr7 = Controller.createPrisForSalgsType(butik, p4, 36, 0);
        Pris pr8 = Controller.createPrisForSalgsType(fredagsBar, p5, 70, 2);
        Pris pr9 = Controller.createPrisForSalgsType(butik, p5, 36, 0);
        Pris pr10 = Controller.createPrisForSalgsType(fredagsBar, p6, 70, 2);
        Pris pr11 = Controller.createPrisForSalgsType(butik, p6, 36, 0);
        Pris pr12 = Controller.createPrisForSalgsType(fredagsBar, p7, 70, 2);
        Pris pr13 = Controller.createPrisForSalgsType(butik, p7, 36, 0);
        Pris pr14 = Controller.createPrisForSalgsType(fredagsBar, p8, 70, 2);
        Pris pr15 = Controller.createPrisForSalgsType(butik, p8, 36, 0);
        Pris pr16 = Controller.createPrisForSalgsType(fredagsBar, p9, 70, 2);
        Pris pr17 = Controller.createPrisForSalgsType(butik, p9, 36, 0);
        Pris pr18 = Controller.createPrisForSalgsType(fredagsBar, p10, 70, 2);
        Pris pr19 = Controller.createPrisForSalgsType(butik, p10, 36, 0);
        Pris pr20 = Controller.createPrisForSalgsType(fredagsBar, p11, 70, 2);
        Pris pr21 = Controller.createPrisForSalgsType(butik, p11, 36, 0);
        Pris pr22 = Controller.createPrisForSalgsType(fredagsBar, p12, 70, 2);
        Pris pr23 = Controller.createPrisForSalgsType(butik, p12, 36, 0);
        Pris pr24 = Controller.createPrisForSalgsType(fredagsBar, p13, 70, 2);
        Pris pr25 = Controller.createPrisForSalgsType(butik, p13, 36, 0);
        Pris pr26 = Controller.createPrisForSalgsType(fredagsBar, p14, 70, 2);
        Pris pr27 = Controller.createPrisForSalgsType(butik, p14, 36, 0);
        Pris pr28 = Controller.createPrisForSalgsType(fredagsBar, p15, 100, 3);
        Pris pr29 = Controller.createPrisForSalgsType(butik, p15, 60, 0);


        Produkt p16 = Controller.createProdukt("Klosterbryg", fadøl40cl);
        Produkt p17 = Controller.createProdukt("Jazz Classic", fadøl40cl);
        Produkt p18 = Controller.createProdukt("Extra Pilsner", fadøl40cl);
        Produkt p19 = Controller.createProdukt("Celebration", fadøl40cl);
        Produkt p20 = Controller.createProdukt("Blondie", fadøl40cl);
        Produkt p21 = Controller.createProdukt("Forårsbryg", fadøl40cl);
        Produkt p22 = Controller.createProdukt("India Pale Ale", fadøl40cl);
        Produkt p23 = Controller.createProdukt("Julebryg", fadøl40cl);
        Produkt p24 = Controller.createProdukt("Imperial Stout", fadøl40cl);
        Produkt p25 = Controller.createProdukt("Special", fadøl40cl);
        Produkt p26 = Controller.createProdukt("Æblebrus", fadøl40cl);
        Produkt p27 = Controller.createProdukt("chips", fadøl40cl);
        Produkt p28 = Controller.createProdukt("peanuts", fadøl40cl);
        Produkt p29 = Controller.createProdukt("cola", fadøl40cl);
        Produkt p30 = Controller.createProdukt("Nikoline", fadøl40cl);
        Produkt p31 = Controller.createProdukt("7-Up", fadøl40cl);
        Produkt p32 = Controller.createProdukt("vand", fadøl40cl);
        Produkt p33 = Controller.createProdukt("Ølpølser", fadøl40cl);

        Pris pr30 = Controller.createPrisForSalgsType(fredagsBar, p16, 38, 1);
        Pris pr31 = Controller.createPrisForSalgsType(fredagsBar, p17, 38, 1);
        Pris pr32 = Controller.createPrisForSalgsType(fredagsBar, p18, 38, 1);
        Pris pr33 = Controller.createPrisForSalgsType(fredagsBar, p19, 38, 1);
        Pris pr34 = Controller.createPrisForSalgsType(fredagsBar, p20, 38, 1);
        Pris pr35 = Controller.createPrisForSalgsType(fredagsBar, p21, 38, 1);
        Pris pr36 = Controller.createPrisForSalgsType(fredagsBar, p22, 38, 1);
        Pris pr37 = Controller.createPrisForSalgsType(fredagsBar, p23, 38, 1);
        Pris pr38 = Controller.createPrisForSalgsType(fredagsBar, p24, 38, 1);
        Pris pr39 = Controller.createPrisForSalgsType(fredagsBar, p25, 38, 1);
        Pris pr40 = Controller.createPrisForSalgsType(fredagsBar, p26, 15, 0);
        Pris pr41 = Controller.createPrisForSalgsType(fredagsBar, p27, 10, 0);
        Pris pr42 = Controller.createPrisForSalgsType(fredagsBar, p28, 15, 0);
        Pris pr43 = Controller.createPrisForSalgsType(fredagsBar, p29, 15, 0);
        Pris pr44 = Controller.createPrisForSalgsType(fredagsBar, p30, 15, 0);
        Pris pr45 = Controller.createPrisForSalgsType(fredagsBar, p31, 15, 0);
        Pris pr46 = Controller.createPrisForSalgsType(fredagsBar, p32, 10, 0);
        Pris pr47 = Controller.createPrisForSalgsType(fredagsBar, p33, 30, 1);

        Produkt p34 = Controller.createProdukt("Whisky 45% 50 cl rør", spiritus);
        Produkt p35 = Controller.createProdukt("Whisky 4 cl.", spiritus);
        Produkt p36 = Controller.createProdukt("Whisky 43% 50 cl rør", spiritus);
        Produkt p37 = Controller.createProdukt("u/ egesplint", spiritus);
        Produkt p38 = Controller.createProdukt("m/ egesplint", spiritus);
        Produkt p39 = Controller.createProdukt("2*whisky glas + brikker", spiritus);
        Produkt p40 = Controller.createProdukt("Liquor of Aarhus", spiritus);
        Produkt p41 = Controller.createProdukt("Lyng gin 50 cl", spiritus);
        Produkt p42 = Controller.createProdukt("Lyng gin 4cl", spiritus);

        Pris pr48 = Controller.createPrisForSalgsType(fredagsBar, p34, 599, 0);
        Pris pr49 = Controller.createPrisForSalgsType(butik, p34, 599, 0);
        Pris pr50 = Controller.createPrisForSalgsType(fredagsBar, p35, 50, 0);
        Pris pr51 = Controller.createPrisForSalgsType(fredagsBar, p36, 499, 0);
        Pris pr52 = Controller.createPrisForSalgsType(butik, p36, 499, 0);
        Pris pr53 = Controller.createPrisForSalgsType(fredagsBar, p37, 300, 0);
        Pris pr54 = Controller.createPrisForSalgsType(butik, p37, 300, 0);
        Pris pr55 = Controller.createPrisForSalgsType(fredagsBar, p38, 350, 0);
        Pris pr56 = Controller.createPrisForSalgsType(butik, p38, 350, 0);
        Pris pr57 = Controller.createPrisForSalgsType(fredagsBar, p39, 80, 0);
        Pris pr58 = Controller.createPrisForSalgsType(butik, p39, 80, 0);
        Pris pr59 = Controller.createPrisForSalgsType(fredagsBar, p40, 175, 0);
        Pris pr60 = Controller.createPrisForSalgsType(butik, p40, 175, 0);
        Pris pr61 = Controller.createPrisForSalgsType(fredagsBar, p41, 350, 0);
        Pris pr62 = Controller.createPrisForSalgsType(butik, p41, 350, 0);
        Pris pr63 = Controller.createPrisForSalgsType(butik, p42, 40, 0);

        Produkt p43 = Controller.createProdukt("Klosterbryg, 20 liter", fustage);
        Produkt p44 = Controller.createProdukt("Jazz Classic, 25 liter", fustage);
        Produkt p45 = Controller.createProdukt("Extra Pilsner, 25 liter", fustage);
        Produkt p46 = Controller.createProdukt("Celebration, 20 liter", fustage);
        Produkt p47 = Controller.createProdukt("Blondie, 25 liter ", fustage);
        Produkt p48 = Controller.createProdukt("Forårsbryg, 20 liter", fustage);
        Produkt p49 = Controller.createProdukt("India Pale Ale, 20 liter", fustage);
        Produkt p50 = Controller.createProdukt("Julebryg, 20 liter", fustage);
        Produkt p51 = Controller.createProdukt("Imperial Stout, 20 liter", fustage);
        Produkt p52 = Controller.createProdukt("Pant", fustage);

        Pris pr64 = Controller.createPrisForSalgsType(butik, p43, 775, 0);
        Pris pr65 = Controller.createPrisForSalgsType(butik, p44, 625, 0);
        Pris pr66 = Controller.createPrisForSalgsType(butik, p45, 575, 0);
        Pris pr67 = Controller.createPrisForSalgsType(butik, p46, 775, 0);
        Pris pr68 = Controller.createPrisForSalgsType(butik, p47, 700, 0);
        Pris pr69 = Controller.createPrisForSalgsType(butik, p48, 775, 0);
        Pris pr70 = Controller.createPrisForSalgsType(butik, p49, 775, 0);
        Pris pr71 = Controller.createPrisForSalgsType(butik, p50, 775, 0);
        Pris pr72 = Controller.createPrisForSalgsType(butik, p51, 775, 0);
        Pris pr73 = Controller.createPrisForSalgsType(butik, p52, 200, 0);

        Produkt p53 = Controller.createProdukt("6 Kg", kulsyre);
        Produkt p54 = Controller.createProdukt("Pant", kulsyre);
        Produkt p55 = Controller.createProdukt("4 Kg", kulsyre);
        Produkt p56 = Controller.createProdukt("10 Kg", kulsyre);
        Pris pr74 = Controller.createPrisForSalgsType(fredagsBar, p53, 400, 0);
        Pris pr75 = Controller.createPrisForSalgsType(butik, p53, 400, 0);
        Pris pr76 = Controller.createPrisForSalgsType(fredagsBar, p54, 1000, 0);
        Pris pr77 = Controller.createPrisForSalgsType(butik, p54, 1000, 0);

        Produkt p57 = Controller.createProdukt("25 kg sæk", malt);
        Pris pr78 = Controller.createPrisForSalgsType(butik, p57, 300, 0);

        Produkt p58 = Controller.createProdukt("t-shirt", beklædning);
        Produkt p59 = Controller.createProdukt("polo", beklædning);
        Produkt p60 = Controller.createProdukt("cap", beklædning);
        Pris pr79 = Controller.createPrisForSalgsType(fredagsBar, p58, 70, 0);
        Pris pr80 = Controller.createPrisForSalgsType(butik, p58, 70, 0);
        Pris pr81 = Controller.createPrisForSalgsType(fredagsBar, p59, 100, 0);
        Pris pr82 = Controller.createPrisForSalgsType(butik, p59, 100, 0);
        Pris pr83 = Controller.createPrisForSalgsType(fredagsBar, p60, 30, 0);
        Pris pr84 = Controller.createPrisForSalgsType(butik, p60, 30, 0);

        Produkt p61 = Controller.createProdukt("1-hane", anlæg);
        Produkt p62 = Controller.createProdukt("2-haner", anlæg);
        Produkt p63 = Controller.createProdukt("Bar med flere haner", anlæg);
        Produkt p64 = Controller.createProdukt("Levering", anlæg);
        Produkt p65 = Controller.createProdukt("Krus", anlæg);
        Pris pr85 = Controller.createPrisForSalgsType(butik, p61, 250, 0);
        Pris pr86 = Controller.createPrisForSalgsType(butik, p62, 400, 0);
        Pris pr87 = Controller.createPrisForSalgsType(butik, p63, 500, 0);
        Pris pr88 = Controller.createPrisForSalgsType(butik, p64, 500, 0);
        Pris pr89 = Controller.createPrisForSalgsType(butik, p65, 60, 0);

        Produkt p66 = Controller.createProdukt("uanset størrelse", glas);
        Pris pr90 = Controller.createPrisForSalgsType(butik, p66, 15, 0);

        Produkt p67 = Controller.createProdukt("gaveæske 2 øl, 2 glas", sampakninger);
        Produkt p68 = Controller.createProdukt("gaveæske 4 øl ", sampakninger);
        Produkt p69 = Controller.createProdukt("trækasse 6 øl", sampakninger);
        Produkt p70 = Controller.createProdukt("gavekurv 6 øl, 2 glas", sampakninger);
        Produkt p71 = Controller.createProdukt("trækasse 6 øl, 6 glas", sampakninger);
        Produkt p72 = Controller.createProdukt("trækasse 12 øl", sampakninger);
        Produkt p73 = Controller.createProdukt("papkasse 12 øl", sampakninger);
        Pris pr91 = Controller.createPrisForSalgsType(fredagsBar, p67, 110, 0);
        Pris pr92 = Controller.createPrisForSalgsType(butik, p67, 110, 0);
        Pris pr93 = Controller.createPrisForSalgsType(fredagsBar, p68, 140, 0);
        Pris pr94 = Controller.createPrisForSalgsType(butik, p68, 140, 0);
        Pris pr95 = Controller.createPrisForSalgsType(fredagsBar, p69, 260, 0);
        Pris pr96 = Controller.createPrisForSalgsType(butik, p69, 260, 0);
        Pris pr97 = Controller.createPrisForSalgsType(fredagsBar, p70, 260, 0);
        Pris pr98 = Controller.createPrisForSalgsType(butik, p70, 260, 0);
        Pris pr99 = Controller.createPrisForSalgsType(fredagsBar, p71, 350, 0);
        Pris pr100 = Controller.createPrisForSalgsType(butik, p71, 350, 0);
        Pris pr101 = Controller.createPrisForSalgsType(fredagsBar, p72, 410, 0);
        Pris pr102 = Controller.createPrisForSalgsType(butik, p72, 410, 0);
        Pris pr103 = Controller.createPrisForSalgsType(fredagsBar, p73, 370, 0);
        Pris pr104 = Controller.createPrisForSalgsType(butik, p73, 370, 0);

        Produkt p74 = Controller.createProdukt("pr person pr dag", rundvisning);
        Pris pr105 = Controller.createPrisForSalgsType(butik, p74, 370, 0);


        Salg s1 = new Salg(LocalDateTime.of(2022, 11, 01, 12, 30));
        s1.createSalgslinje(2, pr2);
        s1.setBetalingsMetode("Dankort");

        Salg s2 = new Salg(LocalDateTime.of(2022, 11, 01, 12, 30));
        s2.createSalgslinje(2, pr3);
        s2.setBetalingsMetode("Kontant");

        Salg s3 = new Salg(LocalDateTime.of(2022, 11, 01, 12, 30));
        s3.createSalgslinje(2, pr4);
        s3.createSalgslinje(2,pr6);
        s3.brugKlip();
        s3.setBetalingsMetode("Dankort");

        Salg s4 = new Salg(LocalDateTime.of(2022, 11, 01, 12, 30));
        s4.createSalgslinje(2, pr5);
        s4.setBetalingsMetode("Kontant");

        Salg s5 = new Salg(LocalDateTime.now());
        s5.createSalgslinje(2, pr1);
        s5.setBetalingsMetode("Dankort");

        Salg s6 = new Salg(LocalDateTime.now());
        s6.createSalgslinje(3,pr2);
        s6.brugKlip();

        Controller.createSalg(s1);
        Controller.createSalg(s2);
        Controller.createSalg(s3);
        Controller.createSalg(s4);
        Controller.createSalg(s5);
        Controller.createSalg(s6);

        Kunde k1 = new Kunde("Behzad Haidari", "42334315", "Bispehavevej 3");
        Udlejning ud1 = new Udlejning(LocalDateTime.now(), k1);
        ud1.createSalgslinje(3, pr64);
        ud1.createSalgslinje(2, pr67);
        ud1.createSalgslinje(2, pr85);
        ud1.createSalgslinje(1, pr86);
        ud1.setBetalingsMetode("Dankort");


        createSalg(ud1);


    }


}
