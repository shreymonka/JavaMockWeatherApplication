package Service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Model.WeatherData;
import Repository.WeatherFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class CompareWeatherTest {
    @Mock
    private WeatherFetcher weatherFetcher;

    @InjectMocks
    private CompareWeather compareWeather;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompareWeatherWithValidInputs() {
        // Setup
        WeatherData weather1 = new WeatherData(25, 60, "Sunny");
        WeatherData weather2 = new WeatherData(20, 55, "Cloudy");
        when(weatherFetcher.fetchCurrentWeather("Location1")).thenReturn(weather1);
        when(weatherFetcher.fetchCurrentWeather("Location2")).thenReturn(weather2);

        // Execute
        compareWeather.compareWeather("Location1", "Location2");

        // Verify
        verify(weatherFetcher).fetchCurrentWeather("Location1");
        verify(weatherFetcher).fetchCurrentWeather("Location2");
    }

    @Test
    void testCompareWeatherWithNullInputs() {
        boolean expectedFalse = compareWeather.compareWeather(null, null);
        assertFalse(expectedFalse);

        boolean expectedFalse2 = compareWeather.compareWeather("India", null);
        assertFalse(expectedFalse2);

        boolean expectedFalse3 = compareWeather.compareWeather(null, "India");
        assertFalse(expectedFalse3);
    }

    @Test
    void testCompareWeatherWithEmptyString() {
        boolean expectedFalse = compareWeather.compareWeather("", "");
        assertFalse(expectedFalse);

        boolean expectedFalse2 = compareWeather.compareWeather("Hali", "");
        assertFalse(expectedFalse2);

        boolean expectedFalse3 = compareWeather.compareWeather("", "Baroda");
        assertFalse(expectedFalse3);
    }

    @Test
    void testCosineSimilarity() {
        // Data
        double[] vectorA = {1, 2, 3};
        double[] vectorB = {1, 2, 3};

        // Execute
        double result = compareWeather.cosineSimilarity(vectorA, vectorB);

        // Verify
        assertEquals(1.0, result, 0.001);
    }

    @Test
    void testInterpretSimilarity() {
        assertEquals("Very Similar", compareWeather.interpretSimilarity(0.95));
        assertEquals("Similar", compareWeather.interpretSimilarity(0.75));
        assertEquals("Moderately Similar", compareWeather.interpretSimilarity(0.55));
        assertEquals("Slightly Similar", compareWeather.interpretSimilarity(0.35));
        assertEquals("Not Similar", compareWeather.interpretSimilarity(0.25));
    }
}
