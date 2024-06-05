package Model;

public class WeatherStatistics {
    private float averageTemperature;
    private float highestTemperature;
    private float lowestTemperature;
    private float averageHumidity;
    private int highestHumidity;
    private int lowestHumidity;

    public WeatherStatistics(float averageTemperature, float highestTemperature, float lowestTemperature, float averageHumidity, int highestHumidity, int lowestHumidity) {
        this.averageTemperature = averageTemperature;
        this.highestTemperature = highestTemperature;
        this.lowestTemperature = lowestTemperature;
        this.averageHumidity = averageHumidity;
        this.highestHumidity = highestHumidity;
        this.lowestHumidity = lowestHumidity;
    }

    public float getAverageTemperature() {
        return averageTemperature;
    }

    public float getHighestTemperature() {
        return highestTemperature;
    }

    public float getLowestTemperature() {
        return lowestTemperature;
    }

    public float getAverageHumidity() {
        return averageHumidity;
    }

    public int getHighestHumidity() {
        return highestHumidity;
    }

    public int getLowestHumidity() {
        return lowestHumidity;
    }
}


