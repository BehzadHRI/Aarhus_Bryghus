package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Salg;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StatPane extends GridPane {
    private ListView<Salg> lvwSalg;
    private DatePicker datePickerSalg;
    private TextField txfSamletSalg;

    public StatPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);


        HBox hbDato = new HBox(20);
        this.add(hbDato,0,0);
        datePickerSalg = new DatePicker();
        Label lblDato = new Label("Dato:");
        hbDato.getChildren().add(lblDato);
        hbDato.getChildren().add(datePickerSalg);
        datePickerSalg.setOnAction(event -> this.datoValgtAction());

        lvwSalg = new ListView<>();
        this.add(lvwSalg, 0,1, 1,1);
        lvwSalg.setPrefWidth(200);
        lvwSalg.setPrefHeight(200);
        HBox hbSamletSalg = new HBox(20);
        this.add(hbSamletSalg,0,2);
        Label lblSamletSalg = new Label("Samlet salg:");
        hbSamletSalg.getChildren().add(lblSamletSalg);
        txfSamletSalg = new TextField();
        hbSamletSalg.getChildren().add(txfSamletSalg);
        txfSamletSalg.setDisable(false);







    }
    private void datoValgtAction() {
        lvwSalg.getItems().setAll(Controller.getSalgPåDato(datePickerSalg.getValue()));
        txfSamletSalg.setText(Controller.getPrisforDagensSalg(datePickerSalg.getValue())+"");
    }
}
