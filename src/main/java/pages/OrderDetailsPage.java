package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class OrderDetailsPage {
    private final WebDriver driver;
    private final By myOrdersLoc = By.xpath("//label[text()=' Orders History Page ']");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrdersPage goToMyOrders(){
        new ElementActions(driver).click(myOrdersLoc);
        return new OrdersPage(driver);
    }

}
