package Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import Repository.WeatherFetcher;
import Model.WeatherData;
import Model.WeatherStatistics;

class WeatherTrendAnalyzerTest {
    @Mock
    private WeatherFetcher weatherFetcher;

    @InjectMocks
    private WeatherTrendAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAnalyzeWeatherTrendsTest() {
        assertNull(analyzer.analyzeWeatherTrends(null));
        assertNull(analyzer.analyzeWeatherTrends(""));
    }

    @Test
    void testAnalyzeWeatherTrendsValidLocation() {
        WeatherData sampleWeather = new WeatherData(25.0f, 60, "Sunny");
        when(weatherFetcher.fetchCurrentWeather("New York")).thenReturn(sampleWeather);

        WeatherStatistics stats = analyzer.analyzeWeatherTrends("New York");

        assertNotNull(stats);
        verify(weatherFetcher, times(7)).fetchCurrentWeather("New York");
        assertEquals(25.0f, stats.getAverageTemperature());
    }

    @Test
    void testFetchHistoricalWeatherData() {
        WeatherData sampleWeather = new WeatherData(22.0f, 55, "Rainy");
        when(weatherFetcher.fetchCurrentWeather("Los Angeles")).thenReturn(sampleWeather);

        var result = analyzer.fetchHistoricalWeatherData("Los Angeles");

        assertNotNull(result);
        assertEquals(7, result.size());
        result.forEach(data -> {
            assertEquals(22.0f, data.getTemperature());
            assertEquals(55, data.getHumidity());
        });
    }
}

