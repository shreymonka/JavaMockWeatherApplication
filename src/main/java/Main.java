import Repository.WeatherFetcher;
import Repository.WeatherService;
import UserInterface.Application;

public class Main {
    public static void main(String[] args) {
        Application app = new Application(new WeatherFetcher(new WeatherService()));
        app.start();
    }
}