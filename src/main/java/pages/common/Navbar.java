package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.OrdersPage;
import pages.ProductCataloguePage;
import utils.ElementActions;

public class Navbar {

    private final WebDriver driver;

    public Navbar(WebDriver driver) {
        this.driver = driver;
    }

    private final By cartBtn = By.cssSelector("button[routerlink*='cart']");
    private final By ordersBtn = By.cssSelector("button[routerlink='/dashboard/myorders']");
    public CartPage goToCart(){
        new ElementActions(driver).click(cartBtn);
        return new CartPage(driver);
    }
    public OrdersPage gotToMyOrders(){
        new ElementActions(driver).click(ordersBtn);
        return new OrdersPage(driver);
    }

}
