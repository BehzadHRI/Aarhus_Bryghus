package Applikation.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Salg {
    private LocalDateTime datoTid;
    private ArrayList<Salgslinje> salgslinjer;
    private String betalingsMetode;
    private int samletPris;
    private int fuldklip;
    private int klipPris;
    private boolean klipBrugt;

    public Salg(LocalDateTime datoTid) {
        this.datoTid = datoTid;
        salgslinjer = new ArrayList<>();
        fuldklip = 0;
        klipPris = 0;
        klipBrugt = false;
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
        samletPris();
        if (pris.getAntalKlip()>0){
            fuldklip += pris.getAntalKlip()*salgslinje.getAntal();
            klipPris += pris.getPris();
        }
        return salgslinje;
    }

    public int getFuldklip() {
        return fuldklip;
    }

    public int getKlipPris() {
        return klipPris;
    }

    public boolean isKlipBrugt() {
        return klipBrugt;
    }

    public void setDatoTid(LocalDateTime datoTid) {
        this.datoTid = datoTid;
    }

    public int getSamletPris() {
        return samletPris;
    }

    public void setSamletPris(int samletPris) {
        this.samletPris = samletPris;
    }

    public void setRabat(int rabat){
        samletPris -= rabat;
    }

    public String getBetalingsMetode() {
        return betalingsMetode;
    }

    public void setBetalingsMetode(String betalingsMetode) {
        if (betalingsMetode.equals( "Klippekort")){
            klipBrugt = true;
        }
        this.betalingsMetode = betalingsMetode;
    }

    public void removeSalgslinje(Salgslinje salgslinje) {
        salgslinjer.remove(salgslinje);
        samletPris();
    }

    public void brugKlip(){
        if (samletPris == klipPris){
            klipBrugt = true;
            setBetalingsMetode("KlippeKort");
        }else{
            klipBrugt = true;
            samletPris -= klipPris;
        }
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
        samletPris = result;
        return result;
    }

    @Override
    public String toString() {
        return datoTid.getDayOfMonth() + "-" + datoTid.getMonthValue() + "-" + datoTid.getYear() + " " + datoTid.getHour()+":" + datoTid.getMinute() + "\n"
                + betalingsMetode + ", " + samletPris + ",-";
    }
}
