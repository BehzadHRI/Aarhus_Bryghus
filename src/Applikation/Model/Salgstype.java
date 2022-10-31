package Applikation.Model;

import javax.management.PersistentMBean;
import java.util.ArrayList;

public class Salgstype {

    private String navn;
    private ArrayList<Pris> priser = new ArrayList<>();


    public Salgstype(String navn){
        this.navn = navn;
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

    public Pris createPris(int pris, int antalKlip,Produkt produkt, Salgstype salgstype){
        Pris prisObjekt = new Pris(pris,antalKlip,produkt, salgstype);
        priser.add(prisObjekt);
        return prisObjekt;
    }

    public void removePris(Pris pris) {
        if (priser.contains(pris)) {
            removePris(pris);
        }
    }


    @Override
    public String toString() {
        return navn;
    }
}
