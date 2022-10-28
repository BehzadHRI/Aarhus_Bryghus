package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Produkt;
import Applikation.Model.Produktgruppe;
import Applikation.Model.Salg;
import Applikation.Model.Salgstype;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class SalgPane extends GridPane {

    ComboBox<Salgstype> cbSalgsTyp = new ComboBox<>();
    ComboBox<Produktgruppe> cbProduktGrup = new ComboBox<>();
    private ListView<Produkt> lvwProd;
    private ListView<Salg> lvwSalgList;

    private Button btnKontant;
    private Button btnDankort;
    private Button btnKlippeKort;

    private TextField txfRabat;
    private TextField txfSum;



    public SalgPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        //-------Salgstyper--------
        this.add(cbSalgsTyp,1,1);
        cbSalgsTyp.getItems().setAll(Controller.getSalgstyper());



        //-------Produktgruppe
        this.add(cbProduktGrup,1,2);
        cbProduktGrup.getItems().setAll(Controller.getProduktGrupper());


        //---------Produkt----------
        Label lblProd = new Label("Produkt");
        this.add(lblProd, 2,0);

        lvwProd = new ListView<>();
        this.add(lvwProd,2,1,1,2);
        lvwProd.setPrefWidth(200);
        lvwProd.setPrefHeight(200);



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













}
