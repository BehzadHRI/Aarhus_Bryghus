package GUI;

import Applikation.Model.Produktgruppe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProduktgruppeWindow extends Stage {
    private String navn;
    private boolean kanUdlejes;


    public ProduktgruppeWindow(String title){
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    private TextField txfNavn;
    private CheckBox chkKanUdlejes;
    private Button btnAnnuller, btnBekræft;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0,0);

        txfNavn = new TextField();
        pane.add(txfNavn,1,0);

        Label lblKanUdlejes = new Label("Kan udlejes");
        pane.add(lblKanUdlejes, 0,1);
        chkKanUdlejes = new CheckBox();
        pane.add(chkKanUdlejes, 1,1);

        btnBekræft = new Button("Bekræft");
        pane.add(btnBekræft, 2,0);
        btnBekræft.setOnAction(event-> this.bekræftCase());

        btnAnnuller = new Button("Annuller");
        pane.add(btnAnnuller,2,1);
        btnAnnuller.setOnAction(event -> this.annullerCase());



        lblError = new Label();
        pane.add(lblError,2,2);
    }

    private void bekræftCase() {
        String navnInput = txfNavn.getText();
        if (navnInput.length()==0){
            lblError.setTextFill(Color.RED);
            lblError.setText("Ugyldigt navn");
        }else {
            navn = navnInput;
            kanUdlejes = chkKanUdlejes.isSelected();
            txfNavn.setText("");
            chkKanUdlejes.setSelected(false);
            hide();
        }
    }

    private void annullerCase() {
        chkKanUdlejes.setSelected(false);
        txfNavn.setText("");
        hide();
    }

    public String getNavn() {
        return navn;
    }

    public boolean isKanUdlejes() {
        return kanUdlejes;
    }
}
