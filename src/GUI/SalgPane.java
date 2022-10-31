package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class SalgPane extends GridPane {


    ComboBox<Salgstype> cbSalgsTyp = new ComboBox<>();
    ComboBox<Produktgruppe> cbProduktGrup = new ComboBox<>();
    ArrayList<Salg> salgList = new ArrayList<>();

    private ListView<Pris> lvwPris;
    private ListView<Salg> lvwSalgList;
    Produkt produkt;
    Salgslinje antal;
    Pris pris;
    Salg salg;


    private TextField txfRabat;
    private TextField txfSum;

    private Salgstype salgstype;


    public SalgPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        //-------Salgstyper--------
        this.add(cbSalgsTyp,1,1);
        cbSalgsTyp.getItems().setAll(Controller.getSalgstyper());
        ChangeListener<Salgstype> salgstypeChangeListener = (ov, oldST, newST) -> this.selectedSTchanged();
        cbSalgsTyp.getSelectionModel().selectedItemProperty().addListener(salgstypeChangeListener);


        //-------Produktgruppe
        this.add(cbProduktGrup,1,2);
        cbProduktGrup.getItems().setAll(Controller.getProduktGrupper());
        ChangeListener<Produktgruppe> proGrupChangeListener = (ov, oldPrGr, newPrGr) -> this.selectedProGrupChanged();
        cbProduktGrup.getSelectionModel().selectedItemProperty().addListener(proGrupChangeListener);


        //---------Produkt----------
        Label lblProd = new Label("Produkt");
        this.add(lblProd, 2,0);

        lvwPris = new ListView<>();
        this.add(lvwPris,2,1,1,2);
        lvwPris.setPrefWidth(200);
        lvwPris.setPrefHeight(200);


        ChangeListener<Pris> prisChangeListener = (ov, oldPr, newPr) -> this.selectedPrisChanged();
        lvwPris.getSelectionModel().selectedItemProperty().addListener(prisChangeListener);



        //-------Salgsliste----------
        Label lblSalgsList = new Label("SalgsListe");
        this.add(lblSalgsList,3,0);

        lvwSalgList = new ListView<>();
        this.add(lvwSalgList,3,1,1,2);





        //--------Knapper------------
        HBox hbxBetalingsMidButtons = new HBox(10);
        this.add(hbxBetalingsMidButtons,3,3,1,1);

        Button btnKontant = new Button("Kontant");
        hbxBetalingsMidButtons.getChildren().add(btnKontant);

        Button btnDankort = new Button("Dankort");
        hbxBetalingsMidButtons.getChildren().add(btnDankort);

        Button btnKlippeKort = new Button("Klippekort");
        hbxBetalingsMidButtons.getChildren().add(btnKlippeKort);


        //------Rabat-------
        Label lblRabat = new Label("Rabat: ");
        this.add(lblRabat,4,5);

        txfRabat = new TextField();
        this.add(txfRabat,5,5);
        txfRabat.setPrefWidth(200);
        txfRabat.setPrefHeight(10);


        //---------Sum--------
        Label lblSum = new Label("Sum: ");
        this.add(lblSum, 4,6);

        txfSum = new TextField();
        this.add(txfSum,5,6);
        txfSum.setPrefWidth(200);
        txfSum.setPrefHeight(10);

    }




    private void selectedPrisChanged() {
        this.updateControls();
    }


    private void updateControls() {

    }



/*        pris = lvwPris.getSelectionModel().getSelectedItem();
        if(pris != null) {
          lvwSalgList.getItems().setAll();
        }*/


    private void selectedProGrupChanged(){
        Produktgruppe pg = cbProduktGrup.getSelectionModel().getSelectedItem();

        ArrayList<Pris> result = new ArrayList<>();
        for (Pris pr : salgstype.getPriser()){
            if (pr.getProdukt().getProduktgruppe() == pg){
                result.add(pr);
            }
        }
        lvwPris.getItems().setAll(result);
    }

    private void selectedSTchanged(){
        salgstype = cbSalgsTyp.getSelectionModel().getSelectedItem();
    }


}
