package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CartPage {
    private final WebDriver driver;

    private final By products = By.xpath("//*[contains(@class,'cartSection')]/h3");
    private final By checkoutBtn = By.xpath("//button[contains(text(),'Checkout')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public Boolean isProductAdded(String actualProductName){
        new ElementActions(driver)
                .waitForVisibility(products);
        var cartProducts = driver.findElements(products);
       return cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(actualProductName));
    }
    public CheckoutPage goToCheckout(){
        new ElementActions(driver).click(checkoutBtn);
        return new CheckoutPage(driver);
    }
}
