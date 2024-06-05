package Service;

import Model.WeatherAlert;
import Model.WeatherData;
import Repository.WeatherFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SevereWeatherAlertTest {

    private SevereWeatherAlert severeWeatherAlert;
    private WeatherFetcher weatherFetcher;
    private WeatherData weatherData;
    private List<WeatherData> historicalData;

    @BeforeEach
    void setUp() {
        weatherFetcher = mock(WeatherFetcher.class);
        severeWeatherAlert = new SevereWeatherAlert(weatherFetcher);
        weatherData = mock(WeatherData.class);
        historicalData = new ArrayList<>();
        historicalData.add(weatherData);
    }

    @Test
    void testGenerateWeatherAlerts_NoAlerts() {
        // Set up historical data to trigger no alerts
        when(weatherFetcher.fetchCurrentWeather(anyString())).thenReturn(weatherData);
        severeWeatherAlert = spy(new SevereWeatherAlert(weatherFetcher));
        doReturn(false).when(severeWeatherAlert).isHeatAlert(historicalData);
        doReturn(false).when(severeWeatherAlert).isColdAlert(historicalData);
        doReturn(false).when(severeWeatherAlert).isHumidityAlert(historicalData);
        doReturn(false).when(severeWeatherAlert).isRapidTemperatureChangeAlert(historicalData);

        List<WeatherAlert> alerts = severeWeatherAlert.generateWeatherAlerts("Bhavnagar");

        assertTrue(alerts.isEmpty());
    }

}
