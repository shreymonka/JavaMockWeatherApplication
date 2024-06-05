package Service;

import Model.WeatherAlert;
import Model.WeatherData;
import Repository.WeatherFetcher;

import java.util.ArrayList;
import java.util.List;

public class SevereWeatherAlert {

    private WeatherFetcher weatherFetcher;

    public SevereWeatherAlert(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public void weatherAlerts(String location) {
        List<WeatherAlert> alerts = generateWeatherAlerts(location);
        displayWeatherAlerts(location, alerts);

    }

    private void displayWeatherAlerts(String location, List<WeatherAlert> alerts) {
        System.out.println("\nWeather alerts for " + location + ":");
        for (WeatherAlert alert : alerts) {
            System.out.println(alert.getAlertType() + ": " + alert.getDescription());
            System.out.println("Safety Tips: " + alert.getSafetyTips());
        }
    }

    List<WeatherAlert> generateWeatherAlerts(String alertLocation) {

        WeatherTrendAnalyzer weatherTrendAnalyzer = new WeatherTrendAnalyzer(weatherFetcher);
        List<WeatherData> historicalData = weatherTrendAnalyzer.fetchHistoricalWeatherData(alertLocation);
        List<WeatherAlert> alerts = new ArrayList<>();

        if (isHeatAlert(historicalData)) {
            alerts.add(new WeatherAlert("Heat Alert", "Average temperature exceeds 35°C", "Stay hydrated and avoid outdoor activities."));
        }
        if (isColdAlert(historicalData)) {
            alerts.add(new WeatherAlert("Cold Alert", "Average temperature drops below 0°C", "Stay warm and avoid exposure to cold."));
        }
        if (isHumidityAlert(historicalData)) {
            alerts.add(new WeatherAlert("High Humidity Alert", "Average humidity exceeds 80%", "Stay in cool, dry places and drink plenty of water."));
        }
        if (isRapidTemperatureChangeAlert(historicalData)) {
            alerts.add(new WeatherAlert("Rapid Temperature Change Alert", "Temperature changes more than 10°C within 24 hours", "Be prepared for sudden weather changes."));
        }

        return alerts;
    }

    boolean isHeatAlert(List<WeatherData> historicalData) {
        double averageTemp = historicalData.stream().mapToDouble(WeatherData::getTemperature).average().orElse(0);
        return averageTemp > 35;
    }

    boolean isColdAlert(List<WeatherData> historicalData) {
        double averageTemp = historicalData.stream().mapToDouble(WeatherData::getTemperature).average().orElse(0);
        return averageTemp < 0;
    }

    boolean isHumidityAlert(List<WeatherData> historicalData) {
        double averageHumidity = historicalData.stream().mapToInt(WeatherData::getHumidity).average().orElse(0);
        return averageHumidity > 80;
    }

    boolean isRapidTemperatureChangeAlert(List<WeatherData> historicalData) {
        for (int i = 1; i < historicalData.size(); i++) {
            float tempChange = Math.abs(historicalData.get(i).getTemperature() - historicalData.get(i - 1).getTemperature());
            if (tempChange > 10) {
                return true;
            }
        }
        return false;
    }
}