package greenKart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

import java.util.Iterator;
import java.util.Set;

public class OffersPage {
    private final WebDriver driver;
    private final By searchField = By.id("search-field");
    private final By resultName = By.cssSelector("tr td:nth-child(1)");
    String parentHandle;
    public OffersPage(WebDriver driver) {
        this.driver = driver;
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> it = windowHandles.iterator();
        parentHandle = it.next();

        driver.switchTo().window(it.next());

    }
    public OffersPage searchFor(String searchTerm){
        new ElementActions(driver).type(searchField,searchTerm);
        return this;
    }
    public String getProductName(){

        String productName = new ElementActions(driver).getText(resultName);
        driver.switchTo().window(parentHandle);
        return  productName;
    }

//    public void switchToParentWindow() {
//
//    }
}
