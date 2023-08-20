package pages;

import org.openqa.selenium.WebDriver;

public class OrderDetailsPage {
    private WebDriver driver;
    private static OrderDetailsPage orderDetailsPageInstance = null;


    private OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public static OrderDetailsPage using(WebDriver driver){
        if (orderDetailsPageInstance == null){
            orderDetailsPageInstance = new OrderDetailsPage(driver);
        }
        return orderDetailsPageInstance;
    }
}
