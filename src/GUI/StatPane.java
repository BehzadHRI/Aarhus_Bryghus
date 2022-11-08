package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Salg;
import Applikation.Model.Salgslinje;
import Applikation.Model.Udlejning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class StatPane extends GridPane {
    private Salg salg;
    private Udlejning udlejning;

    private ListView<Salg> lvwSalg;
    private ListView<Salgslinje> lvwSalgslinjer, lvwReturneretProd;
    private DatePicker datePickerSalg, dpfraKlip, dptilKlip;
    private TextField txfSamletSalg, txfKlipSolgt, txfKlipBrugt;
    private Label lblDato = new Label();
    private Label lblDatoforUdlejning;
    private HBox hbKlipSolgt, hbKlipBrugt;
    private Button btnVisInfo;
    private CheckBox cbhAfslutttet;

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
        ChangeListener<Salg> salgChangeListener = (ov, oldSalg, newSalg) -> this.selectedSalgChanged();
        lvwSalg.getSelectionModel().selectedItemProperty().addListener(salgChangeListener);

        HBox hbSamletSalg = new HBox(20);
        this.add(hbSamletSalg,0,2);
        Label lblSamletSalg = new Label("Samlet salg:");
        hbSamletSalg.getChildren().add(lblSamletSalg);
        txfSamletSalg = new TextField();
        hbSamletSalg.getChildren().add(txfSamletSalg);
        txfSamletSalg.setDisable(true);

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


        hbKlipSolgt = new HBox(20);
        Label lblSolgteKlip = new Label("Solgte klip");
        hbKlipSolgt.getChildren().add(lblSolgteKlip);
        txfKlipSolgt = new TextField();
        hbKlipSolgt.getChildren().add(txfKlipSolgt);


        hbKlipBrugt = new HBox(20);
        Label lblBrugteKlip = new Label("Brugte klip");
        hbKlipBrugt.getChildren().add(lblBrugteKlip);
        txfKlipBrugt = new TextField();
        hbKlipBrugt.getChildren().add(txfKlipBrugt);

        this.add(lblDato, 0, 4);
        this.add(hbKlipSolgt, 0, 5);
        this.add(hbKlipBrugt,0,6);

        btnVisInfo = new Button("Vis detaljer om udlejning");
        this.add(btnVisInfo, 2,0);
        btnVisInfo.setDisable(true);
        btnVisInfo.setOnAction(event -> this.visInfoAction());

        HBox hbAfsluttet = new HBox(20);
        this.add(hbAfsluttet,3,0);
        lblDatoforUdlejning = new Label("Dato");
        hbAfsluttet.getChildren().add(lblDatoforUdlejning);
        cbhAfslutttet = new CheckBox("Afsluttet");
        hbAfsluttet.getChildren().add(cbhAfslutttet);

        lvwSalgslinjer = new ListView<>();
        this.add(lvwSalgslinjer, 2,1,1,1);
        lvwSalgslinjer.setPrefHeight(200);
        lvwSalgslinjer.setPrefWidth(200);

        lvwReturneretProd = new ListView<>();
        this.add(lvwReturneretProd, 3,1,1,1);
        lvwReturneretProd.setPrefWidth(200);
        lvwReturneretProd.setPrefHeight(200);


    }

    private void visInfoAction() {
        lblDatoforUdlejning.setText("Dato:" +salg.getDatoTid().getDayOfMonth() + "-"+salg.getDatoTid().getMonthValue() + " Kl:" + salg.getDatoTid().getHour()+":"+salg.getDatoTid().getMinute());
        cbhAfslutttet.setSelected(!udlejning.isErAktiv());
        lvwSalgslinjer.getItems().setAll(udlejning.getSalgslinjer());
        lvwReturneretProd.getItems().setAll(udlejning.getReturneretProd());
    }

    private void selectedSalgChanged() {
        salg = lvwSalg.getSelectionModel().getSelectedItem();
        if (salg.getClass() == Udlejning.class) {
            udlejning = (Udlejning) salg;
            btnVisInfo.setDisable(false);
        }else {
            btnVisInfo.setDisable(true);
        }
    }

    private void datoValgtAction() {
        lvwSalg.getItems().setAll(Controller.getSalgPåDato(datePickerSalg.getValue()));
        txfSamletSalg.setText(Controller.getPrisforDagensSalg(datePickerSalg.getValue())+"");


    }

    private void klipPeriodeAction(){
            lblDato.setText(dpfraKlip.getValue() + " - " + dptilKlip.getValue());
            txfKlipSolgt.setText(Controller.getSolgteKlipIPeriode(dpfraKlip.getValue(), dptilKlip.getValue()) + "");
            txfKlipBrugt.setText(Controller.getBrugteKlipPeriode(dpfraKlip.getValue(),dptilKlip.getValue())+"");

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
                                + salg.getSalgslinjer() + ", "+ salg.getBetalingsMetode() + ", " + salg.getSamletPris() + ",-");
                    }
                }
            };
        }
    }
}
