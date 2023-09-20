package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class EmailAFreindPage {
    private WebDriver driver;
    private ElementActions eleActions;

    private By frinedEmailField = By.id("FriendEmail");
    private By yourEmailField = By.id("YourEmailAddress");
    private By messageTextarea = By.id("PersonalMessage");
    private By sendEmailBtn = By.name("send-email");
    private By result = By.cssSelector("div.result");

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
