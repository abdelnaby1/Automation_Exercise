package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class LoginPage {
    private WebDriver driver;
   private ElementActions eleActions;

    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private final By loginBtn = By.cssSelector("button.button-1.login-button");
    private By rememberMeField = By.id("RememberMe");
    private By forgotPassLink = By.xpath("//a[contains(text(),'Forgot password?')]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }

    public HomePage login(String email, String pass){
        eleActions.type(emailField,email)
                .type(passwordField,pass)
                .click(loginBtn);
        return  new HomePage(driver);
    }
}
