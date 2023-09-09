package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CheckoutPage {
    private final WebDriver driver;
    private final By countyField = By.cssSelector("input[placeholder='Select Country']");
    private final By countryResultsLoc = By.xpath("//section[contains(@class,'ta-results')]/button");
    private final By placeOrderBtn = By.cssSelector(".action__submit");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    public CheckoutPage enterCountry(String countryName){
        new ElementActions(driver)
                .type(countyField,countryName)
                .waitForVisibility(countryResultsLoc);

        var results = driver.findElements(By.xpath("//section[contains(@class,'ta-results')]/button"));

        var matchedResult = results.stream().filter(result -> result.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
        assert matchedResult != null;
        matchedResult.click();

        return this;
    }
    public OrderDetailsPage clickPlaceOrder(){
        new ElementActions(driver)
                .click(placeOrderBtn);
        return new OrderDetailsPage(driver);
    }
}
