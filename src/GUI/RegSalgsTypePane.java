package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;


public class RegSalgsTypePane extends GridPane {
    private Salgstype salgstype;
    private Produktgruppe produktgruppe;
    private Produkt produkt;
    private Pris pris;
    private ComboBox<Salgstype> cbSalgstype;
    private ComboBox<Produktgruppe> cbProdGrup;
    private ListView<Produkt> lvwProdukt;
    private ListView<Pris> lvwPris;
    private Button btnTilføj, btnFjern;
    private TextField txfAntalKlip, txfPris;

    public RegSalgsTypePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        HBox hbsalg = new HBox(40);
        this.add(hbsalg, 0, 0);

        Label lblSalgsType = new Label("Salgstype:    ");
        hbsalg.getChildren().add(lblSalgsType);

        cbSalgstype = new ComboBox<>();
        hbsalg.getChildren().add(cbSalgstype);
        cbSalgstype.setPromptText("Salgstype");
        cbSalgstype.setPrefWidth(130);
        cbSalgstype.getItems().setAll(Controller.getSalgstyper());
        ChangeListener<Salgstype> salgstypeChangeListener = (ov, oldST, newST) -> this.selectedSalgsTChangedcase();
        cbSalgstype.getSelectionModel().selectedItemProperty().addListener(salgstypeChangeListener);
        hbsalg.setSpacing(57);

        Button btnAddSt = new Button("Tilføj Salgstype");
        this.add(btnAddSt, 1, 0);
        btnAddSt.setOnAction(event -> addSalgstypeAction());

        HBox hbProGrup = new HBox(40);
        this.add(hbProGrup, 0, 1);
        Label lblProdgrup = new Label("Produktgruppe:");
        hbProGrup.getChildren().add(lblProdgrup);
        cbProdGrup = new ComboBox<>();
        cbProdGrup.getItems().setAll(Controller.getProduktGrupper());
        hbProGrup.getChildren().add(cbProdGrup);
        cbProdGrup.setPromptText("Produktgruppe");
        cbProdGrup.setPrefWidth(130);
        cbProdGrup.setOnAction(event -> this.prodGrupChangedCase());

        Label lblProduk = new Label("Produkter:");
        this.add(lblProduk, 0, 2);
        lvwProdukt = new ListView<>();
        this.add(lvwProdukt, 0, 3, 1, 1);
        lvwProdukt.setPrefHeight(200);
        lvwProdukt.setPrefWidth(200);
        ChangeListener<Produkt> produktChangeListener = (ov, oldPr, newPr) -> this.selectedProdChanged();
        lvwProdukt.getSelectionModel().selectedItemProperty().addListener(produktChangeListener);

        HBox hbPris = new HBox(70);
        this.add(hbPris, 0, 4);
        Label lblpris = new Label("Pris");
        hbPris.getChildren().add(lblpris);
        txfPris = new TextField();
        hbPris.getChildren().add(txfPris);

        HBox hbKlip = new HBox(40);
        this.add(hbKlip, 0, 5);
        Label lblKlip = new Label("Antal klip");
        hbKlip.getChildren().add(lblKlip);
        txfAntalKlip = new TextField("0");
        hbKlip.getChildren().add(txfAntalKlip);

        HBox hbtilføjFjern = new HBox(40);
        this.add(hbtilføjFjern, 1,4);
        btnTilføj = new Button("Tilføj");
        hbtilføjFjern.getChildren().add(btnTilføj);
        btnTilføj.setDisable(true);
        btnTilføj.setOnAction(event -> this.opretPrisCase());

        btnFjern = new Button("Fjern");
        hbtilføjFjern.getChildren().add(btnFjern);
        btnFjern.setDisable(true);
        btnFjern.setOnAction(event -> fjernPrisCase());

        Label lblPris = new Label("Priser:");
        this.add(lblPris, 1, 2);
        lvwPris = new ListView<>();
        this.add(lvwPris, 1, 3, 1, 1);
        lvwPris.setPrefWidth(200);
        lvwPris.setPrefHeight(200);
        ChangeListener<Pris> prisChangeListener = (ov, oldPris, newPris) -> this.prisvalgtchanged();
        lvwPris.getSelectionModel().selectedItemProperty().addListener(prisChangeListener);

    }

    //----------Button actions --------
    private void addSalgstypeAction() {
        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Ny salgstype");
        dialog.setHeaderText("Indtast navn på arrangement:");
        Optional<String> result = dialog.showAndWait();
        String input = "";
        if (result.isPresent()) {
            input = result.get();
            if (input.length() > 0) {
                Controller.createSalgstype(input);
                this.updateCb();
            }
        }
    }

    private void prodGrupChangedCase() {
        produktgruppe = cbProdGrup.getSelectionModel().getSelectedItem();
        lvwProdukt.getItems().setAll(produktgruppe.getProdukter());

    }

    private void selectedProdChanged() {
        produkt = lvwProdukt.getSelectionModel().getSelectedItem();
        btnTilføj.setDisable(false);
    }


    private void selectedSalgsTChangedcase() {
        this.salgstype = cbSalgstype.getSelectionModel().getSelectedItem();
        lvwPris.getItems().setAll(salgstype.getPriser());
    }

    private void prisvalgtchanged(){
        pris = lvwPris.getSelectionModel().getSelectedItem();
        btnFjern.setDisable(false);
    }

    private void opretPrisCase() {
        try {
            int pris = Integer.parseInt(txfPris.getText());
            int klip = Integer.parseInt(txfAntalKlip.getText());
            Controller.createPrisForSalgsType(salgstype, produkt, pris, klip);
            lvwPris.getItems().setAll(salgstype.getPriser());
        }catch (NumberFormatException e){
            Label lblWarning = new Label("Ugyldig indtastning");
            this.add(lblWarning, 1,5);
            lblWarning.setTextFill(Color.RED);
        }
    }

    private void fjernPrisCase(){
        Controller.removePrisFraSalgstype(salgstype, pris);
        lvwPris.getItems().setAll(salgstype.getPriser());

    }


    private void updateCb() {
        cbSalgstype.getItems().setAll(Controller.getSalgstyper());
    }
}
