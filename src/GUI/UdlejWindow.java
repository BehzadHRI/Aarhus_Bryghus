package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.Produkt;
import Applikation.Model.Udlejning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;
import java.util.OptionalInt;


public class UdlejWindow extends Stage {

    private String navn;


    public String getNavn() {
        return navn;
    }

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
    private ListView<Produkt> lvwProdukter;
    private TextField txfSumBetalt, txfTilbBetal, txfAntalRetur;
    private Button btnRetur, btnTilbageBetal;



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
        lvwAktiUdl.setPrefHeight(100);
        lvwAktiUdl.getItems().setAll(Controller.getAktiveUdlejninger());



        //Produkter
        Label lblUdlejsPro = new Label("Produkter");
        pane.add(lblUdlejsPro, 1, 0);

        lvwProdukter = new ListView<>();
        pane.add(lvwProdukter,1,1);
        lvwProdukter.setPrefHeight(100);


        HBox hbretur = new HBox(20);
        pane.add(hbretur,1,3 );


        //Retur
        btnRetur = new Button("Retur");
        hbretur.getChildren().add(btnRetur);
        /*pane.add(btnRetur,1,3);*/

        //Antal retur
        txfAntalRetur = new TextField();
        txfAntalRetur.setPromptText("Indtast antal her");
        hbretur.getChildren().add(txfAntalRetur);
        /*pane.add(txfAntalRetur,1,4);*/


        Label lblSumBetalt = new Label("Sum Betalt: ");
        pane.add(lblSumBetalt,2, 1);

        txfSumBetalt = new TextField();
        pane.add(txfSumBetalt,3,1);



        //Tilbagebetaling
        Label lblTilbBetal = new Label("Tilbagebetaling");
        pane.add(lblTilbBetal, 2,2);

        txfTilbBetal = new TextField();
        pane.add(txfTilbBetal,3,2);

        btnTilbageBetal = new Button("TilbageBetal");
        pane.add(btnTilbageBetal, 3,3);

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

}
