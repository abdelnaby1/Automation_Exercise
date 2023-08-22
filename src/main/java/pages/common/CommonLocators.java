package pages.common;

import org.openqa.selenium.By;

public class CommonLocators {
    public By toastMessageLoc = By.xpath("//*[contains(@id,'toast-container')]//*[contains(text(),'Product Added To Cart')]");
    public By animationLayerLoc = By.cssSelector(".ng-animating");
}
