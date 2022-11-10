package Applikation.Model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    private Salg salg;
    private Produkt produkt;
    private Pris pris1, pris2;
    private Produktgruppe produktgruppe;
    private Salgstype salgstype;


    @BeforeEach

    void setUP() {
        produktgruppe = new Produktgruppe("Flaske",true);
        produkt = new Produkt("Julebryg", produktgruppe);
        salg = new Salg(LocalDateTime.of(2022,1,1,12,0));
        salgstype = new Salgstype("FredagsBar");
        pris1 = new Pris(100,2, produkt, salgstype);
        pris2 = new Pris(150,0, produkt, salgstype);

    }

    @Test
    void createSalgslinje_TC1_antalu0_prisnotnull() {
        //Arrange
        int antal = -1;
        Pris prisInp = pris1;


        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            salg.createSalgslinje(antal, prisInp);
        });
        assertEquals(exception.getMessage(), "Ugyldig antal");
    }

    @Test
    void createSalgslinje_TC2_antalo0_prisnotnull() {
        //Arrange
        int antal = 1;
        Pris prisInp = pris1;
        //Act
        salg.createSalgslinje(antal, prisInp);
        //Assert
        assertEquals(salg.getSalgslinjer().get(0).getAntal(), antal);
        assertEquals(salg.getSalgslinjer().get(0).getPris(), pris1);
    }

    @Test
    void createSalgslinje_TC2_antalo0_prisnull() {
        //Arrange
        int antal = 1;
        Pris prisInp = null;
        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () ->{
            salg.createSalgslinje(antal, prisInp);
        });
        assertEquals(exception.getMessage(), "Ugyldig Pris");

    }


    @Test
    void setRabat_TC1_rabatu0(){
        //Arrange
        salg.createSalgslinje(1, pris1);

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () ->{
           salg.setRabat(-50);
        });
        assertEquals("Ugyldig rabat", exception.getMessage());
    }

    @Test
    void setRabat_TC2_rabatlig0(){
        //Arrange
        salg.createSalgslinje(1, pris1);

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () ->{
            salg.setRabat(0);
        });
        assertEquals("Ugyldig rabat", exception.getMessage());
    }

    @Test
    void setRabat_TC3_rabato0(){
        //Arrange
        salg.createSalgslinje(1, pris1);

        //Act
        salg.setRabat(50);

        //Assert
        int expected = 50;
        assertEquals(expected, salg.getSamletPris());
    }
    @Test
    void setBetaling_TC1_Klippekort(){
        //Arrange
        salg.createSalgslinje(1, pris1);


        //Act
        salg.setBetalingsMetode("Klippekort");

        //Assert
        assertEquals("Klippekort", salg.getBetalingsMetode());
        assertTrue(salg.isKlipBrugt());
    }
    @Test
    void setBetaling_TC2_Dankort(){
        //Arrange
        salg.createSalgslinje(1, pris1);


        //Act
        salg.setBetalingsMetode("Dankort");

        //Assert
        assertEquals("Dankort", salg.getBetalingsMetode());
        assertTrue(!salg.isKlipBrugt());
    }
    @Test
    void setBetaling_TC3_Kontant(){
        //Arrange
        salg.createSalgslinje(1, pris1);


        //Act
        salg.setBetalingsMetode("Kontant");

        //Assert
        assertEquals("Kontant", salg.getBetalingsMetode());
        assertTrue(!salg.isKlipBrugt());
    }
    @Test
    void setBetaling_TC4_Nflæaslædnfal(){
        //Arrange
        salg.createSalgslinje(1, pris1);


        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
           salg.setBetalingsMetode("Nflæaslædnfal");
        });
        assertEquals("Ugyldig Betalingsmetode", exception.getMessage());
    }
    @Test
    void setBetaling_TC4_tomString(){
        //Arrange
        salg.createSalgslinje(1, pris1);


        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            salg.setBetalingsMetode("");
        });
        assertEquals("Ugyldig Betalingsmetode", exception.getMessage());
    }
    @Test
    void brugKlip_TC1_pris1ant1_pris2ant0(){
        //Arrange
        Salgslinje sl1 = salg.createSalgslinje(1, pris1);
//        Salgslinje sl2 = salg.createSalgslinje(0, pris2);

        //Act
        salg.brugKlip();

        //Assert
        assertTrue(salg.isKlipBrugt());
        assertEquals("Klippekort", salg.getBetalingsMetode());
        assertEquals(salg.getKlipPris(), salg.getSamletPris());
    }
    @Test
    void brugKlip_TC1_pris1ant1_pris2ant1(){
        //Arrange
        Salgslinje sl1 = salg.createSalgslinje(1, pris1);
        Salgslinje sl2 = salg.createSalgslinje(1, pris2);

        //Act
        salg.brugKlip();

        //Assert
        assertTrue(salg.isKlipBrugt());

        assertEquals(pris2.getPris(), salg.getSamletPris());
    }
    @Test
    void brugKlip_TC1_pris1ant0_pris2ant1(){
        //Arrange
//        Salgslinje sl1 = salg.createSalgslinje(1, pris1);
        Salgslinje sl2 = salg.createSalgslinje(1, pris2);

        //Act
        salg.brugKlip();

        //Assert
        assertTrue(!salg.isKlipBrugt());
        assertEquals(pris2.getPris(), salg.getSamletPris());
        assertEquals(0, salg.getFuldklip());
    }

    @Test
    void samletPris_TC1_pris1ant1_pris2ant0(){
        //Arrange
        Salgslinje sl1 = salg.createSalgslinje(1, pris1);
//        Salgslinje sl2 = salg.createSalgslinje(1, pris2);

        //Act
        salg.samletPris();

        //Assert
        int expected = 100;
        assertEquals(expected, salg.getSamletPris());
    }
    @Test
    void samletPris_TC1_pris1ant1_pris2ant1(){
        //Arrange
        Salgslinje sl1 = salg.createSalgslinje(1, pris1);
        Salgslinje sl2 = salg.createSalgslinje(1, pris2);

        //Act
        salg.samletPris();

        //Assert
        int expected = 250;
        assertEquals(expected, salg.getSamletPris());
    }
    @Test
    void samletPris_TC1_pris1ant0_pris2ant1(){
        //Arrange
//        Salgslinje sl1 = salg.createSalgslinje(1, pris1);
        Salgslinje sl2 = salg.createSalgslinje(1, pris2);

        //Act
        salg.samletPris();

        //Assert
        int expected = 150;
        assertEquals(expected, salg.getSamletPris());
    }


    @Test

    void createSalgsLinje_TC1_tjeksalgslinjeCreated() {
        Salg salg = new Salg(LocalDateTime.now());
        Salgslinje salgslinje= salg.createSalgslinje(4, pris1);
        ArrayList<Salgslinje> expected = new ArrayList<>();
        expected.add(salgslinje);

        assertEquals(expected, salg.getSalgslinjer());
        assertTrue(salg.getSalgslinjer().contains(salgslinje));
    }

    @Test
    void removeSalgsLinje_TC1_tjeksalgslinjeRemoved() {
        Salg salg = new Salg(LocalDateTime.now());
        Salgslinje salgslinje= salg.createSalgslinje(4, pris1);
        assertTrue(salg.getSalgslinjer().contains(salgslinje));

        salg.removeSalgslinje(salgslinje);
        assertEquals(0,salg.getSalgslinjer().size());
        assertFalse(salg.getSalgslinjer().contains(salgslinje));
    }

    @Test
    void getBetalingsform_TC1_tjekbetalingsform() {

    }












}