package Model;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<WeatherData> points;
    private WeatherData centroid;

    public Cluster(WeatherData centroid) {
        this.centroid = centroid;
        this.points = new ArrayList<>();
    }

    public void addPoint(WeatherData point) {
        points.add(point);
    }

    public void clear() {
        points.clear();
    }

    public WeatherData getCentroid() {
        return centroid;
    }

    public List<WeatherData> getPoints() {
        return points;
    }

    // Calculate centroid based only on temperature and humidity
    public void calculateCentroid() {
        double sumTemp = 0;
        double sumHumidity = 0;
        if (!points.isEmpty()) {
            for (WeatherData point : points) {
                sumTemp += point.getTemperature();
                sumHumidity += point.getHumidity();
            }
            double avgTemp = sumTemp / points.size();
            double avgHumidity = (sumHumidity / points.size());
            this.centroid = new WeatherData((float)avgTemp, (int) avgHumidity, this.centroid.getCondition());
        }
    }
}