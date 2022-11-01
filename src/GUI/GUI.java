package GUI;

import Applikation.Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    public void init(){Controller.init();}

    @Override
    public void start(Stage stage) {
        stage.setTitle("Aarhus Bryghus");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(BorderPane pane){
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane){
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabSalg = new Tab("Salg");
        tabPane.getTabs().add(tabSalg);
        SalgPane salgPane = new SalgPane();
        tabSalg.setContent(salgPane);

        Tab tabAdmin = new Tab("OpretPro");
        tabPane.getTabs().add(tabAdmin);
        AdminPane adminPane = new AdminPane();
        tabAdmin.setContent(adminPane);
//        tabAdmin.setOnSelectionChanged(event -> adminPane.updateControls());

        Tab tabRegSalgsType = new Tab("Opret Salgstype");
        tabPane.getTabs().add(tabRegSalgsType);
        RegSalgsTypePane regSalgsTypePane = new RegSalgsTypePane();
        tabRegSalgsType.setContent(regSalgsTypePane);

        Tab tabStat = new Tab("Statistik");
        tabPane.getTabs().add(tabStat);
        StatPane statPane = new StatPane();
        tabStat.setContent(statPane);



    }

}
