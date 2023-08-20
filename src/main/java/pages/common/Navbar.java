package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import utils.ElementActions;

public class Navbar {
    WebDriver driver;

    public Navbar(WebDriver driver) {
        this.driver = driver;
    }

    private By cartBtn = By.cssSelector("button[routerlink*='cart']");
    public CartPage goToCart(){
        new ElementActions(driver).click(cartBtn);
        return CartPage.using(driver);
    }

}
