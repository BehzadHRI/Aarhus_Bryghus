package Applikation.Model;

public class Antal {
    private int antal;
    private Pris pris;

    public Antal(int antal, Pris pris){
        this.antal = antal;
        this.pris = pris;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public Pris getPris() {
        return pris;
    }

    public void setPris(Pris pris) {
        this.pris = pris;
    }
}
