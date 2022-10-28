package Applikation.Model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduktgruppeTest {

    private Produktgruppe pg;


    @BeforeEach

    void setUp() {
        pg = new Produktgruppe("Flaske");
    }
    @Test
    void Produktgruppe_TC1_tjeknavn() {

        assertTrue(pg, pg.getNavn().contains();
    }

    @Test
    void addProdukt_TC1_addproduktp1() {
        Produkt p1 = new Produkt("Klosterbryg", pg);
        Produkt p2 = new Produkt("Blondie", pg);

        assertTrue(pg.getProdukter().contains(p2));

        assertEquals(p1, pg.getProdukter().get(0));
    }

    @Test
    void addProdukt_TC2_addproduktp2() {
        Produkt p1 = new Produkt("Klosterbryg", pg);
        Produkt p2 = new Produkt("Blondie", pg);

        assertTrue(pg.getProdukter().contains(p1));

        assertEquals(p2, pg.getProdukter().get(1));
    }
    @Test
    void addProdukt_TC3_addproduktp3() {
        Produkt p1 = new Produkt("Klosterbryg", pg);
        Produkt p3 = new Produkt("Blondie", pg);

        assertTrue(pg.getProdukter().contains(p1));

        assertEquals(p3, pg.getProdukter().get(2));
    }
    @Test
    void addProdukt_TC4_addproduktnull() {

        assertTrue(pg.getProdukter().contains(null));

        assertEquals(null, pg.getProdukter().get(0));
    }


    @Test
    void removeProdukt_TC1_removeproduktp1() {
        Produkt p1 = new Produkt("Klosterbryg", pg);
        Produkt p2 = new Produkt("Tuborg", pg);

        pg.removeProdukt(p1);

        assertTrue(pg.getProdukter().size() == 1);


    }

    @Test
    void removeProdukt_TC2_removeproduktp2() {
        Produkt p1 = new Produkt("Klosterbryg", pg);
        Produkt p2 = new Produkt("Tuborg", pg);

        pg.removeProdukt(p2);

        assertTrue(pg.getProdukter().size() == 1);


    }

    @Test
    void removeProdukt_TC3_removeproduktp3() {
        Produkt p1 = new Produkt("Klosterbryg", pg);
        Produkt p2 = new Produkt("Tuborg", pg);
        Produkt p3 = new Produkt("Øl", pg);

        pg.removeProdukt(p3);

        assertTrue(pg.getProdukter().size() == 2);

    }

}