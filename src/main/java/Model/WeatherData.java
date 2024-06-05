package Model;

public class WeatherData {
    private float temperature;
    private int humidity;
    private String condition;

    public WeatherData(float temperature, int humidity, String condition) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.condition = condition;
    }

    public float getTemperature(){
        return temperature;
    }

    public int getHumidity(){
        return humidity;
    }

    public String getCondition(){
        return condition;
    }

    public double distanceTo(WeatherData other) {
        return Math.sqrt(Math.pow(this.temperature - other.temperature, 2) +
                Math.pow(this.humidity - other.humidity, 2));
    }

}
