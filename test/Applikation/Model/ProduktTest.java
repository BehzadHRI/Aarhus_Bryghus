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
    }
    

    @Test
    void getProdukttype() {
    }

    @Test
    void setProduktgruppe() {
    }
}