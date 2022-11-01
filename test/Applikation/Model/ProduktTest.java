package Applikation.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduktTest {

    private Produkt p;
    private Produktgruppe pg;

    @BeforeEach

    void setUp() {
        p = new Produkt("Klosterbryg",pg);
        pg = new Produktgruppe("Flaske");
    }

    @Test

    void Produkt_TC1_tjekProduktOprettet() {
        assertTrue(pg.getProdukter().contains(p));

    }


    @Test
    void getProdukttype() {
    }

    @Test
    void setProduktgruppe() {
    }
}