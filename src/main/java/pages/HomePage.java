package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class HomePage {

    private  static WebDriver driver;
    private static  ElementActions eleActions;
    private final By registerLink = By.linkText("Register");
    private static By loginLink = By.linkText("Log in");
    private static By logoutLink = By.linkText("Log out");
    private  final By myAccountLink = By.linkText("My account");

    private  final  By contactUsLink = By.linkText("Contact us");
    private final By currencySelect = By.id("customerCurrency");
    private final By cartLink = By.cssSelector("a.ico-cart");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public UserRegisterPage openRegistrationPage(){
        eleActions.click(registerLink);
        return new UserRegisterPage(driver);
    }
    public LoginPage openLoginPage(){
        eleActions.click(loginLink);
        return new LoginPage(driver);
    }
    public HomePage logout(){
        eleActions.click(logoutLink);
        return new HomePage(driver);
    }
    public MyAccountPage openMyAccountPage(){
        eleActions.click(myAccountLink);
        return new MyAccountPage(driver);
    }
    public ContactUsPage openContactUsPage(){
        eleActions.click(contactUsLink);
        return new ContactUsPage(driver);
    }
    public HomePage changeCurrency(String currency){
        eleActions.select(currencySelect, ElementActions.SelectType.TEXT,currency);
        return this;
    }
    public SubCategoryPage clickOnMenu(String category,String subcategory){
        eleActions.mouseHover(By.linkText(category)).click(By.linkText(subcategory));
        return new SubCategoryPage(driver,subcategory);
    }
    public CartPage openCart(){
        eleActions.click(cartLink);
        return new CartPage(driver);
    }
    public static Boolean  isLoginLinkDisplayed(){
        return eleActions.isElementDisplayed(loginLink);
    }
    public static Boolean isLogoutLinkDisplayed(){
        return eleActions.isElementDisplayed(logoutLink);
    }
}
