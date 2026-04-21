package is.vidmot.switcher;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 *
 * viðbætur fyrir Ferdaplan verkefni
 */
public enum View {
    ADAL("/is/vidmot/adal-view.fxml"),
    FERD("/is/vidmot/kvikmyndakistan-view.fxml"),
    FERDDIALOG("/is/vidmot/nyKvikmynd-dialog.fxml"),
    FERDSPJALD ("/is/vidmot/kvikmyndakistanSpjald-view.fxml"),
    KVIKMYNDA_LISTI ("/is/vidmot/listikvikmyndaView.fxml"),
    SKODA_KVIKMYND ("/is/vidmot/skodaKvikmynd.fxml");


    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
