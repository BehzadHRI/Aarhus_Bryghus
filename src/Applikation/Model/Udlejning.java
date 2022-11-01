package Applikation.Model;

import java.time.LocalDateTime;

public class Udlejning extends Salg {

    private Kunde kunde;

    private boolean erAfleveret;

    public Udlejning(LocalDateTime datoTid, Kunde kunde) {
        super(datoTid);
        this.erAfleveret = false;
        this.kunde = kunde;
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
