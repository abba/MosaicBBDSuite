package testrunner;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by zsuleiman on 25/04/2018.
 */



@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        jsonReport = "./target/cucumber.json",
        overviewReport = true,
        detailedReport = true,
        outputFolder = "./target/",
        toPDF = true,
        screenShotLocation ="./target/screenshots/")
@CucumberOptions(
        features="./src/main/resources/features/",
        glue={"testrunner","pageobjects","stepsdefinitions"},
        plugin={ "pretty",
                "html:./target/cucumber-html-report",
                "json:./target/cucumber.json",
                "junit:./target/cucumber-results.xml"},
        tags = {"~@ignore"}

)

public class TestRunner {


}
