package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

import java.util.ArrayList;
import java.util.List;

public class ComparePage {
    private WebDriver driver;
    private ElementActions eleActions;
    private By clearBtn = By.xpath("//a[text()='Clear list']");
    private By notData = By.className("no-data");
    private By productsName = By.cssSelector("tr.product-name td a");

    public ComparePage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }
    public ComparePage clearList(){
        eleActions.click(clearBtn);
        return this;
    }
    public String getNoDataText(){
        return eleActions.getText(notData);
    }
    public List getProductsName(){
        List names = new ArrayList<>();
        var pNames = driver.findElements(productsName);
        for (var name: pNames) {
            names.add(name.getText());
        }
        return names;
    }
}
