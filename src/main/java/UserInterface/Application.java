package UserInterface;

import Repository.WeatherFetcher;
import Service.*;

import java.util.Scanner;

public class Application {
    private WeatherFetcher weatherFetcher;
    private Scanner scanner;

    public Application(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Weather Fetcher UserInterface.Application!");
        boolean running = true;

        while (running) {
            displayMenu();
            String input = scanner.nextLine();
            running = processInput(input);
        }
        System.out.println("Thank you for using the Weather Fetcher UserInterface.Application!");
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Current Weather");
        System.out.println("2. Set Preferences");
        System.out.println("3. Compare Weather with another location");
        System.out.println("4. Analyze Weather Trend");
        System.out.println("5. Activity Recommendation for next 3 days");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean processInput(String input) {

        switch (input) {
            case "1":
                System.out.print("Enter location: ");
                String location = scanner.nextLine();
                CurrentWeather currentWeather = new CurrentWeather(weatherFetcher);
                currentWeather.displayCurrentWeather(location);
                break;
            case "2":
                HandleUserPreferance handleUserPreferences = new HandleUserPreferance(weatherFetcher);
                handleUserPreferences.handlePreferance();
                break;
            case "3":
                System.out.print("Enter first location: ");
                String firstLocation = scanner.nextLine();
                System.out.print("Enter the second location: ");
                String secondlocation = scanner.nextLine();
                CompareWeather compareWeather = new CompareWeather(weatherFetcher);
                compareWeather.compareWeather(firstLocation, secondlocation);
                break;
            case "4":
                System.out.print("Enter location: ");
                String analyzeLocation = scanner.nextLine();
                WeatherTrendAnalyzer weatherTrendAnalyzer = new WeatherTrendAnalyzer(weatherFetcher);
                weatherTrendAnalyzer.handleWeatherTrendAnalysis(analyzeLocation);
                break;
            case "5":
                System.out.print("Enter location: ");
                String recommendLocation = scanner.nextLine();
                ActivityRecommendation recommendation = new ActivityRecommendation(weatherFetcher);
                recommendation.performClustering(recommendLocation);
                break;
            case "6":
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
        return true;
    }
}