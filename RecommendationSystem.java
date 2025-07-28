import java.util.*;

public class RecommendationSystem {

    static class Movie {
        String title;
        Set<String> genres;

        Movie(String title, String... genres) {
            this.title = title;
            this.genres = new HashSet<>(Arrays.asList(genres));
        }

        // Converts genres into a vector for similarity comparison
        double[] toVector(List<String> allGenres) {
            double[] vector = new double[allGenres.size()];
            for (int i = 0; i < allGenres.size(); i++) {
                vector[i] = genres.contains(allGenres.get(i)) ? 1.0 : 0.0;
            }
            return vector;
        }
    }

    // Cosine similarity between two vectors
    static double cosineSimilarity(double[] vec1, double[] vec2) {
        double dot = 0.0, mag1 = 0.0, mag2 = 0.0;
        for (int i = 0; i < vec1.length; i++) {
            dot += vec1[i] * vec2[i];
            mag1 += vec1[i] * vec1[i];
            mag2 += vec2[i] * vec2[i];
        }
        return (mag1 == 0 || mag2 == 0) ? 0.0 : dot / (Math.sqrt(mag1) * Math.sqrt(mag2));
    }

    // Recommend similar movies
    static List<Movie> recommend(Movie input, List<Movie> allMovies, int topN) {
        // Gather all unique genres
        Set<String> genreSet = new HashSet<>();
        for (Movie movie : allMovies) {
            genreSet.addAll(movie.genres);
        }
        List<String> allGenres = new ArrayList<>(genreSet);

        double[] inputVector = input.toVector(allGenres);

        // Calculate similarity for each movie
        Map<Movie, Double> similarityMap = new HashMap<>();
        for (Movie movie : allMovies) {
            if (!movie.title.equalsIgnoreCase(input.title)) {
                double sim = cosineSimilarity(inputVector, movie.toVector(allGenres));
                similarityMap.put(movie, sim);
            }
        }

        // Sort by similarity
        return similarityMap.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(topN)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        // Sample movie data
        List<Movie> movies = List.of(
                new Movie("Inception", "Action", "Sci-Fi", "Thriller"),
                new Movie("Interstellar", "Sci-Fi", "Drama", "Adventure"),
                new Movie("The Matrix", "Action", "Sci-Fi"),
                new Movie("The Notebook", "Romance", "Drama"),
                new Movie("John Wick", "Action", "Thriller"),
                new Movie("Arrival", "Sci-Fi", "Drama"),
                new Movie("Titanic", "Romance", "Drama")
        );

        // Choose a movie to get recommendations
        Movie selected = new Movie("Inception", "Action", "Sci-Fi", "Thriller");

        System.out.println("Recommendations for: " + selected.title);
        List<Movie> recommendations = recommend(selected, movies, 3);

        for (Movie m : recommendations) {
            System.out.println("- " + m.title);
        }
    }
}
