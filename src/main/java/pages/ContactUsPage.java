package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ContactUsPage {
    private final WebDriver driver;
    private final ElementActions eleActions;

    private final By fullNameField = By.id("FullName");
    private final By emailField = By.id("Email");
    private final By enquiryTextarea = By.id("Enquiry");
    private final By submitBtn = By.cssSelector("button.button-1.contact-us-button");
    private final By result = By.cssSelector("div.result");

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
