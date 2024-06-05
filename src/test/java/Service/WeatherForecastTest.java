package Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import Repository.WeatherFetcher;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class WeatherForecastTest {
    @Mock
    private WeatherFetcher weatherFetcher;

    @Mock
    private WeatherTrendAnalyzer weatherTrendAnalyzer;

    @InjectMocks
    private WeatherForecast weatherForecast;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void handleNextDayForecastTest() {
        // Redirect System.out to capture the print output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Method under test
        weatherForecast.handleNextDayForecast("");

        // Validate the output
        assertTrue(outContent.toString().contains("Location should not be null or empty."));
        System.setOut(System.out);
    }


}
