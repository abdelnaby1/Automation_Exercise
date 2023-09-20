package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class MyAccountPage {
    private WebDriver driver;
    ElementActions eleActions;

    private final By changePassLink = By.linkText("Change password");
    private final By oldPasswordField = By.id("OldPassword");
    private final By newPasswordField = By.id("NewPassword");
    private final By confirmPasswordField = By.id("ConfirmNewPassword");
    private final By changePassBtn = By.cssSelector("button.button-1.change-password-button");
    private final By notificationBar = By.cssSelector("div.bar-notification.success p");

    public MyAccountPage( WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public MyAccountPage openChangePasswordView(){
        eleActions.click(changePassLink);
        return this;
    }
    public MyAccountPage changePasswrod(String oldPass, String newPass){
        eleActions.type(oldPasswordField,oldPass)
                .type(newPasswordField,newPass)
                .type(confirmPasswordField,newPass)
                .click(changePassBtn);
        return this;
    }
    public String getSuccessMsg(){
        return eleActions.getText(notificationBar);
    }
//Password was changed
}
