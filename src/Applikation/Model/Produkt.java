package Applikation.Model;

import java.util.ArrayList;

public class Produkt {
    private String navn;
    private Produktgruppe produktgruppe;


    //Dobbeltrettet produkt 1 til Pris mange.
/*    private ArrayList<Pris> priser = new ArrayList<>();

    public ArrayList<Pris> getPriser(){
        return new ArrayList<>(priser);
    }

    public void addPris(Pris pris){
        if(!priser.contains(pris)){
            priser.add(pris);
            priser.setProdukt(this);
        }
    }

    public void removePris(Pris pris){
        if(priser.contains(pris)){
            priser.remove(pris);
            priser.setProdukt(null);
        }
    }*/

    //-------------------------------------


    public Produkt(String navn, Produktgruppe produktgruppe){
        this.navn = navn;
        setProduktgruppe(produktgruppe);
    }


    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Produktgruppe getProduktgruppe() {
        return produktgruppe;
    }

    public void setProduktgruppe(Produktgruppe produktgruppe){
        if(this.produktgruppe != produktgruppe){
            Produktgruppe oldProduktgruppe = this.produktgruppe;
            if(oldProduktgruppe != null){
                oldProduktgruppe.removeProdukt(this);
            }
            this.produktgruppe = produktgruppe;
            if(produktgruppe != null){
                produktgruppe.addProdukt(this);
            }
        }
    }



    @Override
    public String toString() {
        return navn;
    }



}
