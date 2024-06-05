package Model;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserPreferanceTest {

    @Test
    public void testConstructor(){
        String temperatureUnit = "F";
        UserPreferance preferences = new UserPreferance(temperatureUnit);
        assertEquals(temperatureUnit, preferences.getTemperatureUnit());
    }

    @Test
    public void testGetTemperatureUnit(){
        String invalidTempUnit = "Q";
        String validInput = "C";
        UserPreferance preferences = new UserPreferance(validInput);
        assertNotEquals(invalidTempUnit, preferences.getTemperatureUnit());
    }

}
