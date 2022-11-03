package Applikation.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalgstypeTest {
    private Salgstype st;

    @BeforeEach
    void setUP() {
        st = new Salgstype("Butik");

    }

    @Test
    void Salgstype_TC1_tjekSalgstypenavn() {
        assertTrue(st.getNavn().contains(""));

    }
}