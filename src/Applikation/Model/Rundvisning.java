package Applikation.Model;

import java.time.LocalDateTime;

public class Rundvisning extends Salg {

    private Kunde kunde;
    private boolean erBetalt;
    private LocalDateTime mødeTidspunkt;
    private int antalPer;

    public Rundvisning(LocalDateTime datoTid, Kunde kunde, LocalDateTime mødeTidspunkt, int antalPer, boolean erBetalt) {
        super(datoTid);
        this.kunde = kunde;
        this.erBetalt = false;
        this.mødeTidspunkt = mødeTidspunkt;
        this.antalPer = antalPer;
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

    public int getAntalPer() {
        return antalPer;
    }

    public void setAntalPer(int antalPer) {
        this.antalPer = antalPer;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public boolean isErBetalt() {
        return erBetalt;
    }

    public void setErBetalt(boolean erBetalt) {
        this.erBetalt = erBetalt;
    }

    public LocalDateTime getMødeTidspunkt() {
        return mødeTidspunkt;
    }

    public void setMødeTidspunkt(LocalDateTime mødeTidspunkt) {
        this.mødeTidspunkt = mødeTidspunkt;
    }


}
