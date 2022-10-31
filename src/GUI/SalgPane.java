package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class SalgPane extends GridPane {


    ComboBox<Salgstype> cbSalgsTyp = new ComboBox<>();
    ComboBox<Produktgruppe> cbProduktGrup = new ComboBox<>();
    ArrayList<Salg> salgList = new ArrayList<>();

    private ListView<Pris> lvwPris;
    private ListView<Salgslinje> lvwSalgList;
    private Button btnBekræft, btnFjern;
    Produkt produkt;
    Salgslinje antal;
    private Pris pris;
    private Salg salg = new Salg(LocalDateTime.now());


    private TextField txfRabat, txfAntal;
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

        HBox hbAntal = new HBox(20);
        this.add(hbAntal, 2,3);
        Label lblAntal = new Label("Antal");
        hbAntal.getChildren().add(lblAntal);
        txfAntal = new TextField();
        txfAntal.setText("1");
        hbAntal.getChildren().add(txfAntal);
        txfAntal.setDisable(true);

        //-------Salgsliste----------
        Label lblSalgsList = new Label("SalgsListe");
        this.add(lblSalgsList,3,0);

        lvwSalgList = new ListView<>();
        this.add(lvwSalgList,3,1,1,2);





        //--------Knapper------------
        btnFjern = new Button("Fjern");
        this.add(btnFjern,3,3);
        btnFjern.setDisable(true);
        btnFjern.setOnAction(event -> this.fjernCase());




        HBox hbxBetalingsMidButtons = new HBox(10);
        this.add(hbxBetalingsMidButtons,3,4,1,1);

        Button btnKontant = new Button("Kontant");
        hbxBetalingsMidButtons.getChildren().add(btnKontant);

        Button btnDankort = new Button("Dankort");
        hbxBetalingsMidButtons.getChildren().add(btnDankort);

        Button btnKlippeKort = new Button("Klippekort");
        hbxBetalingsMidButtons.getChildren().add(btnKlippeKort);

        btnBekræft = new Button("Tilføj");
        this.add(btnBekræft, 2 ,4);
        btnBekræft.setDisable(true);
        btnBekræft.setOnAction(event -> this.bekræftSalgCase());

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
        pris = lvwPris.getSelectionModel().getSelectedItem();
        btnBekræft.setDisable(false);
        txfAntal.setDisable(false);

    }


    private void updateControls() {
        lvwSalgList.getItems().setAll(salg.getAntals());
        txfSum.setText(""+Controller.getSumforSalg(this.salg));
        btnFjern.setDisable(false);

    }

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

    private void bekræftSalgCase(){
        try {
            int antal = Integer.parseInt(txfAntal.getText());
            salg.createSalgslinje(antal, pris);
        }catch (NumberFormatException e){
            Label warning = new Label("Ugyldig antal!");
            this.add(warning, 1,3);
            warning.setTextFill(Color.RED);
        }
        this.updateControls();
    }

    private void fjernCase(){
        salg.removeSalgslinje(lvwSalgList.getSelectionModel().getSelectedItem());
        this.updateControls();
    }

}
