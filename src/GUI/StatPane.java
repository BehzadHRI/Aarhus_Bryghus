package GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class StatPane extends GridPane {

    public StatPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


    }
}
