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
    private final WebDriver driver;
    private final By searchField = By.cssSelector("input.search-keyword");
    private final By submitBtn = By.cssSelector("button.search-button");
    private final By resultName =  By.cssSelector(".products-wrapper .products:first-child  .product-name");
    private final By offersLink = By.linkText("Top Deals");
    private By addToCartBtn(int productIdx){
        return By.xpath("(//*[contains(text(),'ADD TO CART')])["+productIdx+"]");
    }
    private By qtyField(int productIdx){
        return By.xpath("(//*[contains(@class,'quantity')])["+productIdx+"]");
    }
    private final By cartLink = By.xpath("//a[contains(@class,'cart-icon')]");
    private final By checkoutBtn = By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
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

    public HomePage addProductToCart(int productIdx, int qty,String shortName){
        new ElementActions(driver)
//                .waitForElementToContainsText(resultName,shortName)
                .type(qtyField(productIdx), String.valueOf(qty),true)
                .click(addToCartBtn(productIdx));
        return this;
    }
    public CartPage goToCart(){
        new ElementActions(driver)
                .click(cartLink)
                .click(checkoutBtn);
        return new CartPage(driver);
    }
}
