package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class BuyPage extends BasePage {

    WebDriverWait wait;

    @FindBy(xpath = "//div[@data-mark='FilterOfferType']/button")
    public WebElement buyBnt;

    @FindBy(xpath = "//div[@data-mark='FilterRoomsCount']/button")
    public WebElement roomsBnt;

    @FindBy(xpath = "//div[@data-mark='FilterPrice']/button")
    public WebElement priceBtn;

    @FindBy(xpath = "//div[@data-mark='FilterGeo']/button")
    public WebElement geoBtn;

    public BuyPage(WebDriver driver) {
        super(driver);
    }

    public void selectAHouseInDropDown(String option) {
        buyBnt.click();
        // WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-mark='FilterOfferType']//div[contains(@class,'dropdown')]")));

        driver.findElement(By.xpath("//div[contains(@class,'dropdown')]//ul[contains(@class,'list')]//*[contains(text(),'" + option + "')]")).click();
        buyBnt.click();
    }

}
