package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;

public class ElementActions {
    private WebDriver driver;
    public enum SelectType {
        TEXT, VALUE;
    }
    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }
    public ElementActions waitForVisibility(By elementLocator){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return this;
    }

    public ElementActions waitForInvisibility(By elementLocator){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
        return this;
    }
    public ElementActions waitForElementToContainsText(By elementLocator,String text){
        try {
            Helper.getExplicitWait(driver)
                    .until(ExpectedConditions.textToBePresentInElementLocated(elementLocator,text));
        }catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        return this;
    }

    public ElementActions click(By elementLocator) {
        click(driver, elementLocator);
        return this;
    }
    private void click(WebDriver driver, By elementLocator) {
//        locatingElementStrategy(driver, elementLocator);
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }

//        logElementActionStep(driver, "Click on", elementLocator);
        try {
            new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {

            try {
                ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                        driver.findElement(elementLocator));
            } catch (Exception rootCauseException) {
                Logger.logStep(exception.getMessage());
                Logger.logStep(rootCauseException.getMessage());
                fail("Couldn't click on the element:" + elementLocator, rootCauseException);
            }
        }
    }
    public ElementActions type(By elementLocator, String text) {
        type(driver, elementLocator, text, true);
        return this;
    }
    public ElementActions type(By elementLocator, String text, boolean clearBeforeTyping) {
        type(driver, elementLocator, text, clearBeforeTyping);
        return this;
    }
    public void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
//        locatingElementStrategy(driver, elementLocator);
        try {
            if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
//                logElementActionStep(driver, "Clear and Type [" + text + "] on", elementLocator);
                driver.findElement(elementLocator).clear();
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
                            driver.findElement(elementLocator));
                }
            } else {
//                logElementActionStep(driver, "Type [" + text + "] on", elementLocator);
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
                    String currentValue = driver.findElement(elementLocator).getAttribute("value");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].setAttribute('value', '" + currentValue + text + "')",
                            driver.findElement(elementLocator));
                }
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
                "The data is not inserted successfully to the field, the inserted data should be: [" + text
                        + "]; But the current field value is: ["
                        + driver.findElement(elementLocator).getAttribute("value") + "]");
    }


    public  void select(WebDriver driver, By elementLocator, SelectType selectType, String option) {
//        locatingElementStrategy(driver, elementLocator);
        try {
            Select select = new Select(driver.findElement(elementLocator));
//            logElementActionStep(driver, "Select [" + option + "] from", elementLocator);
            assertFalse(select.isMultiple());
            switch (selectType) {
                case TEXT -> select.selectByVisibleText(option);
                case VALUE -> select.selectByValue(option);
                default -> fail("Unexpected value: " + selectType);
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions select(By elementLocator, SelectType selectType, String option) {
        select(driver, elementLocator, selectType, option);
        return this;
    }

    public void mouseHover(WebDriver driver, By elementLocator) {
//        locatingElementStrategy(driver, elementLocator);
//        logElementActionStep(driver, "Mouse Hover over", elementLocator);
        try {
            new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }
    public ElementActions mouseHover(By elementLocator) {
        mouseHover(driver, elementLocator);
        return this;
    }

    public void doubleClick(WebDriver driver, By elementLocator) {
//        locatingElementStrategy(driver, elementLocator);
//        logElementActionStep(driver, "Double Click on", elementLocator);
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(driver.findElement(elementLocator)).perform();
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions doubleClick(By elementLocator) {
        doubleClick(driver, elementLocator);
        return this;
    }

    public static void clickKeyboardKey(WebDriver driver, By elementLocator, Keys key) {
//        locatingElementStrategy(driver, elementLocator);
//        logElementActionStep(driver, "Click a Keyboard key [" + key.name() + "] on", elementLocator);
        try {
            driver.findElement(elementLocator).sendKeys(key);
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions clickKeyboardKey(By elementLocator, Keys key) {
        clickKeyboardKey(driver, elementLocator, key);
        return this;
    }
    public String getText( By elementLocator) {
        return getText(driver,elementLocator);
    }

    private String getText(WebDriver driver, By elementLocator) {
//        locatingElementStrategy(driver, elementLocator);
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        try {

            String text = driver.findElement(elementLocator).getText();
//            logElementActionStep(driver, "Get Text" + "[" + text + "] from", elementLocator);
            return text;
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        return null;
    }

    public String getAttributeValue(By elementLocator, String attributeName) {
        return getAttributeValue(driver,elementLocator,attributeName);
    }
    public static String getAttributeValue(WebDriver driver, By elementLocator, String attributeName) {
//        locatingElementStrategy(driver, elementLocator);
        try {
            String attributeValue = driver.findElement(elementLocator).getAttribute(attributeName);
//            logElementActionStep(driver, "Get the [" + attributeName + "] Attribute Value" + "[" + attributeValue + "] from",
//                    elementLocator);
            return attributeValue;
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());

        }
        return null;
    }

    public Boolean isElementDisplayed(By elementLocator){
        try {
            // Wait for the element to be visible
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            // Scroll the element into view to handle some browsers cases
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
                    driver.findElement(elementLocator));
            // Check if the element is displayed
            if (!driver.findElement(elementLocator).isDisplayed()) {
                Logger.logStep("The element [" + elementLocator.toString() + "] is not Displayed");
                fail("The element [" + elementLocator.toString() + "] is not Displayed");
            }else{
                Logger.logStep("The element [" + elementLocator.toString() + "] is Displayed");
                return true;
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        return false;
    }


//    private static void locatingElementStrategy(WebDriver driver, By elementLocator) {
//        try {
//            // Wait for the element to be visible
//            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
//            // Scroll the element into view to handle some browsers cases
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
//                    driver.findElement(elementLocator));
//            // Check if the element is displayed
//            if (!driver.findElement(elementLocator).isDisplayed()) {
//                Logger.logStep("The element [" + elementLocator.toString() + "] is not Displayed");
//                fail("The element [" + elementLocator.toString() + "] is not Displayed");
//            }
//        } catch (Exception e) {
//            Logger.logStep(e.getMessage());
//            fail(e.getMessage());
//        }
//    }

//    private static void logElementActionStep(WebDriver driver, String action, By elementLocator) {
//        String elementName = driver.findElement(elementLocator).getAccessibleName();
//        if ((elementName != null && !elementName.isEmpty())) {
//            Logger.logStep("[Element Action] " + action + " [" + elementName + "] element");
//        } else {
//            Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
//        }
//
//    }

}
