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
    ArrayList<Salgslinje> salgList = new ArrayList<>();

    private ListView<Pris> lvwPris;
    private ListView<Salgslinje> lvwSalgList;
    private Button btnBekræft, btnFjern, btnBrugKlip,btnGodkend;
    private Button btnKontant, btnDankort;
    private Pris pris;
    private Salg salg = new Salg(LocalDateTime.now());


    private TextField txfRabat, txfAntal;
    private TextField txfSum, txfKlip;

    private Salgstype salgstype;


    public SalgPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        //-------Salgstyper--------
        this.add(cbSalgsTyp, 1, 1);
        cbSalgsTyp.setPromptText("Salgstype");
        cbSalgsTyp.getItems().setAll(Controller.getSalgstyper());
        ChangeListener<Salgstype> salgstypeChangeListener = (ov, oldST, newST) -> this.selectedSTchanged();
        cbSalgsTyp.getSelectionModel().selectedItemProperty().addListener(salgstypeChangeListener);


        //-------Produktgruppe
        this.add(cbProduktGrup, 1, 2);
        cbProduktGrup.setPromptText("ProduktGruppe");
        cbProduktGrup.getItems().setAll(Controller.getProduktGrupper());
        ChangeListener<Produktgruppe> proGrupChangeListener = (ov, oldPrGr, newPrGr) -> this.selectedProGrupChanged();
        cbProduktGrup.getSelectionModel().selectedItemProperty().addListener(proGrupChangeListener);


        //---------Produkt----------
        Label lblProd = new Label("Produkt");
        this.add(lblProd, 2, 0);

        lvwPris = new ListView<>();
        this.add(lvwPris, 2, 1, 1, 2);
        lvwPris.setPrefWidth(200);
        lvwPris.setPrefHeight(200);

        ChangeListener<Pris> prisChangeListener = (ov, oldPr, newPr) -> this.selectedPrisChanged();
        lvwPris.getSelectionModel().selectedItemProperty().addListener(prisChangeListener);

        HBox hbAntal = new HBox(20);
        this.add(hbAntal, 2, 3);
        Label lblAntal = new Label("Antal");
        hbAntal.getChildren().add(lblAntal);
        txfAntal = new TextField();
        txfAntal.setText("1");
        hbAntal.getChildren().add(txfAntal);
        txfAntal.setDisable(true);

        //-------Salgsliste----------
        Label lblSalgsList = new Label("SalgsListe");
        this.add(lblSalgsList, 3, 0);

        lvwSalgList = new ListView<>();
        this.add(lvwSalgList, 3, 1, 1, 2);
        lvwSalgList.setPrefHeight(200);
        lvwSalgList.setPrefWidth(200);

        //-----Klip------
        HBox hbKlip = new HBox(10);
        this.add(hbKlip, 3,4,1,3);
        Label lblKlip = new Label("Antal klip:");
        hbKlip.getChildren().add(lblKlip);

        txfKlip = new TextField();
        txfKlip.setDisable(true);
        hbKlip.getChildren().add(txfKlip);

        btnBrugKlip = new Button("Brug klip");
        hbKlip.getChildren().add(btnBrugKlip);
        btnBrugKlip.setOnAction(event -> this.brugKlipCase());



        //------Rabat-------
        HBox hbRabat = new HBox(23);
        this.add(hbRabat,3,5);

        Label lblRabat = new Label("Rabat: ");
        hbRabat.getChildren().add(lblRabat);

        txfRabat = new TextField();
        hbRabat.getChildren().add(txfRabat);

        Button btnAnvend = new Button("Anvend");
        hbRabat.getChildren().add(btnAnvend);
        btnAnvend.setOnAction(event -> this.anvendRabatCase());



        //---------Sum--------
        Label lblSum = new Label("Sum: ");
        txfSum = new TextField();

        HBox hbSum = new HBox(30);
        hbSum.getChildren().add(lblSum);
        hbSum.getChildren().add(txfSum);
        this.add(hbSum, 3, 6);



        //--------Knapper------------
        btnFjern = new Button("Fjern");
        this.add(btnFjern, 3, 3);
        btnFjern.setDisable(true);
        btnFjern.setOnAction(event -> this.fjernCase());


        HBox hbxBetalingsMidButtons = new HBox(10);
        this.add(hbxBetalingsMidButtons, 3, 7, 1, 1);

        btnKontant = new Button("Kontant");
        hbxBetalingsMidButtons.getChildren().add(btnKontant);
        btnKontant.setOnAction(event -> this.kontantCase());

        btnDankort = new Button("Dankort");
        hbxBetalingsMidButtons.getChildren().add(btnDankort);
        btnDankort.setOnAction(event -> this.dankortCase());

        btnBekræft = new Button("Tilføj");
        this.add(btnBekræft, 2, 4);
        btnBekræft.setDisable(true);
        btnBekræft.setOnAction(event -> this.bekræftSalgCase());


        btnGodkend = new Button("Godkend");
        this.add(btnGodkend, 4,6);
        btnGodkend.setOnAction(event -> this.godkendSalgCase());
        btnGodkend.setDisable(true);


    }

    private void brugKlipCase() {
        Controller.brugKlipPåSalg(salg);
        txfSum.setText(salg.getSamletPris()+"");
        btnBrugKlip.setDisable(true);
        txfKlip.setText(0+"");
        if (!(salg.getBetalingsMetode()==null)){
            btnDankort.setDisable(true);
            btnKontant.setDisable(true);
            btnGodkend.setDisable(false);
        }else {
            btnBrugKlip.setDisable(true);
        }

    }

    private void selectedPrisChanged() {
        pris = lvwPris.getSelectionModel().getSelectedItem();
        btnBekræft.setDisable(false);
        txfAntal.setDisable(false);

    }

    private void updateControls() {
        lvwSalgList.getItems().setAll(salg.getSalgslinjer());
        txfSum.setText(salg.getSamletPris() + "");
        btnFjern.setDisable(false);
        btnBrugKlip.setDisable(false);
        btnKontant.setDisable(false);
        btnDankort.setDisable(false);


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
            Controller.createSalgsLinjeforSalg(salg, antal, pris);
            txfKlip.setText(salg.getFuldklip()+"");
        } catch (NumberFormatException e) {
            Label warning = new Label("Ugyldig antal!");
            this.add(warning, 1,3);
            warning.setTextFill(Color.RED);
        }
        this.updateControls();
    }

    private void fjernCase(){
        Controller.removeSalgslinjefraSalg(salg, lvwSalgList.getSelectionModel().getSelectedItem());
        this.updateControls();
    }


    private void anvendRabatCase() {
        try {
            int rabat = Integer.parseInt(txfRabat.getText());
            Controller.setRabatforSalg(salg, rabat);
            this.updateControls();
        }catch (NumberFormatException e){
            Label warning = new Label("Ugyldig beløb!");
            this.add(warning, 1,3);
            warning.setTextFill(Color.RED);
        }
    }

    private void kontantCase(){
        Controller.setBetalingsmetode(salg, "Kontant");
        btnDankort.setDisable(true);
        btnGodkend.setDisable(false);

    }
    private void dankortCase(){
        Controller.setBetalingsmetode(salg, "Dankort");
        btnKontant.setDisable(true);
        btnGodkend.setDisable(false);

    }

    private void godkendSalgCase(){
        if (salg.getSamletPris() != 0) {
            Controller.setDatoTidforSalg(salg, LocalDateTime.now());
            Controller.createSalg(salg);
            salg = new Salg(LocalDateTime.now());
            updateControls();
        }
    }

    public void updatePane(){
        cbProduktGrup.getItems().setAll(Controller.getProduktGrupper());
        cbSalgsTyp.getItems().setAll(Controller.getSalgstyper());
    }



}
