import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApiClientExample {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        try {
            // 1. GET Request
            String getUrl = "https://jsonplaceholder.typicode.com/posts/1";
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getUrl))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("GET Response:\n" + getResponse.body());

            // 2. POST Request
            String postUrl = "https://jsonplaceholder.typicode.com/posts";
            String postJson = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create(postUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(postJson))
                    .build();

            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("\nPOST Response:\n" + postResponse.body());

            // 3. PUT Request
            String putUrl = "https://jsonplaceholder.typicode.com/posts/1";
            String putJson = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(URI.create(putUrl))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(putJson))
                    .build();

            HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("\nPUT Response:\n" + putResponse.body());

            // 4. DELETE Request
            String deleteUrl = "https://jsonplaceholder.typicode.com/posts/1";

            HttpRequest deleteRequest = HttpRequest.newBuilder()
                    .uri(URI.create(deleteUrl))
                    .DELETE()
                    .build();

            HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("\nDELETE Response:\n" + deleteResponse.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
