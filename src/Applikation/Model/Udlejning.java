package Applikation.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Udlejning extends Salg {

    private Kunde kunde;

    private boolean erAfleveret;
    private boolean erAktiv;
    private ArrayList<Salgslinje> returneretProd;

    public Udlejning(LocalDateTime datoTid, Kunde kunde) {
        super(datoTid);
        this.erAfleveret = false;
        this.kunde = kunde;
        this.erAktiv = true;
        returneretProd = new ArrayList<>();
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

    public Kunde getKunde() {
        return kunde;
    }

    public boolean isErAfleveret() {
        return erAfleveret;
    }

    public void setErAfleveret(boolean erAfleveret) {
        this.erAfleveret = erAfleveret;
    }

    public boolean isErAktiv() {
        return erAktiv;
    }

    public void setErAktiv(boolean erAktiv) {
        this.erAktiv = erAktiv;
    }

    public void angivReturProd(Salgslinje salgslinje, int antal){
        for (Salgslinje sl : getSalgslinjer()){
            if (sl == salgslinje){
                if (antal <= sl.getAntal()) {
                    sl.setAntal(sl.getAntal() - antal);
                    returneretProd.add(sl);
                    samletPris();
                }
            }
        }
    }

    public ArrayList<Salgslinje> getReturneretProd() {
        return new ArrayList<>(returneretProd);
    }

    public int beregnPant(){
        int result = 0;
        for (Salgslinje salgslinje : getSalgslinjer()) {
            String pg = salgslinje.getPris().getProdukt().getProduktgruppe().getNavn();
            if (pg.equals("Fustage")) {
                result += salgslinje.getAntal() * 200;
            } else if (pg.equals("Kulsyre")) {
                result += salgslinje.getAntal() * 1000;
            }
        }
        return result;
    }

    public int beregnPantForReturneretSalgsLinje(Salgslinje salgslinje, int antal){
        int result = 0;
            String pg = salgslinje.getPris().getProdukt().getProduktgruppe().getNavn();
            if (pg.equals("Fustage")) {
                result += antal * 200;
            } else if (pg.equals("Kulsyre")) {
                result += antal * 1000;
            }

        return result;
    }



}
