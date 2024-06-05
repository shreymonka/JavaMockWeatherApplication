package Service;
import Model.WeatherData;
import Repository.WeatherFetcher;

public class CompareWeather {
    private WeatherFetcher weatherFetcher;

    public CompareWeather(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public boolean compareWeather(String location1, String location2) {

        if (location1 == null || location1.isEmpty()){
            return false;
        }

        if (location2 == null || location2.isEmpty()){
            return false;
        }

        WeatherData weather1 = weatherFetcher.fetchCurrentWeather(location1);
        WeatherData weather2 = weatherFetcher.fetchCurrentWeather(location2);

        // Construct vectors
        double[] vector1 = {weather1.getTemperature(), weather1.getHumidity()};
        double[] vector2 = {weather2.getTemperature(), weather2.getHumidity()};

        // Compute cosine similarity
        double similarity = cosineSimilarity(vector1, vector2);

        // Display comparison
        displayWeatherComparison(location1, weather1, location2, weather2, similarity);
        return true;
    }

    public double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private void displayWeatherComparison(String location1, WeatherData weather1, String location2, WeatherData weather2, double similarity) {
        System.out.println();
        System.out.println("Comparing weather between " + location1 + " and " + location2 + ":");
        System.out.println(location1 + " - Temperature: " + weather1.getTemperature() + "°, Humidity: " + weather1.getHumidity() + "%, Condition: " + weather1.getCondition());
        System.out.println(location2 + " - Temperature: " + weather2.getTemperature() + "°, Humidity: " + weather2.getHumidity() + "%, Condition: " + weather2.getCondition());
//        System.out.println("Cosine Similarity Score: " + similarity);

        // Interpret and display the similarity score in human-readable form
        String similarityDescription = interpretSimilarity(similarity);
        System.out.println("Weather Similarity: " + similarityDescription);
    }

    public String interpretSimilarity(double similarity) {
        if (similarity >= 0.9) {
            return "Very Similar";
        } else if (similarity >= 0.7) {
            return "Similar";
        } else if (similarity >= 0.5) {
            return "Moderately Similar";
        } else if (similarity >= 0.3) {
            return "Slightly Similar";
        } else {
            return "Not Similar";
        }
    }
}