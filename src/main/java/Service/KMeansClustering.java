package Service;
import Model.WeatherData;
import Model.Cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeansClustering {

    public static List<Cluster> performClustering(List<WeatherData> data, int k) {
        List<Cluster> clusters = initializeClusters(data, k);
        List<WeatherData> lastCentroids;
        boolean finished = false;
        int iterations = 0;

        while (!finished && iterations++ < 100) {
            clearClusters(clusters);
            lastCentroids = getCentroids(clusters);

            assignClusters(data, clusters);
            calculateCentroids(clusters);

            List<WeatherData> currentCentroids = getCentroids(clusters);
            double distance = calculateDistanceBetweenCentroids(lastCentroids, currentCentroids);
            if (distance == 0) {
                finished = true;
            }
        }
        return clusters;
    }

    public static List<Cluster> initializeClusters(List<WeatherData> data, int k) {
        Random random = new Random();
        List<Cluster> clusters = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(data.get(random.nextInt(data.size()))));
        }
        return clusters;
    }

    private static void clearClusters(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private static List<WeatherData> getCentroids(List<Cluster> clusters) {
        List<WeatherData> centroids = new ArrayList<>();
        for (Cluster cluster : clusters) {
            centroids.add(cluster.getCentroid());
        }
        return centroids;
    }

    static void assignClusters(List<WeatherData> data, List<Cluster> clusters) {
        for (WeatherData point : data) {
            Cluster bestCluster = null;
            double minDistance = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double distance = point.distanceTo(cluster.getCentroid());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestCluster = cluster;
                }
            }
            if (bestCluster != null) {
                bestCluster.addPoint(point);
            }
        }
    }

    static void calculateCentroids(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            cluster.calculateCentroid();
        }
    }

    private static double calculateDistanceBetweenCentroids(List<WeatherData> lastCentroids, List<WeatherData> currentCentroids) {
        double totalDistance = 0;
        for (int i = 0; i < lastCentroids.size(); i++) {
            totalDistance += lastCentroids.get(i).distanceTo(currentCentroids.get(i));
        }
        return totalDistance;
    }
}
