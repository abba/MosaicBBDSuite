package core;

import com.google.common.base.Optional;
import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.concurrent.TimeUnit;

/**
 * Created by zsuleiman on 25/04/2018.
 */
public class BrowserCore {


    public static WebDriver aDriver;
    private static String currentDir = System.getProperty("user.dir");
    private static BrowserName defaultBrowserName;
    private static String browserName;


    public BrowserCore(WebDriver aDriver){

        this.aDriver= aDriver;

    }

    public static void setSystemsProperties(String browser, String driverPath) {


        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver." + browser + ".driver", currentDir
                    + driverPath + ".exe");
        } else {
            System.setProperty("webdriver." + browser + ".driver", currentDir
                    + driverPath);
        }

    }

    public static String getBrowserName() {

        try {
            browserName = System.getProperty("browserName").trim();

        } catch (NullPointerException e) {

            System.out.println("No Browser not specified....."+"\nUsing Chrome as Default Browser.....");
        }

        return browserName;
    }


    public enum BrowserName {

        FIREFOX("firefox"), CHROME("chrome");

        String name;

        BrowserName(String name) {
            this.name = name;
        }

        public static Optional<BrowserName> findByName(String browserName) {
            for (BrowserName bName : values()) {
                if (bName.name.equalsIgnoreCase(browserName)) {
                    return Optional.of(bName);
                }
            }
            return Optional.absent();
        }

    }

    public static WebDriver initialiseDriver() {

        defaultBrowserName = BrowserName.findByName(getBrowserName()).or(BrowserName.CHROME);

        switch (defaultBrowserName) {

            case FIREFOX:
                setSystemsProperties("gecko", "/drivers/geckodriver");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("marionette", true);
                aDriver = new FirefoxDriver(firefoxOptions);
                aDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
                break;

            case CHROME:
                setSystemsProperties("chrome", "/drivers/chromedriver");
                aDriver = new ChromeDriver();
                aDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
                break;
        }

        return aDriver;
    }

    public static void quitBrowser() {

        try {

            aDriver.close();
            if (aDriver != null) {

                aDriver = null;
            }

        } catch (UnreachableBrowserException e) {

            System.out.println("Browser has already Shutdown");

        }


    }

    public static void screenShotOnFailure(Scenario scenario) {


        if (scenario.isFailed()) try {

            scenario.write(scenario.getName() + " ---- " + scenario.getId()
                    + " ------- " + scenario.getStatus() + "\n"
                    + aDriver.getCurrentUrl());

            byte[] screenshot = ((TakesScreenshot) aDriver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");

        } catch (WebDriverException screenShotException) {
            System.err.println(screenShotException.getMessage());
        }
    }

    public static void maximiseBrowserWindow(){


        aDriver.manage().window().maximize();
    }

}
