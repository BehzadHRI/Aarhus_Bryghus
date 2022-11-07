package Applikation.Model;

import java.util.ArrayList;

public class Kunde {

    private String navn;
    private String telefon;
    private String adresse;

    ArrayList<Udlejning> udlejninger = new ArrayList<>();
    ArrayList<Rundvisning> rundvisninger = new ArrayList<>();

    public Kunde(String navn, String tlf, String adresse){
        this.navn = navn;
        this.telefon = tlf;
        this.adresse = adresse;
    }

    public ArrayList<Udlejning> getUdlejninger(){
        return new ArrayList<>(udlejninger);
    }

    public ArrayList<Rundvisning> getRundvisninger(){
        return new ArrayList<>(rundvisninger);
    }



    public void addUdlejning(Udlejning udlejning){
        if(!udlejninger.contains(udlejning)){
            udlejninger.add(udlejning);
            udlejning.setKunde(this);
        }
    }

    public void removeUdlejning(Udlejning udlejning){
        if(!udlejninger.contains(udlejning)){
            udlejninger.remove(udlejning);
            udlejning.setKunde(null);
        }
    }

    public void addRundvisning(Rundvisning rundvisning){
        if(!rundvisninger.contains(rundvisning)){
            rundvisninger.add(rundvisning);
            rundvisning.setKunde(this);
        }
    }

    public void removeRundvisning(Rundvisning rundvisning){
        if(!rundvisninger.contains(rundvisning)){
            rundvisninger.remove(rundvisning);
            rundvisning.setKunde(null);
        }
    }

}
