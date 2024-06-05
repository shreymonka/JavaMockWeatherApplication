package Service;

import Repository.WeatherFetcher;
import Model.WeatherStatistics;
import Model.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class WeatherTrendAnalyzer {
    private WeatherFetcher weatherFetcher;

    public WeatherTrendAnalyzer(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public void handleWeatherTrendAnalysis(String location) {
        WeatherStatistics stats = analyzeWeatherTrends(location);
        displayWeatherStatistics(location, stats);
    }

    public WeatherStatistics analyzeWeatherTrends(String location) {

        if (location == null || location.isEmpty()) {
            return null;
        }

        List<WeatherData> historicalData = fetchHistoricalWeatherData(location);
        return calculateStatistics(historicalData);
    }

    public List<WeatherData> fetchHistoricalWeatherData(String location) {
        // Mock implementation: In a real scenario, this would fetch data from an API or database
        // Here we'll just return mock data for the last 7 days
        List<WeatherData> historicalData = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            historicalData.add(weatherFetcher.fetchCurrentWeather(location)); // Mock data for simplicity
        }
        return historicalData;
    }

    private WeatherStatistics calculateStatistics(List<WeatherData> historicalData) {
        float totalTemperature = 0;
        float highestTemperature = Float.MIN_VALUE;
        float lowestTemperature = Float.MAX_VALUE;
        int totalHumidity = 0;
        int highestHumidity = Integer.MIN_VALUE;
        int lowestHumidity = Integer.MAX_VALUE;
        int count = historicalData.size();

        for (WeatherData data : historicalData) {
            float temp = data.getTemperature();
            totalTemperature += temp;
            if (temp > highestTemperature) {
                highestTemperature = temp;
            }
            if (temp < lowestTemperature) {
                lowestTemperature = temp;
            }
            int humidity = data.getHumidity();
            totalHumidity += humidity;
            if (humidity > highestHumidity) {
                highestHumidity = humidity;
            }
            if (humidity < lowestHumidity) {
                lowestHumidity = humidity;
            }
        }

        float averageTemperature = count > 0 ? totalTemperature / count : 0;
        float averageHumidity = count > 0 ? (float) totalHumidity / count : 0;
        return new WeatherStatistics(averageTemperature, highestTemperature, lowestTemperature, averageHumidity, highestHumidity, lowestHumidity);
    }

    private void displayWeatherStatistics(String location, WeatherStatistics stats) {
        System.out.println("\nWeather statistics for " + location + " based on past week " + ":");
        System.out.println("Average Temperature: " + stats.getAverageTemperature() + "°");
        System.out.println("Highest Temperature: " + stats.getHighestTemperature() + "°");
        System.out.println("Lowest Temperature: " + stats.getLowestTemperature() + "°");
        System.out.println("Average Humidity: " + stats.getAverageHumidity() + "%");
        System.out.println("Highest Humidity: " + stats.getHighestHumidity() + "%");
        System.out.println("Lowest Humidity: " + stats.getLowestHumidity() + "%");
    }
}