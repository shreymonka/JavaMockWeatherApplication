package Model;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherStatisticsTest {

    @Test
    public void testConstructor(){
        float averageTemperature = 23.0f;
        float highestTemperature = 32.5f;
        float lowestTemperature = 2.34f;
        float averageHumidity = 23;
        int highestHumidity = 88;
        int lowestHumidity = 10;

        WeatherStatistics statistics = new WeatherStatistics(averageTemperature, highestTemperature, lowestTemperature, averageHumidity, highestHumidity, lowestHumidity);

        assertEquals(averageTemperature, statistics.getAverageTemperature(), 0.0f);
        assertEquals(highestTemperature, statistics.getHighestTemperature(), 0.0f);
        assertEquals(lowestTemperature, statistics.getLowestTemperature(), 0.0f);
        assertEquals(averageHumidity, statistics.getAverageHumidity(), 0.0f);
        assertEquals(highestHumidity, statistics.getHighestHumidity());
        assertEquals(lowestHumidity, statistics.getLowestHumidity());
    }

    @Test
    public void testAverageTemp(){
        float expectedAvgTemp = 23.0f;
        WeatherStatistics statistics = new WeatherStatistics(expectedAvgTemp, 45, 10, 23, 80, 10);
        assertEquals(expectedAvgTemp, statistics.getAverageTemperature(), 0.0f);
    }

    @Test
    public void testHighestTemp(){
        float expectedHighTemp = 32.5f;
        WeatherStatistics statistics = new WeatherStatistics(15.0f, expectedHighTemp, 10, 23, 80, 10);
        assertEquals(expectedHighTemp, statistics.getHighestTemperature(), 0.0f);
    }

    @Test
    public void testLowestTemp(){
        float expectedLowestTemp = 23.0f;
        WeatherStatistics statistics = new WeatherStatistics(12.0f, 45, expectedLowestTemp, 23, 80, 10);
        assertEquals(expectedLowestTemp, statistics.getLowestTemperature(), 0.0f);
    }

    @Test
    public void testAverageHumidity(){
        float expectedAvgHumidity = 45.55f;
        WeatherStatistics statistics = new WeatherStatistics(34, 45, 10, expectedAvgHumidity, 80, 10);
        assertEquals(expectedAvgHumidity, statistics.getAverageHumidity(), 0.0f);
    }

    @Test
    public void testHighestHumidity(){
        int expectedHighestHumidity = 90;
        WeatherStatistics statistics = new WeatherStatistics(34, 45, 10, 34.5f, expectedHighestHumidity, 10);
        assertEquals(expectedHighestHumidity, statistics.getHighestHumidity());
    }

    @Test
    public void testLowestHumidity(){
        int expectedLowestHumidity = 10;
        WeatherStatistics statistics = new WeatherStatistics(34, 45, 10, 35.9f, 80, expectedLowestHumidity);
        assertEquals(expectedLowestHumidity, statistics.getLowestHumidity());
    }

}
