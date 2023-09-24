package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final ElementActions eleActions;
    private final By productTitle = By.cssSelector("strong.current-item");
    private final By emailAFrientBtn = By.xpath("//button[text()='Email a friend']");
    private final By priceLable = By.id("price-value-4");
    private final By addReviewBtn = By.xpath("//a[text()='Add your review']");
    private final By addToWishlistBtn = By.id("add-to-wishlist-button-5");
    private final By toastMessage = By.xpath("//p[text()='The product has been added to your ']");
    private final By wishlistLink = By.xpath("//a[text()='wishlist']");

    private final By addToCompareBtn = By.xpath("//div[contains(@class,'compare-products')]//button[text()='Add to compare list']");

    private final By toastMsg = By.cssSelector(".bar-notification .content");
    private final By addToCartBtn = By.xpath("//div[@class='add-to-cart']//button[text()='Add to cart']");
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
        setProductName(eleActions.getText(productTitle));

    }
    private String ProductName;

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductTitle(){
//        setProductName(eleActions.getText(productTitle));
        return getProductName();
    }
    public EmailAFreindPage clickEmailAFriend(){
        eleActions.click(emailAFrientBtn);
        return new EmailAFreindPage(driver);
    }
    public String getPrice(){
        return eleActions.getText(priceLable);
    }
    public ProductReviewPage openProductReviewPage(){
        eleActions.click(addReviewBtn);
        return new ProductReviewPage(driver);
    }
    public ProductDetailsPage addProductToWishlist(){
        eleActions.click(addToWishlistBtn);
        return this;
    }
    private Boolean isToastMessageAppeared(){
        return eleActions.isElementDisplayed(toastMessage);
    }
    public WishlistPage openWishlist(){
        if(isToastMessageAppeared()){
            eleActions.click(wishlistLink);
        }
        return new WishlistPage(driver,getProductName());
    }
    public ProductDetailsPage addProductToCompare(){
        eleActions.click(addToCompareBtn);
        return this;
    }

    public String getToastMessage() {
        return eleActions.getText(toastMessage);
    }
    public ProductDetailsPage addProductToCart(){
        eleActions.click(addToCartBtn);
        return this;
    }
}
