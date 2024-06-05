package Model;

public class WeatherAlert {
    private String alertType;
    private String description;
    private String safetyTips;

    public WeatherAlert(String alertType, String description, String safetyTips){
        this.alertType = alertType;
        this.description = description;
        this.safetyTips = safetyTips;
    }

    public String getAlertType(){
        return alertType;
    }

    public String getDescription(){
        return description;
    }

    public String getSafetyTips(){
        return safetyTips;
    }
}
