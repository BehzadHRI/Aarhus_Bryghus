package Applikation.Model;

import java.time.LocalDateTime;

public class Udlejning extends Salg {

    private Kunde kunde;

    private boolean erAfleveret;
    private boolean erAktiv;

    public Udlejning(LocalDateTime datoTid, Kunde kunde, boolean erAktiv) {
        super(datoTid);
        this.erAfleveret = false;
        this.kunde = kunde;
        this.erAktiv = erAktiv;
    }

    public void setKunde (Kunde kunde){
        if(this.kunde != kunde){
            Kunde oldKunde = this.kunde;
            if(oldKunde != null){
                oldKunde.removeUdlejning(this);
            }
            this.kunde = kunde;
            if(kunde != kunde){
                kunde.addUdlejning(this);
            }
        }
    }

}
