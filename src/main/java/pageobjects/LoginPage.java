package pageobjects;

import core.BrowserCore;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by zsuleiman on 25/04/2018.
 */
public class LoginPage extends BrowserCore {


    public static final String APP_URL="http://mosaic-test-app.s3-website.eu-west-2.amazonaws.com/";

    @FindBy(css = "input[placeholder='username']")
    private WebElement usernameField;

    @FindBy(css = "input[placeholder='password']")
    private WebElement passwordField;

    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;

    public LoginPage(){

        super(aDriver);

        PageFactory.initElements(aDriver, this);

    }


    public LoginPage gotoLoginPage() {

        aDriver.navigate().to(APP_URL);


        return new LoginPage();
    }


    public String pageTitle(){

        String pageTitle = aDriver.getTitle();

        return pageTitle;
    }

    public LoginPage setUserName(String userName){

        usernameField.sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password){

        passwordField.sendKeys(password);
        return this;
    }

    public void submit(){

        submitButton.click();

    }
}
