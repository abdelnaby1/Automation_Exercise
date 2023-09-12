package utils;

import org.openqa.selenium.WebElement;

public class ElementLocators {
    public String getElementLoc(WebElement element){
        if (element.getAttribute("id") != null && !element.getAttribute("id").isBlank()){
            return "Element that has id: " + element.getAttribute("id");
        }else if (element.getAttribute("name") != null && !element.getAttribute("name").isBlank()){
            return "Element that has name: " + element.getAttribute("name");
        }else if (element.getText() != null && !element.getText().isBlank()){
            return "Element that has text: " + element.getText();
        }else {
            return "Element which is "+ element.getTagName() + " and with Accessible Name of " + element.getAccessibleName() ;
        }
    }
}
