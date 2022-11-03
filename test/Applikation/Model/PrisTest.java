package Applikation.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrisTest {
    private Produktgruppe pg;
    private Produkt p;
    private Salgstype st;

    @BeforeEach
    void setup() {
        pg = new Produktgruppe("Flaske",true);
        p = new Produkt("Klosterbryg",pg);
        st = new Salgstype("Butik");
    }

    @Test
    void Pris_TC1_Prisoprettet() {
        Pris ps = new Pris(70,2,p,st);

        assertEquals(ps.getPris(), 70);
        assertEquals(ps.getAntalKlip(), 2);
        assertEquals(ps.getProdukt(), p);
        assertEquals(ps.getSalgstype(), st);
    }


}