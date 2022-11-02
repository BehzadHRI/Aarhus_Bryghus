package Applikation.Model;

import java.util.ArrayList;

public class Produktgruppe {

    private String navn;
    private ArrayList<Produkt> produkter;
    private boolean kanUdlejes;

    public Produktgruppe(String navn, boolean kanUdlejes) {
        this.navn = navn;
        produkter = new ArrayList<>();
        this.kanUdlejes = kanUdlejes;
    }

    public ArrayList<Produkt> getProdukter(){
        return new ArrayList<>(produkter);
    }

    public boolean isKanUdlejes() {
        return kanUdlejes;
    }

    public void setKanUdlejes(boolean kanUdlejes) {
        this.kanUdlejes = kanUdlejes;
    }

    public void addProdukt(Produkt produkt){
        if(!produkter.contains(produkt)){
            produkter.add(produkt);
            produkt.setProduktgruppe(this);
        }
    }

    public void removeProdukt(Produkt produkt){
        if(produkter.contains(produkt)){
            produkter.remove(produkt);
        }
    }

    public String toString() {
        return navn;
    }
}
