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

    public ArrayList<Salgslinje> getSalgslinjer() {
        return new ArrayList<>(salgslinjer);
    }

    public LocalDateTime getDatoTid() {
        return datoTid;
    }

    public Salgslinje createSalgslinje(int antal, Pris pris) {
        if (antal > 0) {
            if (pris != null) {
                Salgslinje salgslinje = new Salgslinje(antal, pris);
                salgslinjer.add(salgslinje);
                samletPris();

                if (pris != null && pris.getAntalKlip() > 0) {
                    fuldklip += pris.getAntalKlip() * salgslinje.getAntal();
                    klipPris += pris.getPris() * salgslinje.getAntal();
                }
                return salgslinje;
            } else {
                throw new RuntimeException(String.format("Ugyldig Pris"));
            }
        } else {
            throw new RuntimeException(String.format("Ugyldig antal"));
        }
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

    public void setRabat(int rabat) {
        if (rabat > 0) {
            samletPris -= rabat;
        } else {
            throw new RuntimeException("Ugyldig rabat");
        }
    }

    public String getBetalingsMetode() {
        return betalingsMetode;
    }

    public void setBetalingsMetode(String betalingsMetode) {
        if (betalingsMetode.equalsIgnoreCase("Klippekort") || betalingsMetode.equalsIgnoreCase("Dankort") || betalingsMetode.equalsIgnoreCase("Kontant")) {
            if (betalingsMetode.equals("Klippekort")) {
                klipBrugt = true;
            }
            this.betalingsMetode = betalingsMetode;
        }else{
            throw new RuntimeException("Ugyldig Betalingsmetode");
        }
    }


    public void removeSalgslinje(Salgslinje salgslinje) {
        salgslinjer.remove(salgslinje);
        samletPris();
    }

    public void brugKlip() {
        if (fuldklip > 0) {
            if (samletPris == klipPris) {
                klipBrugt = true;
                setBetalingsMetode("Klippekort");
            } else {
                klipBrugt = true;
                samletPris -= klipPris;
            }
        }
    }


    public int samletPris() {
        int result = 0;
        for (Salgslinje ant : salgslinjer) {
            if (ant.getPris() != null) {
//                if (!(ant.getPris().getProdukt().getProduktgruppe().getNavn().equals("Anlæg"))) {
                    result += (ant.getAntal() * ant.getPris().getPris());
//                }
            }
        }
        samletPris = result;
        return result;
    }

    @Override
    public String toString() {
        return datoTid.getDayOfMonth() + "-" + datoTid.getMonthValue() + "-" + datoTid.getYear() + " " + datoTid.getHour() + ":" + datoTid.getMinute() + "\n"
                + betalingsMetode + ", " + samletPris + ",-";
    }
}
