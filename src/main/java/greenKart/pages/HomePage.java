package greenKart.pages;

import org.bouncycastle.jcajce.provider.drbg.DRBG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserActions;
import utils.ElementActions;

import java.util.Iterator;
import java.util.Set;

public class HomePage {
    private final String url = "https://rahulshettyacademy.com/seleniumPractise";
    private WebDriver driver;
    private By searchField = By.cssSelector("input.search-keyword");
    private By submitBtn = By.cssSelector("button.search-button");
    private By resultName =  By.cssSelector(".products-wrapper .products:first-child  .product-name");
    private By offersLink = By.linkText("Top Deals");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage openHomePage(){
        BrowserActions.navigateToUrl(driver,url);
        return this;
    }
    public HomePage searchFor(String searchTerm){
        new ElementActions(driver)
                .type(searchField,searchTerm)
                .click(submitBtn);
        return this;
    }
    public String getProductName(String text){
        return new ElementActions(driver)
                .waitForElementToContainsText(resultName,text)
                .getText(resultName).split("-")[0].trim();
    }
    public OffersPage openOffersPage(){
        new ElementActions(driver).click(offersLink);
        return new OffersPage(driver);
    }
}
