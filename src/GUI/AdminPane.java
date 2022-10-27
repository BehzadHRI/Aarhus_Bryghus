package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class AdminPane extends GridPane {
    private Produktgruppe produktGruppe;
    private ListView<Produktgruppe> lvwProGrup;
    private ListView<Produkt> lvwProd;

    public AdminPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblProdGruppe = new Label("Produktgruppe:");
        this.add(lblProdGruppe, 0, 0);

        lvwProGrup = new ListView<>();
        this.add(lvwProGrup, 0, 1, 1, 4);
        lvwProGrup.setPrefHeight(200);
        lvwProGrup.setPrefWidth(100);
        lvwProGrup.getItems().setAll(Controller.getProduktGrupper());

        ChangeListener<Produktgruppe> produktgruppeChangeListener = (ov, oldPG, newPG) -> this.selectedProdGrupChanged();

        Label lblProd = new Label("Produkter:");
        this.add(lblProd, 1, 0);

        lvwProd = new ListView<>();
        this.add(lvwProd, 1, 1, 1, 4);
        lvwProd.setPrefHeight(200);
        lvwProd.setPrefWidth(100);


    }

    //-----------------------------
    private void selectedProdGrupChanged() {
        this.updateControls();
    }

    private void updateControls() {
        produktGruppe = lvwProGrup.getSelectionModel().getSelectedItem();
        if (produktGruppe != null){
            lvwProd.getItems().setAll(produktGruppe.getProdukter());
        }
    }
}
