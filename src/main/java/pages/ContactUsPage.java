package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ContactUsPage {
    private WebDriver driver;
    private ElementActions eleActions;

    private By fullNameField = By.id("FullName");
    private By emailField = By.id("Email");
    private By enquiryTextarea = By.id("Enquiry");
    private By submitBtn = By.cssSelector("button.button-1.contact-us-button");
    private By result = By.cssSelector("div.result");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }

    public ContactUsPage contactUs(String name, String email,String message){
        eleActions.type(fullNameField,name,true)
                .type(emailField,email,true)
                .type(enquiryTextarea,message,true)
                .click(submitBtn);
        return this;
    }
    public String getResultMessage(){
        return eleActions.getText(result);
    }

}
