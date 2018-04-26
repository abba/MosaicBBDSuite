package stepsdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.Dashboard;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by zsuleiman on 25/04/2018.
 */
public class DashboardSteps {

    Dashboard dashboard = new Dashboard();

    @Given("^User is on dashboard$")
    public void userIsOnDashboard(){

        assertThat(dashboard.pageUrl(),containsString("dashboard"));
    }

    @Given("^Table is displayed$")
    public void tableIsDisplayed(){

        assertThat(dashboard.isTableDisplayed(),is(true));

    }

    @Given("^Table should display all currencies$")
    public void tableShouldDisplayAllCurrencies(){

        assertThat(dashboard.getTableCurrenciesList().size(),equalTo(dashboard.getFetchedCurrenciesList().size()));
    }


    @When("^Currency \"([^\"]*)\" is displayed$")
    public void currencyIsDisplayed(String currency){
                assertThat(dashboard.getTableCurrency(currency),is(currency));
    }

         @Then("^The Currency \"([^\"]*)\" and rate should be correct$")
     public void currencyAndRateShouldBe(String currency){
                assertThat(dashboard.getTableCurrencyRate(currency),
                        is(dashboard.getFetchedCurrencyRate(currency)));
    }

}
