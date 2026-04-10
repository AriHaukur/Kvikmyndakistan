package is.vinnsla;

public class Movie {
    private int id;
    private String title;
    private String overview;
    private String original_title;
    private String release_date;
    private float vote_average;
    private String poster_path;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    /**
     * toString method til að setja fram titil, einkunn og útgáfudag
     * Annars kemur upp bara location af kvikmyndinni s.s @1a23ff1
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s  (%.1f)  %s", title, vote_average, release_date);
    }


}
