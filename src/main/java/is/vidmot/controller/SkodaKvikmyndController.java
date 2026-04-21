package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkodaKvikmyndController implements GognInterface<Movie>{


        @FXML
        private Label fxHeiti;
        @FXML private Label fxEinkunn;
        @FXML private Label fxUtgafudagur;
        @FXML private TextArea fxLysing;
        @FXML private ImageView fxPoster;

        @Override
        public void setGogn(Movie m) {
            if(m == null) return;
            fxHeiti.setText(m.getTitle());
            fxEinkunn.setText(String.valueOf(m.getVote_average()));
            fxUtgafudagur.setText(m.getRelease_date());
            fxLysing.setText(m.getOverview());
            fxPoster.setImage(new Image("https://image.tmdb.org/t/p/w500" + m.getPoster_path(), true));
        }

        @FXML
        private void onTilBaka(ActionEvent event) {
            ViewSwitcher.switchTo(View.KVIKMYNDA_LISTI, true);
        }
    }

