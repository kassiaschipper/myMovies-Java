import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        String apiMovies = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(apiMovies);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();
        String star = "⭐";

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {
            String title = filme.get("title");

            System.out.println("\u001b[31m" + title);
            
            System.out.println(filme.get("image"));

            String rating = filme.get("imDbRating");
            Double strRating = Double.parseDouble(rating);
          
            var intRating = Math.round(strRating);

            System.out.println(rating);
            String stars = "";
            for (int i = 0; i < intRating; i++) {
                stars += star;
            }
            System.out.println(stars);
            System.out.println();
        }
    }
}