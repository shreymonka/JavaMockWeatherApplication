package Service;
import Model.WeatherData;
import Repository.WeatherFetcher;

import java.util.List;

public class WeatherForecast {

    private WeatherFetcher weatherFetcher;

    public WeatherForecast(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public void handleNextDayForecast(String location) {
        float forecastTemperature = forecastNextDayTemperature(location);
        System.out.println("\nForecast temperature for the next day in " + location + ": " + forecastTemperature + "°");
    }

    float forecastNextDayTemperature(String location) {

        WeatherTrendAnalyzer weatherTrendAnalyzer = new WeatherTrendAnalyzer(weatherFetcher);

        if (location == null || location.isEmpty()){
            System.out.println("Location should not be null or empty.");
            return 0.0f;
        }
        List<WeatherData> historicalData = weatherTrendAnalyzer.fetchHistoricalWeatherData(location);
        if (historicalData == null || historicalData.isEmpty()) {
            System.out.println("No historical data available.");
            return 0.0f;
        }

        float dailyChangeSum = 0;
        WeatherData previousDay = null;
        for (WeatherData currentDay : historicalData) {
            if (previousDay != null && currentDay != null) {
                dailyChangeSum += (currentDay.getTemperature() - previousDay.getTemperature());
            }
            previousDay = currentDay;
        }
        if (historicalData.size() < 2) { // Need at least two days to calculate a change
            return historicalData.getFirst().getTemperature(); // or handle this case differently
        }
        float averageDailyChange = dailyChangeSum / (historicalData.size() - 1);
        float lastDayTemperature = historicalData.getLast().getTemperature();
        return lastDayTemperature + averageDailyChange;
    }
}
