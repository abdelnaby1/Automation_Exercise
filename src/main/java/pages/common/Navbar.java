package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.OrdersPage;
import utils.ElementActions;

public class Navbar {
    WebDriver driver;

    public Navbar(WebDriver driver) {
        this.driver = driver;
    }

    private By cartBtn = By.cssSelector("button[routerlink*='cart']");
    private By ordersBtn = By.cssSelector("button[routerlink='/dashboard/myorders']");
    public CartPage goToCart(){
        new ElementActions(driver).click(cartBtn);
        return new CartPage(driver);
    }
    public OrdersPage gotToMyOrders(){
        new ElementActions(driver).click(ordersBtn);
        return new OrdersPage(driver);
    }

}
