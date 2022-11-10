package Applikation.Controller;

import Applikation.Model.Pris;
import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;
import Applikation.Model.Salgstype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Salgstype salgstype;
    private Produktgruppe produktgruppe;
    private Produkt produkt;
    private Pris pris;
    private LocalDate dato1, dato2, dato3, dato4;

    @BeforeEach
    void setUp() {
        salgstype = Controller.createSalgstype("Butik");
        produktgruppe = Controller.createProduktGruppe("Klip", false);
        produkt = Controller.createProdukt("Klippekort", produktgruppe);
        pris = Controller.createPrisForSalgsType(salgstype, produkt, 100, 0);

        dato1 = LocalDate.of(2022, 10,31);
        dato2 = LocalDate.of(2022,11,1);
        dato3 = LocalDate.of(2022,11,3);
        dato4 = LocalDate.of(2022,11,4);
    }

    @Test
    void solgteKlip_TC1_fra31okt_til4nov(){


    }
}