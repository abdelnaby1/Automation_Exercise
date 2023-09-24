package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementActions;

public class SearchPage {
    private final WebDriver driver;
    private final ElementActions eleActions;
    private final By searchField = By.id("small-searchterms");
    private final By searchBtn = By.xpath("//button[text()='Search']");
    private final By suggestionsList = By.id("ui-id-1");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public SearchResultPage searchByProductName(String productName){
        eleActions.type(searchField,productName)
                .click(searchBtn);
        return new SearchResultPage(driver);
    }
    public ProductDetailsPage searchByKeyword(String keyword){
        eleActions.type(searchField,keyword);
        WebElement suggestion = driver.findElement(suggestionsList);
        suggestion.findElements(By.cssSelector("li a")).get(0).click();
        return new ProductDetailsPage(driver);
    }
}
