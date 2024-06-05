package Data;
import java.util.*;

import Repository.WeatherAPI;
import Model.WeatherAlert;
import Model.WeatherData;

public class MockWeatherAPI implements WeatherAPI {

    Random random = new Random();
    String[] condition = {"Sunny", "Rainy", "Cloudy", "Humid", "Light Rain", "Foggy"};

    @Override
    public WeatherData getCurrentWeather(String location) {
        return new WeatherData(random.nextFloat() * (50), random.nextInt(100), condition[random.nextInt(condition.length)]);
    }

    @Override
    public List<WeatherAlert> generateWeatherAlerts(String location) {
        return List.of();
    }

}