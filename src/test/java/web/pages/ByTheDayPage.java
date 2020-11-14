package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ByTheDayPage extends BasePage {
    @FindBy(xpath = "//div[@data-mark='FilterOfferType']")
    public WebElement byTheDayBnt;

    @FindBy(xpath = "//div[@data-mark='FilterRoomsCount']")
    public WebElement roomsBnt;

    @FindBy(xpath = "//div[@data-mark='FilterBedrooms']")
    public WebElement bedroomsBtn;

    @FindBy(xpath = "//div[@data-mark='FilterPrice']")
    public WebElement priceBtn;

    @FindBy(xpath = "//div[@data-mark='FilterGeo']")
    public WebElement geoBtn;

    public ByTheDayPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectParametersInByTheDayPage(List<String> parameters) throws InterruptedException {
        if (parameters.get(1).contains("Квартира")) {
            selectAnElementInDropDown(parameters.get(1), byTheDayBnt, true);
            selectAnElementInDropDown(parameters.get(2), roomsBnt, true);
            sendPriceInDropDown(parameters.get(3), parameters.get(4), priceBtn);
            sendValueInDropDown(parameters.get(5), geoBtn);
        }
        if (parameters.get(1).contains("Комната") || parameters.get(1).contains("Койко-место")) {
            selectAnElementInDropDown(parameters.get(1), byTheDayBnt, true);
            sendPriceInDropDown(parameters.get(2), parameters.get(3), priceBtn);
            sendValueInDropDown(parameters.get(4), geoBtn);
        }
        if (parameters.get(1).contains("Дом")) {
            selectAnElementInDropDown(parameters.get(1), byTheDayBnt, true);
            selectAnElementInDropDown(parameters.get(2), bedroomsBtn, true);
            sendPriceInDropDown(parameters.get(3), parameters.get(4), priceBtn);
            sendValueInDropDown(parameters.get(5), geoBtn);
        }
        wait.until(ExpectedConditions.visibilityOf(searchBtn)).click();
    }
}
