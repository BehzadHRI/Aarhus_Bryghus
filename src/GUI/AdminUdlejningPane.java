package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.*;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AdminUdlejningPane extends GridPane {

    private Salg salg;
    private Udlejning udlejning;
    private Kunde kunde;
    Produktgruppe produktgruppe;
    Produkt produkt;

    private ComboBox<Produktgruppe> cbProGroup;
    private ListView<Produkt> lvwProd;


    public AdminUdlejningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);


        //Gui til at vælge hvilken produktgruppe man vil udleje produkter i.

        VBox vBox = new VBox(10);
        this.add(vBox, 0,0, 1, 2);

        Label lblProdGruppe = new Label("ProduktGruppe");
        vBox.getChildren().add(lblProdGruppe);


        cbProGroup = new ComboBox<>();
        vBox.getChildren().add(cbProGroup);
        cbProGroup.getItems().setAll(Controller.getProduktGrupper());
        cbProGroup.setOnAction(event -> this.selectedProGrupChanged());





        //Listview til at vise de produkter der er i hver produktgruppe.
        lvwProd = new ListView<>();
        this.add(lvwProd,1,0,1,1);
        lvwProd.setPrefHeight(200);
        lvwProd.setPrefWidth(200);

    }

    private void selectedProGrupChanged() {
        Produktgruppe pg = cbProGroup.getSelectionModel().getSelectedItem();
        lvwProd.getItems().setAll(pg.getProdukter());
    }


}
