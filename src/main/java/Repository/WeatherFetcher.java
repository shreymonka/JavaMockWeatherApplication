package Repository;
import Model.UserPreferance;
import Model.WeatherData;

public class WeatherFetcher {
    private WeatherService weatherService;
    private UserPreferance userPreferences;

    public WeatherFetcher(WeatherService weatherService) {
        this.weatherService = weatherService;
        this.userPreferences = new UserPreferance("Celsius"); // Default preferences
    }

    public void setUserPreferences(UserPreferance preferences) {
        this.userPreferences = preferences;
    }

    public UserPreferance getUserPreferences() {
        return userPreferences;
    }

    public WeatherData fetchCurrentWeather(String location) {
        return weatherService.getCurrentWeather(location);
    }
}
