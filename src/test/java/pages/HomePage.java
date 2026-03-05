package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    By extendMenu = By.xpath("/html/body/div/header/nav[1]/div/div/div/div/ul/li[4]/button/span");

    By getWordPressLink = By.cssSelector("a.global-header__desktop-get-wordpress.global-header__get-wordpress");

    By communityMenu = By.cssSelector("button[aria-label='Community submenu']");

    By photoDirectory = By.cssSelector("a[href='https://wordpress.org/photos/']");

    By searchBox = By.xpath("//input[@id='wp-block-search__input-8']");

    By images = By.xpath("//ul[contains(@class,'wp-block-post-template')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void hoverExtendAndClickGetWordPress() {

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement extend = wait.until(
                ExpectedConditions.visibilityOfElementLocated(extendMenu)
        );

        actions.moveToElement(extend).perform();

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement getWP = wait.until(
                ExpectedConditions.elementToBeClickable(getWordPressLink)
        );

        getWP.click();

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public String getH1Text() {

        WebElement h1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );

        return h1.getText().trim();
    }

    public void hoverCommunityAndClickPhotoDirectory() {

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement community = wait.until(
                ExpectedConditions.visibilityOfElementLocated(communityMenu)
        );

        actions.moveToElement(community).perform();

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        community.click();

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement photoDir = wait.until(
                ExpectedConditions.presenceOfElementLocated(photoDirectory)
        );

        js.executeScript("arguments[0].click();", photoDir);

        wait.until(ExpectedConditions.urlContains("photos"));
    }

    public void searchFor(String keyword) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox))
                .sendKeys(keyword + "\n");
    }

    public boolean areImagesDisplayed() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(images));
        List<WebElement> imgList = driver.findElements(images);

        return imgList.size() > 0;
    }
}