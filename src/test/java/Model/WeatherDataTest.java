package Model;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherDataTest {

    @Test
    public void testConstructor() {
        float expectedTemperature = 25.0f;
        int expectedHumidity = 50;
        String expectedCondition = "Rainy";

        WeatherData weatherData = new WeatherData(expectedTemperature, expectedHumidity, expectedCondition);

        assertEquals(expectedTemperature, weatherData.getTemperature(), 0.0);
        assertEquals(expectedHumidity, weatherData.getHumidity());
        assertEquals(expectedCondition, weatherData.getCondition());
    }

    @Test
    public void testGetTemperature() {
        float expectedTemperature = 25.0f;
        WeatherData weatherData = new WeatherData(expectedTemperature, 60, "Rainy");
        assertEquals(expectedTemperature, weatherData.getTemperature(), 0.0);
    }

    @Test
    public void testGetHumidity() {
        int expectedHumidity = 50;
        WeatherData weatherData = new WeatherData(25.0f, expectedHumidity, "Rainy");
        assertEquals(expectedHumidity, weatherData.getHumidity());
    }

    @Test
    public void testGetCondition() {
        String expectedCondition = "Rainy";
        WeatherData weatherData = new WeatherData(25.0f, 60, expectedCondition);
        assertEquals(expectedCondition, weatherData.getCondition());
    }
}
