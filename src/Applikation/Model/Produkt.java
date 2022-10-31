package Applikation.Model;

public class Produkt {
    private String navn;
    private Produktgruppe produktgruppe;

    public Produkt(String navn, Produktgruppe produktgruppe){
        this.navn = navn;
        setProduktgruppe(produktgruppe);

    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Produktgruppe getProdukttype() {
        return produktgruppe;
    }

    public void setProduktgruppe(Produktgruppe produktgruppe){
        if(this.produktgruppe != produktgruppe){
            Produktgruppe oldProduktgruppe = this.produktgruppe;
            if(oldProduktgruppe != null){
                oldProduktgruppe.removeProdukt(this);
            }
            this.produktgruppe = produktgruppe;
            if(produktgruppe != null){
                produktgruppe.addProdukt(this);
            }
        }
    }

    @Override
    public String toString() {
        return navn;
    }
}
