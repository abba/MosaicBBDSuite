package pageobjects;

import com.jayway.jsonpath.JsonPath;
import core.BrowserCore;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static com.jayway.restassured.RestAssured.get;
import static org.openqa.selenium.By.xpath;

/**
 * Created by zsuleiman on 25/04/2018.
 */
public class Dashboard extends BrowserCore {

    private static String currenciesURL = "http://api.fixer.io/latest?base=GBP";


    @CacheLookup
    @FindBy(css = "td:nth-child(2)")
    private List<WebElement> tableRatesList;

    private LinkedHashSet<String> fetchedCurrenciesList = new LinkedHashSet<>();
    private LinkedHashSet<Double> fetchedCurrenciesRateList = new LinkedHashSet<>();
    private LinkedHashSet<String> tableCurrenciesList = new LinkedHashSet<>();
    private LinkedHashSet<Double> tableCurrenciesRatesList = new LinkedHashSet<>();

    public Dashboard() {
        super(aDriver);

        PageFactory.initElements(new AjaxElementLocatorFactory(aDriver, 10), this);
    }

    public String pageUrl() {

        return aDriver.getCurrentUrl();

    }

    public String fetchedApiJSON() {

        return get(currenciesURL).getBody().asString();

    }

    public String getFetchedCurrencyRate(String currency) {

        String rate = JsonPath.parse(fetchedApiJSON()).read("$.rates['" + currency + "']").toString();

        return rate;
    }

    public LinkedHashMap<String, Double> fetchedCurrencies() {

        return JsonPath.parse(fetchedApiJSON()).read("$.rates");

    }


    public LinkedHashSet<String> getFetchedCurrenciesList() {


        fetchedCurrencies().forEach((key, value) -> {

            fetchedCurrenciesList.add(key);
        });
        return fetchedCurrenciesList;
    }

    public LinkedHashSet<Double> getFetchedCurrenciesRateList() {


        fetchedCurrencies().forEach((key, value) -> {

            fetchedCurrenciesRateList.add(value);
        });
        return fetchedCurrenciesRateList;
    }


    public LinkedHashSet<String> getTableCurrenciesList() {

        Wait<WebDriver> wait = new FluentWait<>(aDriver)
                .withTimeout(Duration.ofSeconds(10L))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);


        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//td[contains(.,'A')]")));

        List<WebElement> currenciesList = aDriver.findElements(xpath("//td[not(contains(.,'.'))]"));

        currenciesList.forEach((currency) -> {
            tableCurrenciesList.add(currency.getText());

        });

        return tableCurrenciesList;

    }

    public String getTableCurrency(String currency) {

        return aDriver.findElement(xpath("//td[.='" + currency + "']")).getText();
    }


    public String getTableCurrencyRate(String rate) {

        WebElement rateField = new WebDriverWait(aDriver, 5L)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        xpath("//tr/td[.='" + rate + "']//following::td[1]")));

        return rateField.getText();
    }

    public LinkedHashSet<Double> getTableCurrenciesRatesList() {

        tableRatesList.forEach((value) -> {
            tableCurrenciesRatesList.add(Double.parseDouble(value.getText()));
        });

        return tableCurrenciesRatesList;
    }


    public Boolean isTableDisplayed() {

        return getTableCurrenciesList().size() != 0 && getTableCurrenciesRatesList().size() != 0;


    }


}

