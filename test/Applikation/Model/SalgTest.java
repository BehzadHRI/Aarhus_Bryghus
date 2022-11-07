package Applikation.Model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    private Salg s;
    private Produkt p;
    private Pris ps;
    private Produktgruppe pg;
    private Salgstype st;


    @BeforeEach

    void setUP() {
        pg = new Produktgruppe("Flaske",true);
        p = new Produkt("Klosterbryg",pg);
        s = new Salg(LocalDateTime.of(2022,7,11,12,00));
        st = new Salgstype("Fredagsbar");
        ps = new Pris(70,2,p,st);

    }

    @Test

    void createSalgslinje_TC1_tjekSalgslinjeOprettet() {
        LocalDateTime dato = LocalDateTime.of(2022,07,11,12,00);
        ArrayList<Salgslinje> salgslinjer = new ArrayList<>();

        Salg expected = new Salg(LocalDateTime.now());

        expected.createSalgslinje(5, ps);
        expected.createSalgslinje(5, ps);
        expected.createSalgslinje(5, ps);
        s.createSalgslinje(5,ps);
        s.createSalgslinje(5,ps);
        s.createSalgslinje(5,ps);

        int fuldKlip = 0;
        int klipPris = 0;
        Boolean klipBrugt = false;

        assertEquals(dato,s.getDatoTid());
        assertEquals(expected.getAntals().get(0).getPris(), s.getAntals().get(0).getPris());
        assertEquals(expected.getFuldklip(),s.getFuldklip());
        assertEquals(expected.isKlipBrugt(),s.isKlipBrugt());

    }










}