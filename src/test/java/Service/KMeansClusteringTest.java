package Service;

import static org.junit.jupiter.api.Assertions.*;

import Model.Cluster;
import Model.WeatherData;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class KMeansClusteringTest {
    @Test
    public void testInitializeClusters() {
        List<WeatherData> data = Arrays.asList(new WeatherData(1.0f, 2, "Sunny"), new WeatherData(5.0f, 40, "Cloudy"));
        List<Cluster> clusters = KMeansClustering.initializeClusters(data, 2);
        assertEquals(2, clusters.size());
    }


    @Test
    public void testCalculateCentroids() {
        List<WeatherData> data = Arrays.asList(new WeatherData(1.0f, 2, "Sunny"), new WeatherData(5.0f, 40, "Cloudy"));
        List<Cluster> clusters = KMeansClustering.initializeClusters(data, 2);
        KMeansClustering.assignClusters(data, clusters);
        KMeansClustering.calculateCentroids(clusters);
        assertNotNull(clusters.get(0).getCentroid());
    }

    @Test
    public void testPerformClustering() {
        List<WeatherData> data = Arrays.asList(new WeatherData(1.0f, 2, "Sunny"), new WeatherData(5.0f, 40, "Cloudy"));
        List<Cluster> clusters = KMeansClustering.performClustering(data, 2);
        assertNotNull(clusters);
        assertTrue(clusters.stream().allMatch(cluster -> !cluster.getPoints().isEmpty()));
    }
}
