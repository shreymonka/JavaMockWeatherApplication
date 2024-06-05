package Repository;
import Data.MockWeatherAPI;
import Model.WeatherAlert;
import Model.WeatherData;

import java.util.List;

public class WeatherService implements WeatherAPI {
    private WeatherAPI weatherAPI;

    public WeatherService() {
        this.weatherAPI = new MockWeatherAPI();
    }

    @Override
    public WeatherData getCurrentWeather(String location) {
        return weatherAPI.getCurrentWeather(location);
    }

    @Override
    public List<WeatherAlert> generateWeatherAlerts(String location) {
        return weatherAPI.generateWeatherAlerts(location);
    }
}
