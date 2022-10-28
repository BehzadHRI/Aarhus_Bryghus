package Applikation.Model;

import java.util.ArrayList;

public class Salgstype {

    private String navn;
    private ArrayList<Pris> priser;


    public Salgstype(String navn){
        this.navn = navn;
        priser = new ArrayList<>();
    }
    public ArrayList<Pris> getPriser(){
        return new ArrayList<>(priser);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Pris opretPris(int pris, int antalKlip, Produkt produkt){
        Pris prisObjekt = new Pris(pris,antalKlip,produkt);
        priser.add(prisObjekt);
        return prisObjekt;
    }

    @Override
    public String toString() {
        return navn;
    }
}
