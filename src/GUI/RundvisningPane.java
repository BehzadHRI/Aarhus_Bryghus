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

import java.time.LocalDateTime;

public class RundvisningPane extends GridPane {

    private Kunde kunde;
    private Rundvisning rundvisning = new Rundvisning(LocalDateTime.now(),kunde,LocalDateTime.now(),0,false);
    private Pris pris;

    private Button btnopretRundv, btnBestilRundv;
    private ListView lvwRundvisningObj;
    private TextField txfKundeNavn, txfKundeTlf, txfKundeAdresse, txfAntalPers, txfSum;

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

        btnopretRundv = new Button("Opret Rundvisning");
        vbox1.getChildren().add(btnopretRundv);
        btnopretRundv.setOnAction(event -> opretRundvisning());

        lvwRundvisningObj = new ListView();
        vbox1.getChildren().add(lvwRundvisningObj);
        lvwRundvisningObj.setPrefHeight(150);

        btnBestilRundv = new Button("Bestil Rundvisning");
        vbox1.getChildren().add(btnBestilRundv);

    }

    private void opretRundvisning(){
        kunde = new Kunde(txfKundeNavn.getText(),txfKundeTlf.getText(),txfKundeAdresse.getText());
        int antal = Integer.parseInt(txfAntalPers.getText());
        Controller.setDatoTidforSalg(rundvisning, LocalDateTime.now());
        Controller.createSalg(rundvisning);
        Controller.setKundeForRundvisning(rundvisning,kunde);/*
        lvwRundvisningObj.getItems().setAll(Controller.g)*/
        this.updateControls();
    }


    private void updateControls(){
        lvwRundvisningObj.getItems().setAll(rundvisning.getSalgslinjer());
        txfAntalPers.clear();
        txfKundeAdresse.clear();
        txfKundeNavn.clear();
        txfKundeTlf.clear();
    }







}
