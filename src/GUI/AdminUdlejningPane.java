package GUI;

import Applikation.Controller.Controller;
import Applikation.Model.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminUdlejningPane extends GridPane {

    private Salg salg;
    private Udlejning udlejning;
    private Kunde kunde;
    Produktgruppe produktgruppe;
    Produkt produkt;

    private ComboBox<Produktgruppe> cbProGroup;
    private ListView<Produkt> lvwProd;
    private ListView<Salgslinje> lvwUdlejningsListe;
    private ListView<Udlejning> lvwAktiveUdlejninger;

    private Button btnTilføj, btnFjern;
    private Button btnBekræftUdlej;
    private Button btnAfslutUdl;
    private TextField txfAntal;
    private TextField txfKundeNavn, txfKundeTlf, txfKundeAdresse;
    private TextField txfSumInklPant;
    private Pris pris;


    public AdminUdlejningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        //Gui til at vælge hvilken produktgruppe man vil udleje produkter i.

        VBox vbox = new VBox(10);
        this.add(vbox, 0,0, 1, 2);

        Label lblProdGruppe = new Label("ProduktGruppe");
        vbox.getChildren().add(lblProdGruppe);


        cbProGroup = new ComboBox<>();
        vbox.getChildren().add(cbProGroup);
        cbProGroup.getItems().setAll(Controller.getProduktGrupper());
        cbProGroup.setOnAction(event -> this.selectedProGrupChanged());



        //Listview til at vise de produkter der er i hver produktgruppe.
        lvwProd = new ListView<>();
        this.add(lvwProd,1,1,1,1);
        lvwProd.setPrefHeight(200);
        lvwProd.setPrefWidth(200);



        //Knapper til at tilføje valgte produkter
        VBox vbox1 = new VBox(30);
        this.add(vbox1,2,1);

        btnTilføj = new Button("Tilføj");
        vbox1.getChildren().add(btnTilføj);

        txfAntal = new TextField();
        vbox1.getChildren().add(txfAntal);
        txfAntal.setPrefWidth(60);

        btnFjern = new Button("Fjern");
        vbox1.getChildren().add(btnFjern);


        //Udlejningslisten til udlejede produkter
        VBox vBox1 = new VBox(10);
        this.add(vBox1, 3,0, 1, 2);

        Label lblUdlKurv = new Label("UdlejningsKurv");
        vBox1.getChildren().add(lblUdlKurv);

        lvwUdlejningsListe = new ListView<>();
        vBox1.getChildren().add(lvwUdlejningsListe);
        lvwUdlejningsListe.setPrefHeight(200);
        lvwUdlejningsListe.setPrefWidth(200);


        //Tilføj kunde til udlejning
        VBox vbox2 = new VBox(10);
        this.add(vbox2,4,1);
        Label lblKundeNavn = new Label("Kundenavn");
        vbox2.getChildren().add(lblKundeNavn);
        txfKundeNavn = new TextField();
        vbox2.getChildren().add(txfKundeNavn);

        Label lblKundeTlf = new Label("Telefonnummer");
        vbox2.getChildren().add(lblKundeTlf);
        txfKundeTlf = new TextField();
        vbox2.getChildren().add(txfKundeTlf);

        Label lblAdresse = new Label("Adresse");
        vbox2.getChildren().add(lblAdresse);
        txfKundeAdresse = new TextField();
        vbox2.getChildren().add(txfKundeAdresse);


        //Bekræft køb med pris inkl. pant.
        HBox hBox = new HBox(10);
        this.add(hBox,4,2);
        Label lblSumInklPant = new Label("Sum inkl. Pant");
        hBox.getChildren().add(lblSumInklPant);
        txfSumInklPant = new TextField();
        hBox.getChildren().add(txfSumInklPant);

        btnBekræftUdlej = new Button("Bekræft Udlejning");
        hBox.getChildren().add(btnBekræftUdlej);

        //Knap til at åbne et nyt vindue med indeholdene aktive udlejninger.
        Button btnAfslutUdl = new Button("Find og Afslut Udlejning");
        btnAfslutUdl.setOnAction(event -> this.afslutUdlejAction());
        this.add(btnAfslutUdl,0,4);




        //Administer/afslut aktive udlejninger
/*
        VBox vBox3 = new VBox(20);
        this.add(vBox3,0,4);
        Label lblAktivUdl = new Label("Aktive Udlejninger");
        vBox3.getChildren().add(lblAktivUdl);
        lvwAktiveUdlejninger = new ListView<>();
        lvwAktiveUdlejninger.setPrefHeight(200);
        vBox3.getChildren().add(lvwAktiveUdlejninger);
*/

    }

    private void selectedProGrupChanged() {
        Produktgruppe pg = cbProGroup.getSelectionModel().getSelectedItem();
        lvwProd.getItems().setAll(pg.getProdukter());
    }


    private void afslutUdlejAction(){
        UdlejWindow dia = new UdlejWindow("Afslut Udlejning");
        dia.showAndWait();

        /*String navn = dia.getNavn();*/




    }




































}
