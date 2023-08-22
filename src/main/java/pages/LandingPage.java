package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class LandingPage {
    WebDriver driver;
    private static LandingPage landingPageInstance = null;
    private String url = "https://rahulshettyacademy.com/client";
    private By emailField = By.id("userEmail");
    private By passwordField = By.id("userPassword");
    private By loginBtn = By.id("login");
    private By errorMsgLoc = By.cssSelector("div[aria-label='Incorrect email or password.']");
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
//    public static LandingPage using(WebDriver driver){
//        if (landingPageInstance == null){
//            landingPageInstance = new LandingPage(driver);
//        }
//       return landingPageInstance.setDriver(driver);
//    }
//    private LandingPage setDriver(WebDriver newDriver){
//        this.driver = newDriver;
//        return this;
//    }
    public LandingPage goTo(){
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
