package Applikation.Model;

public class Produkt {
    private String navn;
    private Produkttype produkttype;

    public Produkt(String navn, Produkttype produkttype){
        this.navn = navn;
        this.produkttype = produkttype;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Produkttype getProdukttype() {
        return produkttype;
    }

    public void setProdukttype(Produkttype produkttype) {
        this.produkttype = produkttype;
    }
}
