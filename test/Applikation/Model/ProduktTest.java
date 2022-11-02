package Applikation.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduktTest {

    private Produktgruppe pg;
    private Produkt p;


    @BeforeEach

    void setUp() {
        pg = new Produktgruppe("Flaske");
        p = new Produkt("Klosterbryg",pg);

    }

    @Test

    void Produkt_TC1_tjekProduktOprettet() {
        assertTrue(pg.getProdukter().contains(p));

    }

    @Test
    void getProduktgruppe_TC1_getProduktgruppe() {
        assertEquals(pg, p.getProduktgruppe());
    }





}