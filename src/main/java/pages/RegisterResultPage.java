package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class RegisterResultPage {

    private final WebDriver driver;
    ElementActions eleActions;

    private final By successMessage = By.cssSelector("div.result");

    private final By continueBtn = By.cssSelector("a.button-1.register-continue-button");


    public RegisterResultPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public HomePage clickContinueBtn(){
        eleActions.click(continueBtn);
        return new HomePage(driver);
    }
    public String getSuccessMessage(){
        return new ElementActions(driver).getText(successMessage);

    }
}
