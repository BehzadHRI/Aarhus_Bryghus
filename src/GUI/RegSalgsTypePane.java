package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Optional;


public class RegSalgsTypePane extends GridPane {
    private Salgstype salgstype;
    private ComboBox<Salgstype> cbSalgstype;
    private ComboBox<Produktgruppe> cbProdGrup;
    private ListView<Produkt> lvwProdukt;
    private ListView<Pris> lvwPris;

    public RegSalgsTypePane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        HBox hbsalg = new HBox(40);
        this.add(hbsalg,0,0);

        Label lblSalgsType = new Label("Salgstype:    ");
        hbsalg.getChildren().add(lblSalgsType);

        cbSalgstype = new ComboBox<>();
        hbsalg.getChildren().add(cbSalgstype);
        cbSalgstype.setPrefWidth(100);
        cbSalgstype.getItems().setAll(Controller.getSalgstyper());
        ChangeListener<Salgstype> salgstypeChangeListener = (ov, oldST, newST) -> this.selectedSalgsTChanged();
        cbSalgstype.getSelectionModel().selectedItemProperty().addListener(salgstypeChangeListener);
        hbsalg.setSpacing(57);

        Button btnAddSt = new Button("Tilføj Salgstype");
        this.add(btnAddSt, 1,0);
        btnAddSt.setOnAction(event -> addSalgstypeAction());

        HBox hbProGrup = new HBox(40);
        this.add(hbProGrup,0,1);
        Label lblProdgrup = new Label("Produktgruppe:");
        hbProGrup.getChildren().add(lblProdgrup);
        cbProdGrup = new ComboBox<>();
        cbProdGrup.getItems().setAll(Controller.getProduktGrupper());
        hbProGrup.getChildren().add(cbProdGrup);
        cbProdGrup.setPrefWidth(100);

        Label lblProduk = new Label("Produkter:");
        this.add(lblProduk, 0,2);
        lvwProdukt = new ListView<>();
        this.add(lvwProdukt, 0,3,1,1);
        lvwProdukt.setPrefHeight(200);
        lvwProdukt.setPrefWidth(200);


        Label lblPris = new Label("Priser:");
        this.add(lblPris, 1,2);
        lvwPris = new ListView<>();
        this.add(lvwPris, 1,3,1,1);
        lvwPris.setPrefWidth(200);
        lvwPris.setPrefHeight(200);





    }

    //----------Button actions --------
    private void addSalgstypeAction(){
        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Ny salgstype");
        dialog.setHeaderText("Indtast navn på arrangement:");
        Optional<String> result = dialog.showAndWait();
        String input = "";
        if (result.isPresent()){
            input = result.get();
            if (input.length()>0){
                Controller.createSalgstype(input);
                this.updatePane();
            }
        }
    }


    private void selectedSalgsTChanged(){
        this.salgstype = cbSalgstype.getSelectionModel().getSelectedItem();

    }

    private void updatePane(){
        cbSalgstype.getItems().setAll(Controller.getSalgstyper());
    }
}
