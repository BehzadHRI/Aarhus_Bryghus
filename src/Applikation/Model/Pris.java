package Applikation.Model;

public class Pris {

    private int pris;
    private int antalKlip;
    private Produkt produkt;
    Salgstype salgstype;


    Pris(int pris, int antalKlip, Produkt produkt, Salgstype salgstype) {
        this.pris = pris;
        this.antalKlip = antalKlip;
        this.produkt = produkt;
        this.salgstype = salgstype;
    }

    public Salgstype getSalgstype(){
        return salgstype;
    }


    public Produkt getProdukt() {
        return produkt;
    }


    public void setProdukt(Produkt produkt){
        if(this.produkt != produkt){
            this.produkt = produkt;
        }
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





    @Override
    public String toString() {
        String result = produkt + ", " + pris + "DKK";
        if (antalKlip != 0){
            result += ", " + antalKlip + " klip";
        }
        return result;
    }
}
