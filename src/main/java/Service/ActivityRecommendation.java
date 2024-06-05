package Service;

import Model.Cluster;
import Model.WeatherData;
import Repository.WeatherFetcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityRecommendation {

    private WeatherFetcher weatherFetcher;

    public ActivityRecommendation(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public void performClustering(String location) {
        WeatherTrendAnalyzer weatherTrendAnalyzer = new WeatherTrendAnalyzer(weatherFetcher);
        int k = 3; // Assuming 3 clusters for simplicity
        List<WeatherData> data = weatherTrendAnalyzer.fetchHistoricalWeatherData(location);
        List<Cluster> clusters = KMeansClustering.performClustering(data, k);

        analyzeAndDisplayClusterInsights(clusters);
    }

    public void analyzeAndDisplayClusterInsights(List<Cluster> clusters) {
        System.out.println("\nWeather Insights for Next 3 Days:");

        // Assume each cluster roughly corresponds to a day of the week if we are showing a 7-day forecast
        int dayNumber = 1;
        for (Cluster cluster : clusters) {
            // Identify the most common condition in the cluster
            String mostCommonCondition = getMostCommonCondition(cluster);

            System.out.println("Day " + dayNumber++ + ": Predominant Weather Condition: " + mostCommonCondition);
            System.out.println("  Recommended Activity: " + getActivityRecommendation(mostCommonCondition));
        }
    }

    public String getMostCommonCondition(Cluster cluster) {
        Map<String, Integer> conditionsCount = new HashMap<>();
        for (WeatherData data : cluster.getPoints()) {
            conditionsCount.put(data.getCondition(), conditionsCount.getOrDefault(data.getCondition(), 0) + 1);
        }
        return conditionsCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Variable");
    }

    public String getActivityRecommendation(String condition) {
        switch (condition) {
            case "Sunny":
                return "a great day for outdoor sports like tennis or cycling";
            case "Rainy":
                return "a good day to visit a museum or watch movies indoors";
            case "Cloudy":
                return "ideal for outdoor photography or a relaxed walk in the park";
            case "Snowy":
                return "perfect for skiing or enjoying a hot drink by the fireplace";
            default:
                return "check daily forecasts closer to the date for specific activities";
        }
    }
}
