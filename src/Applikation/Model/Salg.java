package Applikation.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Salg {
    private LocalDateTime datoTid;
    private ArrayList<Salgslinje> salgslinjer;
    private String betalingsMetode;

    public Salg(LocalDateTime datoTid) {
        this.datoTid = datoTid;
        salgslinjer = new ArrayList<>();

    }

    public ArrayList<Salgslinje> getAntals(){
        return new ArrayList<>(salgslinjer);
    }

    public LocalDateTime getDatoTid() {
        return datoTid;
    }

    public Salgslinje createSalgslinje(int antal, Pris pris) {
        Salgslinje salgslinje = new Salgslinje(antal, pris);
        salgslinjer.add(salgslinje);
        return salgslinje;
    }

    public String getBetalingsMetode() {
        return betalingsMetode;
    }

    public void setBetalingsMetode(String betalingsMetode) {
        this.betalingsMetode = betalingsMetode;
    }

    public void removeSalgslinje(Salgslinje salgslinje) {
        salgslinjer.remove(salgslinje);
    }


    public void addSalgslinje(Salgslinje salgslinje){
        if(!salgslinjer.contains(salgslinje)){
            salgslinjer.add(salgslinje);
        }
    }

    public int samletPris() {
        int result = 0;
        for (Salgslinje ant : salgslinjer) {
            result += (ant.getAntal() * ant.getPris().getPris());
        }
        return result;
    }
}
