package Service;

import static org.junit.jupiter.api.Assertions.*;

import Model.WeatherData;
import Repository.WeatherFetcher;
import Repository.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CurrentWeatherTest {

    @Test
    public void testDisplayCurrentWeatherReturnsTrueOnValidCondition() {
        // Setup
        WeatherService service = Mockito.mock(WeatherService.class);
        WeatherFetcher weatherFetcher = new WeatherFetcher(service);
        Mockito.when(service.getCurrentWeather("Halifax")).thenReturn(new WeatherData(55, 20, "Humid"));
        CurrentWeather currentWeather = new CurrentWeather(weatherFetcher);

        // Execute
        boolean result = currentWeather.displayCurrentWeather("Halifax");

        // Verify
        assertTrue(result, "displayCurrentWeather should return true for valid weather conditions");
    }

    @Test
    public void testDisplayCurrentWeatherHandlesApiFailures() {
        // Setup
        WeatherService service = Mockito.mock(WeatherService.class);
        WeatherFetcher weatherFetcher = new WeatherFetcher(service);
        Mockito.when(service.getCurrentWeather("Halifax")).thenThrow(new RuntimeException("API failure"));
        CurrentWeather currentWeather = new CurrentWeather(weatherFetcher);

        // Execute and Verify
        Exception exception = assertThrows(RuntimeException.class, () -> currentWeather.displayCurrentWeather("Halifax"),
                "displayCurrentWeather should throw RuntimeException on API failure");

        assertEquals("API failure", exception.getMessage());
    }

}
