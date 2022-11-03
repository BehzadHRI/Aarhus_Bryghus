package Applikation.Model;

import java.time.LocalDateTime;

public class Rundvisning extends Salg {

    private Kunde kunde;
    private boolean erBetalt;
    private LocalDateTime mødeTidspunkt;

    public Rundvisning(LocalDateTime datoTid, Kunde kunde, LocalDateTime mødeTidspunkt, boolean erBetalt) {
        super(datoTid);
        this.kunde = kunde;
        this.erBetalt = false;
        this.mødeTidspunkt = mødeTidspunkt;
    }

    public void setKunde (Kunde kunde){
        if(this.kunde != kunde){
            Kunde oldKunde = this.kunde;
            if(oldKunde != null){
                oldKunde.removeRundvisning(this);
            }
            this.kunde = kunde;
            if(kunde != kunde){
                kunde.addRundvisning(this);
            }
        }
    }




}
