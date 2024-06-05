package Service;

import Model.UserPreferance;
import Repository.WeatherFetcher;

import java.util.Scanner;

public class HandleUserPreferance {

    private WeatherFetcher weatherFetcher;

    public HandleUserPreferance(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    public void handlePreferance() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose temperature unit (C/F): ");
        String tempUnit = scanner.nextLine().toUpperCase();
        if (!tempUnit.equals("C") && !tempUnit.equals("F")) {
            System.out.println("Invalid temperature unit. Setting to default (Celsius).");
            tempUnit = "C";
        }

        UserPreferance preferences = new UserPreferance(tempUnit);
        weatherFetcher.setUserPreferences(preferences);

        System.out.println("Preferences updated successfully.");
    }
}
