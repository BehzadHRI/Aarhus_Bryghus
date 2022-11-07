package Applikation.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrisTest {
    private Salg s;
    private Produkt p;
    private Pris ps;
    private Produktgruppe pg;
    private Salgstype st;

    @BeforeEach
    void setup() {
        pg = new Produktgruppe("Flaske",true);
        p = new Produkt("Klosterbryg",pg);
        s = new Salg(LocalDateTime.of(2022,3,11,12,30));
        st = new Salgstype("Fredagsbar");
        ps = new Pris(70,2,p,st);
    }

    @Test
    void Pris_TC1_Prisoprettet() {
        Pris ps = new Pris(70,2,p,st);

        assertEquals(ps.getPris(), 70);
        assertEquals(ps.getAntalKlip(), 2);
        assertEquals(ps.getProdukt(), p);
        assertEquals(ps.getSalgstype(), st);
    }

    @Test

    void setProdukt_TC1_tjekProduktTilføjet() {
        ps.setProdukt(p);
        assertEquals(p,ps.getProdukt());
    }

    @Test

    void setProdukt_TC1_tjekPrisTilføjet() {
        int pris = 100;
        ps.setPris(pris);
        assertEquals(p,ps.getProdukt());
    }

    @Test

    void setAntalKlip_TC1_tjekKlipTilføjet() {
        int klip = 5;
        ps.setAntalKlip(klip);
        assertEquals(klip,ps.getAntalKlip());
    }




}