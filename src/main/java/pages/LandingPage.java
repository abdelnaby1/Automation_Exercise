package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.Navbar;
import utils.ElementActions;

public class LandingPage {
    private final WebDriver driver;
    private final By emailField = By.id("userEmail");
    private final By passwordField = By.id("userPassword");
    private final By loginBtn = By.id("login");
    private final By errorMsgLoc = By.cssSelector("div[aria-label='Incorrect email or password.']");
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public Navbar getNavbar() {
        return new Navbar(driver);
    }

    public LandingPage goTo(){
        String url = "https://rahulshettyacademy.com/client";
        driver.get(url);
        return this;
    }

    private LandingPage enterEmail(String email) {
     new ElementActions(driver).type(emailField,email);
      return this;
    }
    private LandingPage enterPassword(String password) {
        new ElementActions(driver).type(passwordField,password);
        return this;
    }
    private LandingPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return this;
    }
    public ProductCataloguePage loginValid(String email,String password){
        enterEmail(email)
                .enterPassword(password)
                .clickLoginBtn();
        return new ProductCataloguePage(driver);
    }
    public LandingPage loginInValid(String email,String password) {
        enterEmail(email)
                .enterPassword(password)
                .clickLoginBtn();
        return this;
    }

        public String getErrorMsg() {
        return new ElementActions(driver).getText(errorMsgLoc);
    }
}
