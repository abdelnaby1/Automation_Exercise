package greenKart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CartPage {
    private WebDriver driver;
    private By productNameOnCart(int productIdx){
        return By.cssSelector("#productCartTables tbody tr:nth-child("+productIdx+") td:nth-child(2)");
    }
    private By placeOrderBtn = By.xpath("//button[contains(text(),'Place Order')]");

    private By countryField = By.tagName("select");
    private By termsField = By.cssSelector("input.chkAgree");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getProductNameOnCart(int productIdx){
        return new ElementActions(driver)
                .getText(productNameOnCart(productIdx)).split("-")[0].trim();
    }
    public CartPage placeOrder(String country){
        new ElementActions(driver)
                .click(placeOrderBtn)
                .select(countryField, ElementActions.SelectType.VALUE,country)
                .click(termsField);
        return this;
    }
}
