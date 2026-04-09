package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class KvikmyndalistiController {

    @FXML
    private void onTilBaka(ActionEvent event) {
        ViewSwitcher.switchTo(View.ADAL, true);
    }
}
