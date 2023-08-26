package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.common.CommonLocators;
import pages.common.Navbar;
import utils.ElementActions;

import java.util.Objects;

public class ProductCataloguePage {
    private WebDriver driver;
    Navbar navbar;
    private By productsLoc = By.cssSelector("#products * .row > div");
    private By productsNameLoc = By.cssSelector(".card-body b");
    private By addToCartbtn = By.xpath("//div[contains(@class,'card-body')]/button[contains(text(),' Add To Cart')]");

    public ProductCataloguePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findProduct(String actualProductName){
        new ElementActions(driver).waitForVisibility(productsLoc);
        var products = driver.findElements(productsLoc);
        if (products.size() > 0){
            return products.stream().filter((product) -> {
                String productName = product.findElement(productsNameLoc).getText();
                return productName.equalsIgnoreCase(actualProductName);
            }).findFirst().orElse(null);
        }else{
            throw new RuntimeException("No Products found");
        }

    }
    public ProductCataloguePage addProductToCart(String actualProductName){
        var product = findProduct(actualProductName);
        product.findElement(addToCartbtn).click();

        new ElementActions(driver)
                .waitForVisibility(new CommonLocators().toastMessageLoc)
                .waitForInvisibility(new CommonLocators().animationLayerLoc)
                .waitForInvisibility(new CommonLocators().toastMessageLoc);

        return this;
    }
    public Boolean isProductDisplayed(String actualProductName){
        new ElementActions(driver).waitForVisibility(productsLoc);
        var products = driver.findElements(productsLoc);

        WebElement proudct = products.stream().filter((product) -> {
            String productName = product.findElement(productsNameLoc).getText();
            return productName.equalsIgnoreCase(actualProductName);
        }).findFirst().orElse(null);

        return null != proudct;
    }


    public CartPage goToCart(){
       return new Navbar(driver).goToCart();
    }

}
