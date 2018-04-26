package testrunner;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static core.BrowserCore.*;

/**
 * Created by zsuleiman on 25/04/2018.
 */
public class Hooks {

    @Before
    public void setUp(){

        initialiseDriver();
        maximiseBrowserWindow();
    }


    @After
    public void tearDown(Scenario scenario) {

        screenShotOnFailure(scenario);
        quitBrowser();

    }
}
