package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CheckoutPage {
    private WebDriver driver;
    private static CheckoutPage checkoutPageInstance = null;
    private By countyField = By.cssSelector("input[placeholder='Select Country']");
    private By countryResultsLoc = By.xpath("//section[contains(@class,'ta-results')]/button");
    private By placeoOrderBtn = By.cssSelector(".action__submit");
    private CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    public static CheckoutPage using(WebDriver driver){
        if (checkoutPageInstance == null){
            checkoutPageInstance = new CheckoutPage(driver);
        }
        return checkoutPageInstance;
    }
    public CheckoutPage enterCountry(String countryName){
        new ElementActions(driver)
                .type(countyField,countryName)
                .waitForVisibilityOfAllElements(countryResultsLoc);

        var results = driver.findElements(By.xpath("//section[contains(@class,'ta-results')]/button"));

        var matchedResult = results.stream().filter(result -> result.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
        assert matchedResult != null;
        matchedResult.click();

        return this;
    }
    public OrderDetailsPage clickPlaceOrder(){
        new ElementActions(driver)
                .click(placeoOrderBtn);
        return OrderDetailsPage.using(driver);
    }
}
