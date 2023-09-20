package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementActions;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private ElementActions eleActions;
    private By searchKeywordField = By.id("q");
    private By prodcutSearchList = By.className("search-results");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public String getSearchedKeyword(){
        return eleActions.getAttributeValue(searchKeywordField,"value");
    }
    public ProductDetailsPage openProductDetails(int productNumber){
        List<WebElement> products = driver.findElements(prodcutSearchList);
        int numOfProductsReturned = products.size();
        System.out.println(numOfProductsReturned);
        if(numOfProductsReturned > 0 && productNumber <= numOfProductsReturned ){

            products.get(productNumber - 1).findElement(By.cssSelector(".product-item a")).click();
        }
        return new ProductDetailsPage(driver);
    }
}
