package Model;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherAlertTest {
    @Test
    public void testConstructor(){
        String alertType = "Heat Alert";
        String description = "Average temperature exceeds 35°C";
        String safetyTips = "Stay hydrated and avoid outdoor activities.";

        WeatherAlert alert = new WeatherAlert(alertType, description, safetyTips);

        assertEquals(alertType, alert.getAlertType());
        assertEquals(description, alert.getDescription());
        assertEquals(safetyTips, alert.getSafetyTips());
    }

    @Test
    public void testGetAlertType(){
        String alertType = "Heat Alert";
        WeatherAlert weatherAlert = new WeatherAlert(alertType, "Average temperature exceeds 35°C", "Stay hydrated and avoid outdoor activities.");
        assertEquals(alertType, weatherAlert.getAlertType());
    }

    @Test
    public void testGetDescription(){
        String expectedDescription = "Average temperature exceeds 35°C";
        WeatherAlert weatherAlert = new WeatherAlert("Cold Alert", expectedDescription, "Stay warm and avoid exposure to cold.");
        assertEquals(expectedDescription, weatherAlert.getDescription());
    }

    @Test
    public void testGetSafetyTips(){
        String expectedSafetyTip = "Be prepared for sudden weather changes.";
        WeatherAlert weatherAlert = new WeatherAlert("Rapid Temperature Change Alert", "Temperature changes more than 10°C within 24 hours", expectedSafetyTip);
        assertEquals(expectedSafetyTip, weatherAlert.getSafetyTips());
    }
}
