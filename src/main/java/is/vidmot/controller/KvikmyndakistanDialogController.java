package is.vidmot.controller;

import is.vidmot.view.KvikmyndakistanSpjald;
import is.vinnsla.Mynd;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller klasi fyrir DialogPane fyrir að bæta við ferð
 *
 *
 *****************************************************************************/
public class KvikmyndakistanDialogController implements GognInterface<Mynd>{
    @FXML
    private KvikmyndakistanSpjald fxKvikmyndakistanSpjald; // viðmótshlutur, custsom component

    private final SimpleObjectProperty<Mynd> ferd = new SimpleObjectProperty<>(); // módelið

    /**
     * Útfærir aðferðina úr GognInterface. Setur ferðina og bindur viðmótshluti við módel hlutinn ferd.
     * @param f ferðin
     */
    @Override
    public void setGogn(Mynd f) {
        this.ferd.set(f);
        fxKvikmyndakistanSpjald.heitiProperty().bindBidirectional (ferd.get().heitiProperty());
        fxKvikmyndakistanSpjald.afangastadurProperty().bindBidirectional (ferd.get().afangastadurProperty());
        fxKvikmyndakistanSpjald.dagsetningProperty().bindBidirectional((ferd.get().dagsetningProperty()));
    }


    public SimpleObjectProperty<Mynd> getFerd() {
        return ferd;
    }

}
