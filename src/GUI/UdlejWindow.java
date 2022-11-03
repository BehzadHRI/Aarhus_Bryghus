package GUI;

import Applikation.Model.Produkt;
import Applikation.Model.Udlejning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;


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
    private TextField txfSumBetalt, txfTilbBetal;
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




        Label lblUdlejsPro = new Label("Produkter");
        pane.add(lblUdlejsPro, 1, 0);

        lvwProdukter = new ListView<>();
        pane.add(lvwProdukter,1,1);
        lvwProdukter.setPrefHeight(100);


        btnRetur = new Button("Retur");
        pane.add(btnRetur,1,3);


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


}
