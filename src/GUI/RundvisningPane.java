package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Kunde;
import Applikation.Model.Pris;
import Applikation.Model.Rundvisning;
import Applikation.Model.Salgslinje;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Time;
import java.time.LocalDateTime;

public class RundvisningPane extends GridPane {

    private Kunde kunde;
    private Rundvisning rundvisning = new Rundvisning(LocalDateTime.now(),kunde,LocalDateTime.now(),0,false);
    private Pris pris;

    private Button btnopretRundv, btnBestilRundv;
    private ListView lvwRundvisningObj;
    private TextField txfKundeNavn, txfKundeTlf, txfKundeAdresse, txfAntalPers, txfTid, txfPris, txfSum;
    private DatePicker datePicker;


    public RundvisningPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        VBox vbox2 = new VBox(10);
        this.add(vbox2,0,0);


        Label lblKundeNavn = new Label("Kundenavn");
        vbox2.getChildren().add(lblKundeNavn);
        txfKundeNavn = new TextField();
        vbox2.getChildren().add(txfKundeNavn);


        Label lblKundeTlf = new Label("Telefonnummer");
        vbox2.getChildren().add(lblKundeTlf);
        txfKundeTlf = new TextField();
        vbox2.getChildren().add(txfKundeTlf);


        Label lblAdresse = new Label("Adresse");
        vbox2.getChildren().add(lblAdresse);
        txfKundeAdresse = new TextField();
        vbox2.getChildren().add(txfKundeAdresse);


        VBox vbox1 = new VBox(10);
        this.add(vbox1,2,0);

        txfAntalPers = new TextField();
        txfAntalPers.setPromptText("Antal Personer");
        vbox1.getChildren().add(txfAntalPers);
        txfAntalPers.setPrefWidth(100);

        datePicker = new DatePicker();
        vbox1.getChildren().add(datePicker);

        txfTid = new TextField();
        txfTid.setPromptText("Indtast mødetidspunkt HH:MM");
        vbox1.getChildren().add(txfTid);

        txfPris = new TextField();
        txfPris.setPromptText("Indtast Pris for Rundvisningen");
        vbox1.getChildren().add(txfPris);


        btnopretRundv = new Button("Opret Rundvisning");
        vbox1.getChildren().add(btnopretRundv);
        btnopretRundv.setOnAction(event -> tilføjRundvisning());

        lvwRundvisningObj = new ListView();
        vbox1.getChildren().add(lvwRundvisningObj);
        lvwRundvisningObj.setPrefHeight(150);

        btnBestilRundv = new Button("Bestil Rundvisning");
        vbox1.getChildren().add(btnBestilRundv);

    }

    private void tilføjRundvisning(){
        int antalPers = Integer.parseInt(txfAntalPers.getText());
        Controller.createSalgslinjeForRundvisning(rundvisning,antalPers,pris);
        kunde = new Kunde(txfKundeNavn.getText(),txfKundeTlf.getText(),txfKundeAdresse.getText());
        Controller.setKundeForRundvisning(rundvisning,kunde);

}

    private void bestilRundvisning(){
        if(rundvisning != null){

        }
    }


    private void updateControls(){
        lvwRundvisningObj.getItems().setAll(rundvisning.getSalgslinjer());
        txfAntalPers.clear();
        txfKundeAdresse.clear();
        txfKundeNavn.clear();
        txfKundeTlf.clear();
    }







}
