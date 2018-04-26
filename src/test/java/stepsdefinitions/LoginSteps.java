package stepsdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageobjects.Dashboard;
import pageobjects.LoginPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zsuleiman on 25/04/2018.
 */
public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("^User navigates to App$")
    public void userNavigatesToApp(){

        loginPage.gotoLoginPage();
    }

    @Then("^User should be on Login Page$")
    public void userIsOnLoginPage(){

        assertThat(loginPage.pageTitle(),is("Dashboard Template for Bootstrap"));
    }

    @Given("^User Login using credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userLoginUsingCredential(String username,String password){

        loginPage.setUserName(username).setPassword(password).submit();
    }


    @Then("^User should be logged in successful$")
    public void successfulUserLogin(){
        assertThat(new Dashboard().pageUrl(),containsString("dashboard"));


    }

    @Then("^User should be be logged in successfully$")
    public void unsuccessfulUserLogin(){
        assertThat(new Dashboard().pageUrl(),not(containsString("dashboard")));


    }
}
