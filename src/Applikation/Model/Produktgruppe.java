package Applikation.Model;

import java.util.ArrayList;

public class Produktgruppe {

    private String navn;
    private ArrayList<Produkt> produkter;

    public Produktgruppe(String navn) {
        this.navn = navn;
        produkter = new ArrayList<>();
    }

    public ArrayList<Produkt> getProdukter(){
        return new ArrayList<>(produkter);
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
            produkt.setProduktgruppe(this);
        }
    }




}
