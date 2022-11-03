package Applikation.Model;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    private Salg s;


    @BeforeEach

    void setUP() {
        s = new Salg(LocalDateTime.of(2022,3,11,12,30));


    }


    @Test

    void Salg_TC1_salgtidoprettet() {
        LocalDateTime tid = LocalDateTime.of(2022,3,11,12,30);
        assertEquals(s.getDatoTid(), tid);
    }

    @Test

    void createSalgslinje_TC1_tjekSalgslinjeOprettet() {
        Pris p = new Pris()
        Salgslinje sl = new Salgslinje(2,)
    }




}