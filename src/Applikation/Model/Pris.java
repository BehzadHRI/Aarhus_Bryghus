package Applikation.Model;

public class Pris {

    private int pris;
    private int antalKlip;
    private Produkt produkt;

    public Pris(int pris, int antalKlip, Produkt produkt) {
        this.pris = pris;
        this.antalKlip = antalKlip;
        this.produkt = produkt;
    }


    public Produkt getProdukt() {
        return produkt;
    }


    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getAntalKlip() {
        return antalKlip;
    }

    public void setAntalKlip(int antalKlip) {
        this.antalKlip = antalKlip;
    }


    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }
}
