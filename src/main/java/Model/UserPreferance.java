package Model;

public class UserPreferance {
    private String temperatureUnit;

    public UserPreferance(String temperatureUnit){
        this.temperatureUnit = temperatureUnit;
    }

    public String getTemperatureUnit(){
        return temperatureUnit;
    }

}