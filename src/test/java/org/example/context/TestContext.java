package org.example.context;

import org.example.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Locale;
import java.util.Map;

public class TestContext {
    private WebDriver driver;
    public void startBrowser() {
        String browser = ConfigReader.getProperty("browser");
        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", Map.of(
                "profile.password_manager_leak_detection", false,
                "credentials_enable_service", false
        ));
        driver = switch (browser.toLowerCase(Locale.ROOT)){
            case "chrome" -> new ChromeDriver(options);
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalArgumentException("Browser not supported "+ browser);
        };
        driver.manage().window().maximize();
    }
    public WebDriver getDriver() {
        return driver;
    }
    public void closeBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
