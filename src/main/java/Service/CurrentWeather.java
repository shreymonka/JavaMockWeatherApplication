package Service;

import Model.WeatherData;
import Repository.WeatherFetcher;


public class CurrentWeather {

    private WeatherFetcher weatherFetcher;

    public CurrentWeather(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public boolean displayCurrentWeather(String location) {

        if (location == null || location.isEmpty()){
            return false;
        }

        WeatherData weatherData = weatherFetcher.fetchCurrentWeather(location);
        if (weatherData != null) {
            System.out.println("\nCurrent weather in " + location + ":");
            System.out.println("Temperature: " + weatherData.getTemperature() + "°" + weatherFetcher.getUserPreferences().getTemperatureUnit());
            System.out.println("Humidity: " + weatherData.getHumidity() + "%");
            System.out.println("Condition: " + weatherData.getCondition());
            System.out.println();
            System.out.print("Forecast Based On Current Weather : ");
            weatherForecastBasedOnCurrentTemperature(location);
            System.out.println();
            weatherAlertBasedOnCurrentTemperature(location);
            return true;
        } else {
            System.out.println("No weather data available for " + location);
            return true;
        }
    }

    private void weatherForecastBasedOnCurrentTemperature(String location) {
        WeatherForecast forecast = new WeatherForecast(weatherFetcher);
        forecast.handleNextDayForecast(location);
    }

    private void weatherAlertBasedOnCurrentTemperature(String location){
        SevereWeatherAlert alert = new SevereWeatherAlert(weatherFetcher);
        alert.weatherAlerts(location);

    }
}