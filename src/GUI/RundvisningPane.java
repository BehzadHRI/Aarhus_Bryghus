package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Kunde;
import Applikation.Model.Pris;
import Applikation.Model.Rundvisning;
import Applikation.Model.Salgslinje;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.TimeStringConverter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class RundvisningPane extends GridPane {

    private Kunde kunde;
    private Rundvisning rundvisning = new Rundvisning(LocalDateTime.now(),kunde,LocalDateTime.now(),0,false);
    private Pris pris;
    private int hour, min;

    private Button btnopretRundv, btnBestilRundv;
    private ListView lvwRundvisningObj;
    private TextField txfKundeNavn, txfKundeTlf, txfKundeAdresse, txfAntalPers, txfTid, txfPris, txfSum;
    private DatePicker datePicker;
    private Label lblWarning;
    private ComboBox<Integer> cbHour, cbMin;


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

        lblWarning = new Label();
        this.add(lblWarning, 3,0);

        datePicker = new DatePicker();
        HBox hBoxDateTime = new HBox(20);
        hBoxDateTime.getChildren().add(datePicker);
        cbHour = new ComboBox();
        hBoxDateTime.getChildren().add(cbHour);
        for (int i = 0; i <24; i++){
            cbHour.getItems().add(i);
        }
        cbHour.setOnAction(event -> { hour = cbHour.getValue();});
        cbMin = new ComboBox();
        hBoxDateTime.getChildren().add(cbMin);
        for (int i = 0; i <=60; i+=5){
            cbMin.getItems().add(i);
        }
        cbMin.setOnAction(event -> { min = cbMin.getValue();});
        vbox1.getChildren().add(hBoxDateTime);

        txfPris = new TextField();
        txfPris.setPromptText("Indtast Pris for Rundvisningen");
        vbox1.getChildren().add(txfPris);

        HBox hBox1 = new HBox(20);
        vbox1.getChildren().add(hBox1);
        btnopretRundv = new Button("Opret Rundvisning");
        hBox1.getChildren().add(btnopretRundv);
        btnopretRundv.setDisable(true);
        ComboBox<String> cbBetaling = new ComboBox<>();
        cbBetaling.getItems().add("Kontant");
        cbBetaling.getItems().add("Dankort");
        cbBetaling.setOnAction(event -> {
            Controller.setBetalingsmetode(rundvisning, cbBetaling.getValue());
            btnopretRundv.setDisable(false);
        });
        hBox1.getChildren().add(cbBetaling);
        cbBetaling.setPromptText("Betalingsmetode");

        btnopretRundv.setOnAction(event -> tilføjRundvisning());

        lvwRundvisningObj = new ListView();
        vbox1.getChildren().add(lvwRundvisningObj);
        lvwRundvisningObj.setPrefHeight(150);

    }

    private void tilføjRundvisning(){
        try {
            String kdNavn = txfKundeNavn.getText();
            String kdTlf = txfKundeTlf.getText();
            String kdAdr = txfKundeAdresse.getText();
            int antalPers = Integer.parseInt(txfAntalPers.getText());

            int year = datePicker.getValue().getYear();
            int month = datePicker.getValue().getMonthValue();
            int day = datePicker.getValue().getDayOfMonth();



            Controller.createSalgslinjeForRundvisning(rundvisning,antalPers);
            Controller.setDatoTidforRundvisning(rundvisning, year,month,day,hour,min);
            kunde = new Kunde(kdNavn, kdTlf, kdAdr);
            Controller.setKundeForRundvisning(rundvisning, kunde);
            Controller.createSalg(rundvisning);
            if (txfPris.getText().length()>0){
                Controller.setPrisforRundvisning(rundvisning, Integer.parseInt(txfPris.getText()));
            }
            updateControls();
        } catch (NumberFormatException e){
            lblWarning.setText("Ugyldigt antal personer");
            lblWarning.setTextFill(Color.RED);
        }
}

    private void updateControls(){
        lvwRundvisningObj.getItems().setAll(Controller.getAlleRundvisninger());
        txfAntalPers.clear();
        txfKundeAdresse.clear();
        txfKundeNavn.clear();
        txfKundeTlf.clear();
        kunde = null;
        rundvisning = new Rundvisning(LocalDateTime.now(), kunde, LocalDateTime.now(), 1, true);
        btnopretRundv.setDisable(true);


    }







}
