package Setup;

import Utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TemplateSetup {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        String browser = System.getProperty("browser");
        String url = "https://" + System.getProperty("environment");

        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();

        if (browser.equalsIgnoreCase("headless")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("msedge")) {
            driver = new EdgeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else {
            Log.error("UNABLE TO INITIATE WEB BROWSER.");
        }

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.get(url);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
