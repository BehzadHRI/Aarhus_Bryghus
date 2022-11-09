package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Salg;
import Applikation.Model.Salgslinje;
import Applikation.Model.Udlejning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.swing.*;


public class UdlejWindow extends Stage {

    private Udlejning udlejning;
    private int fuldPris, returneretPant;

    public UdlejWindow(String title){
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }


    private ListView<Udlejning> lvwAktiUdl;
    private ListView<Salgslinje> lvwSalgsLinjer;
    private TextField txfSumBetalt, txfTilbBetal, txfAntalRetur;
    private Button btnRetur, btnTilbageBetal;
    private Label lblWarning;
    private CheckBox chbKunPant;



    private void initContent(GridPane pane) {

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);


        //Aktive Udlejninger
        Label lblAktUdl = new Label("Aktive Udlejninger");
        pane.add(lblAktUdl,0,0);

        lvwAktiUdl = new ListView();
        pane.add(lvwAktiUdl,0,1);
        lvwAktiUdl.setPrefHeight(200);
        lvwAktiUdl.getItems().setAll(Controller.getAktiveUdlejninger());
        lvwAktiUdl.setCellFactory(new UdlejningCellFactory());
        ChangeListener<Udlejning> udlejningChangeListener = (ov, oldUdl, newUdl)-> this.selectedUdlejChanged();
        lvwAktiUdl.getSelectionModel().selectedItemProperty().addListener(udlejningChangeListener);



        //Produkter
        Label lblUdlejsPro = new Label("Produkter");
        pane.add(lblUdlejsPro, 1, 0);

        lvwSalgsLinjer = new ListView<>();
        pane.add(lvwSalgsLinjer,1,1);
        lvwSalgsLinjer.setPrefHeight(100);

        lblWarning = new Label("");
        pane.add(lblWarning, 1,2);
        HBox hbretur = new HBox(20);
        pane.add(hbretur,1,3 );


        //Retur
        btnRetur = new Button("Returnere");
        hbretur.getChildren().add(btnRetur);
        btnRetur.setDisable(true);
        btnRetur.setOnAction(event -> this.returnProdAction());

        chbKunPant = new CheckBox("Kun Pant");
        hbretur.getChildren().add(chbKunPant);



        //Antal retur
        txfAntalRetur = new TextField();
        txfAntalRetur.setPromptText("Indtast antal her");
        hbretur.getChildren().add(txfAntalRetur);


        Label lblSumBetalt = new Label("Sum Betalt: ");
        pane.add(lblSumBetalt,2, 1);

        txfSumBetalt = new TextField();
        pane.add(txfSumBetalt,3,1);
        txfSumBetalt.setDisable(true);



        //Tilbagebetaling
        Label lblTilbBetal = new Label("Tilbagebetaling");
        pane.add(lblTilbBetal, 2,2);

        txfTilbBetal = new TextField();
        pane.add(txfTilbBetal,3,2);

        btnTilbageBetal = new Button("TilbageBetal");
        pane.add(btnTilbageBetal, 3,3);

    }


    //Lidt bøvl med returner pant + fuld fustage.
    private void returnProdAction() {
        Salgslinje salgslinje = lvwSalgsLinjer.getSelectionModel().getSelectedItem();
        if (chbKunPant.isSelected()){
            int antal = Integer.parseInt(txfAntalRetur.getText());
            returneretPant += Controller.returnerePantForUdlejning(udlejning, salgslinje, antal);
            txfTilbBetal.setText(returneretPant+"");

        }else {
            try {
                int antal = Integer.parseInt(txfAntalRetur.getText());
                Controller.returnereSalgslinjeForUdlejning(salgslinje, antal, udlejning);
                returneretPant = fuldPris - udlejning.getSamletPris() - udlejning.beregnPant();
                txfTilbBetal.setText(returneretPant + "");
                lvwSalgsLinjer.getItems().setAll(udlejning.getSalgslinjer());
            } catch (NumberFormatException e) {
                lblWarning.setText("Ugyldig indtastet antal");
                lblWarning.setTextFill(Color.RED);
            }
        }
    }

    /*
    Skal have lavet checkbox til at angive om hvorvidt hele produkt er returneret eller blot pant
    fx brugt fustage eller ubrugt fustage osv.
     */

    private void selectedUdlejChanged() {
        udlejning = lvwAktiUdl.getSelectionModel().getSelectedItem();
        lvwSalgsLinjer.getItems().setAll(udlejning.getSalgslinjer());
        fuldPris = udlejning.getSamletPris()+ udlejning.beregnPant();
        txfSumBetalt.setText(fuldPris+"");
        btnRetur.setDisable(false);
    }

/*    private void returProdukterAction(){
        TextInputDialog antal = new TextInputDialog("1");
        antal.setTitle("Retur produkt");
        antal.setHeaderText("Hvor mange produkter skal tages retur?");
        antal.setContentText("Indtast antal på returprodukter");
        Optional<String> result = antal.showAndWait();
        result.isPresent())
        Integer num = Integer.valueOf(result.get());
        Integer input = "";
        if(result.isPresent()){
            input = result.get();
            if()
        }
    }*/

    class UdlejningCellFactory implements Callback<ListView<Udlejning>, ListCell<Udlejning>> {
        public ListCell<Udlejning> call(ListView<Udlejning> param){
            return new ListCell<Udlejning>(){
                public void updateItem(Udlejning salg, boolean empty){
                    super.updateItem(salg, empty);
                    if (empty || salg == null){
                        setText(null);
                    }else {
                        setText(salg.getDatoTid().getDayOfMonth() + "-" + salg.getDatoTid().getMonthValue() + "-" + salg.getDatoTid().getYear() + " " + salg.getDatoTid().getHour()+":" + salg.getDatoTid().getMinute() + "\n"
                                + salg.getKunde().getNavn() + ", " + salg.getSamletPris() + ",-");
                    }
                }
            };
        }
    }




}
