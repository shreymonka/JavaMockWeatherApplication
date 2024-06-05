package Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Model.Cluster;
import Model.WeatherData;
import Repository.WeatherFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecommendationTest {

    private WeatherFetcher weatherFetcher;
    private ActivityRecommendation activityRecommendation;

    @BeforeEach
    public void setup() {
        weatherFetcher = mock(WeatherFetcher.class);
        activityRecommendation = new ActivityRecommendation(weatherFetcher);
    }

    @Test
    public void testGetActivityRecommendation() {
        assertEquals("a great day for outdoor sports like tennis or cycling", activityRecommendation.getActivityRecommendation("Sunny"));
        assertEquals("a good day to visit a museum or watch movies indoors", activityRecommendation.getActivityRecommendation("Rainy"));
        assertEquals("ideal for outdoor photography or a relaxed walk in the park", activityRecommendation.getActivityRecommendation("Cloudy"));
        assertEquals("perfect for skiing or enjoying a hot drink by the fireplace", activityRecommendation.getActivityRecommendation("Snowy"));
        assertEquals("check daily forecasts closer to the date for specific activities", activityRecommendation.getActivityRecommendation("Variable"));
    }

    @Test
    public void testGetMostCommonCondition() {
        Cluster cluster = new Cluster(new WeatherData(25.0f, 56, "Sunny"));
        cluster.addPoint(new WeatherData(25.0f, 56, "Sunny"));
        cluster.addPoint(new WeatherData(25.0f, 56, "Sunny"));
        cluster.addPoint(new WeatherData(25.0f, 56, "Sunny"));

        String mostCommonCondition = activityRecommendation.getMostCommonCondition(cluster);
        assertEquals("Sunny", mostCommonCondition);
    }

    @Test
    public void testActivityRecommendationForUnknownCondition() {
        assertEquals("check daily forecasts closer to the date for specific activities", activityRecommendation.getActivityRecommendation("Foggy"));
    }

    @Test
    public void testMostCommonConditionWithEmptyCluster() {
        Cluster emptyCluster = new Cluster(new WeatherData(32.0f, 32, "Sunny"));
        String mostCommonCondition = activityRecommendation.getMostCommonCondition(emptyCluster);
        assertEquals("Variable", mostCommonCondition);
    }

    @Test
    public void testAnalyzeAndDisplayClusterInsights() {
        List<Cluster> clusters = new ArrayList<>();
        clusters.add(new Cluster(new WeatherData(25.0f, 56, "Sunny")));
        clusters.add(new Cluster(new WeatherData(25.0f, 56, "Sunny")));

        clusters.get(0).addPoint(new WeatherData(32.0f, 89, "Rainy"));
        clusters.get(1).addPoint(new WeatherData(32.0f, 89, "Rainy"));

        activityRecommendation.analyzeAndDisplayClusterInsights(clusters);
    }
}
