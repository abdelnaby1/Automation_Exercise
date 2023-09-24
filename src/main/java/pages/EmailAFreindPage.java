package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class EmailAFreindPage {
    private final WebDriver driver;
    private final ElementActions eleActions;

    private final By frinedEmailField = By.id("FriendEmail");
    private final By yourEmailField = By.id("YourEmailAddress");
    private final By messageTextarea = By.id("PersonalMessage");
    private final By sendEmailBtn = By.name("send-email");
    private final By result = By.cssSelector("div.result");

    public EmailAFreindPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public EmailAFreindPage emailYourFriend(String friendMail, String message){
        eleActions.type(frinedEmailField,friendMail)
                .type(messageTextarea,message)
                .click(sendEmailBtn);
        return this;
    }
    public String getMessage(){
        return eleActions.getText(result);
    }
}
