package is.vinnsla;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;


public class TMDBservice {

        //Býr til nýjann client og greining fyrir API KEY
        private final HttpClient client = HttpClient.newHttpClient();
        private final String API_KEY = "997f217043f1df6721fb80944a00b53d";


        //Method til að sækja vinsælar myndir
    public List<Movie> getPopularMovies() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return parseMovies(response.body());
    }

    //method til að sækja top Rated kvikmyndir
    public List<Movie> getTopRated() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return parseMovies(response.body());
    }

    public List<Movie> getUpcoming() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return parseMovies(response.body());
    }

    //Listi Frá Movie.java sem inniheldur nafni og ratings og öllu því
    private static class MovieResponse {
        private List<Movie> results;
    }

    //Notað til að parse info frá json yfir í Gson,
    private List<Movie> parseMovies(String json) {
        Gson gson = new Gson();
        MovieResponse movieResponse = gson.fromJson(json, MovieResponse.class);
        return movieResponse.results;
    }
}


