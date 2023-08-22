package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementActions;

import java.util.Objects;

public class OrdersPage {
    private WebDriver driver;
    private By lastOrderLoc = By.cssSelector("tbody tr:nth-child(1) td:nth-child(3)");
    private By productsNamesLoc = By.xpath("//tbody/tr/td[2]");
    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getLastProductOrdered(){
        return new ElementActions(driver).getText(lastOrderLoc);
    }

    public Boolean isProductDisplayed(String productName){
        new ElementActions(driver).waitForVisibility(productsNamesLoc);
        var products = driver.findElements(productsNamesLoc);

        WebElement proudct = products.stream().filter((product) -> {
            String pn = product.findElement(productsNamesLoc).getText();
            return pn.equalsIgnoreCase(productName);
        }).findFirst().orElse(null);

        return null != proudct;
    }
}
