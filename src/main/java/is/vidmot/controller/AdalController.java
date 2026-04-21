package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.Atburdur;
import is.vinnsla.Mynd;
import is.vinnsla.Kvikmyndakistan;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


/******************************************************************************
 *  Nafn    : Ebba Þóra Hvananberg
 *  T-póstur:
 *  Lýsing  : Controller eða stýring fyrir notendaviðmótið
 *  Controller fyrir upphafsviðmót í Ferðaplani, verkefni 3, 2026
 *
 *
 *****************************************************************************/
public class AdalController {

    private static final String FERD_BAETT_VID = "Mynd %s bætt við ";
    private static final String FERD_EYTT = "Mynd %s eytt";
    private static final String FERD_SKODUD = "Mynd %s skoðuð";
    private static final String ENGIN_FERD_VALIN = "Veldu mynd til að skoða eða eyða";
    private static final String FERD_VALIN = " %s valin";
    public static final String SKRA_FANNST_EKKI = "Mynd finnst ekki ";
    public static final String FERDIR_TXT = "/mynd.txt";

    @FXML
    private ListView<Mynd> fxListiFerdir;

    @FXML
    private Button fxEyda;

    @FXML
    private Button fxSkoda;

    @FXML
    private Label fxSkilabod;

    @FXML
    private ListView<String> fxListiFerdir1;

    // vinnslan
    private Kvikmyndakistan kvikmyndakistan = new Kvikmyndakistan();

    /**
     * Frumstillingaraðferð sem er keyrð eftir að búið er að hlaða inn .fxml skránni en áður
     * en viðmótstréð er birt
     */
    public void initialize() {
        try {
            kvikmyndakistan.lesaLista(FERDIR_TXT);

        } catch (FileNotFoundException e) {
            fxSkilabod.setText(SKRA_FANNST_EKKI);
        }
        fxListiFerdir.setItems(kvikmyndakistan.getFerdaListi());
        tengjaSkodaEydaHnappa();
        tengjaAtburdVidSkilabod();
        tengjaValinFerd();

        //Skrifar í listView til þess að geta smellt á.
        fxListiFerdir1.getItems().addAll("Top Rated", "Popular", "Upcoming");


        //"Clearar" valdi&eth;, svo h&aelig;gt er a&eth; velja mismunandi lista.
        fxListiFerdir.getSelectionModel().selectedItemProperty().addListener((obs, gamla, nyja) -> {
            if (nyja != null) fxListiFerdir1.getSelectionModel().clearSelection();
        });

        fxListiFerdir1.getSelectionModel().selectedItemProperty().addListener((obs, gamla, nyja) -> {
            if (nyja != null) fxListiFerdir.getSelectionModel().clearSelection();
        });
    }

    /**
     * Hjálaraðferð sem hlustar á valda ferð og segir módelinu að ný ferð sé valin
     */
    private void tengjaValinFerd() {
        fxListiFerdir.getSelectionModel().selectedItemProperty().addListener((obs, gamla, nyja)
                -> kvikmyndakistan.veljaFerd(nyja));
    }

    /**
     * Hjálparaðferð sem gerir fxEyda og fxSkoda óvirka ef ekkert er valið
     */
    private void tengjaSkodaEydaHnappa() {
        fxEyda.disableProperty().bind(
                fxListiFerdir.getSelectionModel().selectedItemProperty().isNull());
        fxSkoda.disableProperty().bind(
                fxListiFerdir.getSelectionModel().selectedItemProperty().isNull().
                        and(fxListiFerdir1.getSelectionModel().selectedItemProperty().isNull()));
    }

    /**
     * Hjálparaðferð sem bindindur stöðu módelsins við textaskilaboð
     */
    private void tengjaAtburdVidSkilabod() {
        fxSkilabod.textProperty().bind(Bindings.createStringBinding(() -> // callable lambda fall
                {
                    if (kvikmyndakistan.valinFerdProperty().get() == null) {
                        return String.format(ENGIN_FERD_VALIN);
                    }
                    return switch (kvikmyndakistan.sidastiAtburdurProperty().get()) {
                        case Atburdur.NY_FERD -> String.format(FERD_BAETT_VID, kvikmyndakistan.valinFerdProperty().get());
                        case Atburdur.EYDA -> String.format(FERD_EYTT, kvikmyndakistan.valinFerdProperty().get());
                        case Atburdur.SKODA -> String.format(FERD_SKODUD, kvikmyndakistan.valinFerdProperty().get());
                        case Atburdur.VELJA -> String.format(FERD_VALIN, kvikmyndakistan.valinFerdProperty().get());
                    };
                }
                , kvikmyndakistan.sidastiAtburdurProperty()
                , kvikmyndakistan.valinFerdProperty())); // vaktaðar breytur
    }

    /**
     * Birtir dialog sem notandi getur bætt við ferð í og bætir henni við í módelið
     *
     * @param event
     */
    @FXML
    private void onBaetaVid(ActionEvent event) {
        Optional<Mynd> result = KvikmyndakistanDialogWrapper.birtaDialog(fxSkilabod.getScene().getWindow());
        if (!result.isEmpty()) {
            kvikmyndakistan.nyFerd(result.get());
        }
    }

    /**
     * Birtir staðfestingadialog og eyðir ferðinni í módelinu ef notandi staðfestir
     *
     * @param event
     * @throws IOException ef ekki var hægt að lesa .fxml skrána sem geymir staðfestingardialog
     */
    @FXML
    private void onEyda(ActionEvent event) throws IOException {
        Mynd f = fxListiFerdir.getSelectionModel().getSelectedItem();
        if (f != null) {
            StadfestingEydaDialogController s = new StadfestingEydaDialogController();
            if (s.birta(f)) {
                kvikmyndakistan.eydaFerd(f);
            }
        }
        }

        /**
         * Birtir ferðina í sama glugga
         *
         * Ari: Bætti við þannig að  þegar er ýtt á listann fyrir topp kvikmyndir
         * þá er eyða takkin ekki virkur
         * @param event
         */
        @FXML
        private void onSkoda(ActionEvent event) {
           if(fxListiFerdir1.getSelectionModel().getSelectedItem() != null) {
               String val = fxListiFerdir1.getSelectionModel().getSelectedItem();
               ViewSwitcher.switchTo(View.KVIKMYNDA_LISTI, true, val);

           } else if(fxListiFerdir.getSelectionModel().getSelectedItem() != null) {
               Mynd f = fxListiFerdir.getSelectionModel().getSelectedItem();
               kvikmyndakistan.skodaFerd(f);
               ViewSwitcher.switchTo(View.FERD, true, f);
           }

        }
    }


