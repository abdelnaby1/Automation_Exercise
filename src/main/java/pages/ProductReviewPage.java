package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ProductReviewPage {
    private final WebDriver driver;
    private final ElementActions eleActions;
    private final By titleField = By.id("AddProductReview_Title");
    private final By textFiled = By.id("AddProductReview_ReviewText");
    private By rating;
    private final By submitReviewBtn = By.name("add-review");
    private final By result = By.cssSelector("div.result");

    public ProductReviewPage( WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public ProductReviewPage rate(String title,String text,String rating){
        eleActions.type(titleField,title)
                .type(textFiled,text)
                .click(By.cssSelector("input[aria-label='"+rating+"']"))
                .click(submitReviewBtn);
        return  this;
    }
    public String getMessage(){
        return eleActions.getText(result);
    }
}
