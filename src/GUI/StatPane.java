package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Salg;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class StatPane extends GridPane {
    private ListView<Salg> lvwSalg;
    private DatePicker datePickerSalg, dpfraKlip, dptilKlip;
    private TextField txfSamletSalg;

    public StatPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


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
        lvwSalg.setCellFactory(new SalgCellFactory());
        HBox hbSamletSalg = new HBox(20);
        this.add(hbSamletSalg,0,2);
        Label lblSamletSalg = new Label("Samlet salg:");
        hbSamletSalg.getChildren().add(lblSamletSalg);
        txfSamletSalg = new TextField();
        hbSamletSalg.getChildren().add(txfSamletSalg);
        txfSamletSalg.setDisable(false);

        HBox hbDatoKlipPeriode = new HBox(20);
        this.add(hbDatoKlipPeriode, 0,3);
        Label lblKlipstat = new Label("Antal klip i perioden");
        hbDatoKlipPeriode.getChildren().add(lblKlipstat);
        dpfraKlip = new DatePicker();
        dpfraKlip.setPrefWidth(75);
        hbDatoKlipPeriode.getChildren().add(dpfraKlip);
        dptilKlip = new DatePicker();
        dptilKlip.setPrefWidth(75);
        hbDatoKlipPeriode.getChildren().add(dptilKlip);
        Button btnTjekKlipPeri = new Button("Vis Antal Klip");
        hbDatoKlipPeriode.getChildren().add(btnTjekKlipPeri);
        btnTjekKlipPeri.setOnAction(event -> klipPeriodeAction());





    }
    private void datoValgtAction() {
        lvwSalg.getItems().setAll(Controller.getSalgPåDato(datePickerSalg.getValue()));
        txfSamletSalg.setText(Controller.getPrisforDagensSalg(datePickerSalg.getValue())+"");


    }

    private void klipPeriodeAction(){
        Label lblDato = new Label(dpfraKlip.getValue() + " - " + dptilKlip.getValue());
        this.add(lblDato,0,4);
        TextField txfKlipSolgt = new TextField();
        this.add(txfKlipSolgt, 0,5);

    }

    class SalgCellFactory implements Callback<ListView<Salg>, ListCell<Salg>> {
        public ListCell<Salg> call(ListView<Salg> param){
            return new ListCell<Salg>(){
                public void updateItem(Salg salg, boolean empty){
                    super.updateItem(salg, empty);
                    if (empty || salg == null){
                        setText(null);
                    }else {
                        setText(salg.getDatoTid().getDayOfMonth() + "-" + salg.getDatoTid().getMonthValue() + "-" + salg.getDatoTid().getYear() + " " + salg.getDatoTid().getHour()+":" + salg.getDatoTid().getMinute() + "\n"
                                + salg.getBetalingsMetode() + ", " + salg.getSamletPris() + ",-" + salg.getAntals());
                    }
                }
            };
        }
    }
}
