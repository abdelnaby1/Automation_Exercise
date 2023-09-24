package pages;

import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class SubCategoryPage {
    private final WebDriver driver;
    private final String subcategoryName;
    private final ElementActions eleActions;

    public SubCategoryPage(WebDriver driver,String subcategoryName) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
        this.subcategoryName = subcategoryName;
    }
    public String getSubcategoryName(){
        return this.subcategoryName;
    }

}
