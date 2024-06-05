package Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Model.UserPreferance;
import Repository.WeatherFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class HandleUserPreferanceTest {

    private WeatherFetcher weatherFetcher;
    private HandleUserPreferance handleUserPreferences;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        weatherFetcher = mock(WeatherFetcher.class);
        handleUserPreferences = new HandleUserPreferance(weatherFetcher);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidTemperatureUnit() {
        String input = "C\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handleUserPreferences.handlePreferance();
        verify(weatherFetcher).setUserPreferences(any(UserPreferance.class));
        assertTrue(outContent.toString().contains("Preferences updated successfully."));
    }

    @Test
    public void testInvalidTemperatureUnit() {
        String input = "X\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handleUserPreferences.handlePreferance();
        verify(weatherFetcher).setUserPreferences(any(UserPreferance.class));
        assertTrue(outContent.toString().contains("Invalid temperature unit. Setting to default (Celsius)."));
    }

    @Test
    public void testValidTemperatureUnitFahrenheit() {
        String input = "F\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handleUserPreferences.handlePreferance();
        verify(weatherFetcher).setUserPreferences(any(UserPreferance.class));
        assertTrue(outContent.toString().contains("Preferences updated successfully."));
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }
}
