package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.Movie;
import is.vinnsla.TMDBservice;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class KvikmyndalistiController implements GognInterface<String> {


    @FXML
    private ListView<Movie> fxListiFerdir1;


    @FXML
    private void onTilBaka(ActionEvent event) {
        ViewSwitcher.switchTo(View.ADAL, true);
    }
    // TEMPORARY ONSKODA, MÁ EYÐA EÐA BREYTA SEINNA
    @FXML
    private void onSkoda(ActionEvent event) {
        Movie f = fxListiFerdir1.getSelectionModel().getSelectedItem();
        if (f != null) {
            ViewSwitcher.switchTo(View.FERD, false, f);
        }
    }

    /**
     * setGogn sem setur TopRated myndirnar í listann þegar
     * þú smellir á "Top Rated"
     *
     * ATH: Þarf að breyta með PrintStackTrace, mögulega
     * bæta við villu fyrir notanda til að sjá.
     * t.d "Ekki hægt að sækja "Top Rated" mynd.
     * @param category
     */
    @Override
    public void setGogn(String category) {
        TMDBservice service = new TMDBservice();
        Thread t = new Thread(() -> {
            try {
                List<Movie> movies = switch (category) {
                    case "Top Rated" -> service.getTopRated();
                    case "Popular" -> service.getUpcoming();
                    case "Upcoming" -> service.getPopularMovies();
                    default -> service.getTopRated();
                };
                Platform.runLater(() -> fxListiFerdir1.getItems().addAll(movies));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
        }
    }

