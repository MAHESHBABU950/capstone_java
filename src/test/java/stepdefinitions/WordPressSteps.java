package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;

public class WordPressSteps extends BaseTest {

    HomePage home;

    @Given("User launches wordpress website")
    public void launchWebsite() {
        setup();
        home = new HomePage(driver);
    }

    @Then("Verify homepage title")
    public void verifyTitle() {

        Assert.assertTrue(
                home.getPageTitle().contains("WordPress"),
                "Title should contain WordPress but was: " + home.getPageTitle()
        );
    }

    @When("User hover Extend and click Get WordPress")
    public void hoverExtendGetWordPress() {
        home.hoverExtendAndClickGetWordPress();
    }

    @Then("Verify header text as {string}")
    public void verifyHeaderText(String expected) {

        String actual = home.getH1Text();

        Assert.assertTrue(
                actual.contains(expected),
                "Expected header: " + expected + " but got: " + actual
        );
    }

    @When("User hover Community and click Photo Directory")
    public void hoverCommunityPhotoDirectory() {
        home.hoverCommunityAndClickPhotoDirectory();
    }

    @And("User searches for {string}")
    public void searchPhoto(String keyword) {
        home.searchFor(keyword);
    }

    @Then("Verify photos are displayed")
    public void verifyPhotos() {

        Assert.assertTrue(
                home.areImagesDisplayed(),
                "Photos should be displayed but none found"
        );

        tearDown();
    }
}