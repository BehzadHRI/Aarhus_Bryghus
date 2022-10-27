package Applikation.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Salg {
    private LocalDateTime datoTid;
    private ArrayList<Antal> antals;

    public Salg(LocalDateTime datoTid) {
        this.datoTid = datoTid;
        antals = new ArrayList<>();
    }

    public LocalDateTime getDatoTid() {
        return datoTid;
    }

    public Antal createAntal(int antal, Pris pris) {
        Antal ant = new Antal(antal, pris);
        antals.add(ant);
        return ant;
    }

    public void removeAntal(Antal antal) {
        antals.remove(antal);
    }

    public int samletPris() {
        int result = 0;
        for (Antal ant : antals) {
            result += (ant.getAntal() * ant.getPris().getPris());
        }
        return result;
    }
}
