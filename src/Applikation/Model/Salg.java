package Applikation.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Salg {
    private LocalDateTime datoTid;


    private ArrayList<Salgslinje> salgslinjer;

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
        Salgslinje ant = new Salgslinje(antal, pris);
        salgslinjer.add(ant);
        return ant;
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
