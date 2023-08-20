package pages.common;

import org.openqa.selenium.By;

public class CommonLocators {
     public static By toastMessageLoc = By.xpath("//*[contains(@id,'toast-container')]//*[contains(text(),'Product Added To Cart')]");
    public static By animationLayerLoc = By.cssSelector(".ng-animating");
}
