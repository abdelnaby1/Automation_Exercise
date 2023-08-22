package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class OrdersPage {
    private WebDriver driver;
    private By lastOrderLoc =By.cssSelector("tbody tr:nth-child(1) td:nth-child(3)");

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getLastProductOrdered(){
        return new ElementActions(driver).getText(lastOrderLoc);
    }
}
