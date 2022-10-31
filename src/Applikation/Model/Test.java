package Applikation.Model;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {


        Produktgruppe pg1 = new Produktgruppe("Øl");
        Produktgruppe pg2 = new Produktgruppe("Julebryg");
        Produktgruppe pg3 = new Produktgruppe("Pilsner");

        Produkt p1 = new Produkt("Afghan Beer", pg1);
        Produkt p2 = new Produkt("Danish Juul", pg2);
        Produkt p3 = new Produkt("Somalian Pilz", pg3);

        Salgstype st1 = new Salgstype("Fredagsbar");
        Salgstype st2 = new Salgstype("Julefrokost");
        Salgstype st3 = new Salgstype("Somalisk højtid");

        Pris pris1 = st1.createPris(49,2,p1,st1);
        Pris pris2 = st2.createPris(49,2,p1,st1);
        Pris pris3 = st3.createPris(49,0,p1,st1);

        Salg salg1 = new Salg(LocalDateTime.of(2022,10,27,9,30));
        salg1.createSalgslinje(3,pris1);
        System.out.println(salg1.samletPris());

    }

}
