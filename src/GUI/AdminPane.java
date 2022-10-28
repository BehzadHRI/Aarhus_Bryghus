package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;
import Applikation.Model.Salgstype;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class AdminPane extends GridPane {
    private Produktgruppe produktGruppe;
    private ListView<Produktgruppe> lvwProGrup;
    private ListView<Produkt> lvwProd;
    //
    private ListView<Salgstype> lvwSalgsT;
    //
    private Button btnSletProGru;

    public AdminPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        //----------------Produktgrupper-----------------
        Label lblProdGruppe = new Label("Produktgruppe:");
        this.add(lblProdGruppe, 1, 0);

        lvwProGrup = new ListView<>();
        this.add(lvwProGrup, 1, 1, 1, 2);
        lvwProGrup.setPrefHeight(200);
        lvwProGrup.setPrefWidth(200);
        lvwProGrup.getItems().setAll(Controller.getProduktGrupper());

        ChangeListener<Produktgruppe> produktgruppeChangeListener = (ov, oldPG, newPG) -> this.selectedProdGrupChanged();
        lvwProGrup.getSelectionModel().selectedItemProperty().addListener(produktgruppeChangeListener);

        HBox hbxProGrupbuttons = new HBox(40);
        this.add(hbxProGrupbuttons, 1, 4, 2, 1);
        hbxProGrupbuttons.setSpacing(10);

        Button btnOpretProGrup = new Button("Opret");
        hbxProGrupbuttons.getChildren().add(btnOpretProGrup);
        btnOpretProGrup.setOnAction(event -> this.opretProGruAction());

        btnSletProGru = new Button("Slet");
        hbxProGrupbuttons.getChildren().add(btnSletProGru);
        btnSletProGru.setOnAction(event -> this.sletProGruAction());
        btnSletProGru.setDisable(true);

        //--------- produkter ------
        Label lblProd = new Label("Produkter:");
        this.add(lblProd, 2, 0);

        lvwProd = new ListView<>();
        this.add(lvwProd, 2, 1, 1, 2);
        lvwProd.setPrefHeight(200);
        lvwProd.setPrefWidth(200);

        HBox hbxProButtons = new HBox(40);
        this.add(hbxProButtons, 2,4,2,1);
        hbxProButtons.setSpacing(10);

        Button btnOpretPro = new Button("Opret");
        hbxProButtons.getChildren().add(btnOpretPro);

        Button btnSletPro = new Button("Slet");
        hbxProButtons.getChildren().add(btnSletPro);


        //--------------Registrer ny salgstype------------
    /*    Label lblSalgsT = new Label("Salgstype");
        this.add(lblSalgsT, 0, 0);

        lvwSalgsT = new ListView<>();
        this.add(lvwSalgsT, 0, 1, 1, 2);


        lvwSalgsT.getItems().setAll(Controller.getSalgstyper());


        HBox hbxSalgsTButtons = new HBox(40);
        this.add(hbxSalgsTButtons, 0,4,2,1);
        hbxSalgsTButtons.setSpacing(10);

        Button btnOpretSalgsT = new Button("Opret");
        hbxSalgsTButtons.getChildren().add(btnOpretSalgsT);

        Button btnSletSalgsT = new Button("Slet");
        hbxSalgsTButtons.getChildren().add(btnSletSalgsT);*/






    }

    //-------- Button Actions --------
    private void opretProGruAction() {
        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Ny produktgruppe");
        dialog.setHeaderText("Indtast navn på produktgruppe");
        Optional<String> result = dialog.showAndWait();
        String input = "";
        if (result.isPresent()) {
            input = result.get();
            if (input.length() > 0) {
                Controller.createProduktGruppe(input);
                this.updatePane();
            }
        }
    }

    private void sletProGruAction() {
        if (produktGruppe.getProdukter().size()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Produktgruppe ikke fjernet");
            alert.setHeaderText("Denne produktgruppe indeholder produkter");
            alert.showAndWait();
        }else{
            Controller.removeProduktGruppe(produktGruppe);
            this.updatePane();
        }
    }

    //-----------------------------
    private void selectedProdGrupChanged() {
        this.updateControls();
        btnSletProGru.setDisable(false);
    }

    private void updateControls() {
        produktGruppe = lvwProGrup.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            lvwProd.getItems().setAll(produktGruppe.getProdukter());
        }
    }

    private void updatePane() {
        lvwProGrup.getItems().setAll(Controller.getProduktGrupper());
    }
}
