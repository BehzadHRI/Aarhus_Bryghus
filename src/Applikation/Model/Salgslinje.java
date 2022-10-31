package Applikation.Model;

public class Salgslinje {
    private int antal;
    private Pris pris;

    public Salgslinje(int antal, Pris pris){
        this.antal = antal;
        this.pris = pris;
    }

    public int getAntal() {
        return antal;
    }

    public Pris getPris() {
        return pris;
    }

    public void setPris(Pris pris) {
        this.pris = pris;
    }
}
