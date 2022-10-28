package GUI;

import Applikation.Model.Salgstype;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class SalgPane extends GridPane {

    public SalgPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


    }
}
