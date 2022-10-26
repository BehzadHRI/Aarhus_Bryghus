package Applikation.Model;

import java.util.ArrayList;

public class Salgstype {

    private String navn;
    private ArrayList<Pris> priser = new ArrayList<>();

    public ArrayList<Pris> getPriser(){
        return new ArrayList<>(priser);
    }


    public Salgstype(String navn){
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void opretPris(int pris, int antalKlip, Produkt produkt){
        Pris prisObjekt = new Pris(pris,antalKlip,produkt);
        priser.add(prisObjekt);
    }

}
