package Repository;
import Model.WeatherAlert;
import Model.WeatherData;

import java.util.List;

public interface WeatherAPI {
    WeatherData getCurrentWeather(String location);
    List<WeatherAlert> generateWeatherAlerts(String location);
}