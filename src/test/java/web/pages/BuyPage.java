package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class BuyPage extends BasePage {

    @FindBy(xpath = "//div[@data-mark='FilterOfferType']")
    public WebElement buyBnt;

    @FindBy(xpath = "//div[@data-mark='FilterRoomsCount']")
    public WebElement roomsBnt;

    @FindBy(xpath = "//div[@data-mark='FilterPosessor']")
    public WebElement posessorBtn;

    @FindBy(xpath = "//div[@data-mark='FilterPrice']")
    public WebElement priceBtn;

    @FindBy(xpath = "//div[@data-mark='FilterGeo']")
    public WebElement geoBtn;

    public BuyPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectParametersInBuyPage(List<String> parameters) throws InterruptedException {
        if (parameters.get(1).contains("Квартира")) {
            selectAnElementInDropDown(parameters.get(1), buyBnt, true);
            selectAnElementInDropDown(parameters.get(2), roomsBnt, true);
            sendPriceInDropDown(parameters.get(3), parameters.get(4), priceBtn);
            sendValueInDropDown(parameters.get(5), geoBtn);
        }
        if (parameters.get(1).contains("Дом") || parameters.get(1).contains("Часть дома") ||
                parameters.get(1).contains("Таунхаус") || parameters.get(1).contains("Участок")) {
            selectAnElementInDropDown(parameters.get(1), buyBnt, true);
            selectAnElementInDropDown(parameters.get(2), posessorBtn, false);
            sendPriceInDropDown(parameters.get(3), parameters.get(4), priceBtn);
            sendValueInDropDown(parameters.get(5), geoBtn);
        }
        if (parameters.get(1).contains("Комната") || parameters.get(1).contains("Доля")) {
            selectAnElementInDropDown(parameters.get(1), buyBnt, true);
            sendPriceInDropDown(parameters.get(2), parameters.get(3), priceBtn);
            sendValueInDropDown(parameters.get(4), geoBtn);
        }
        wait.until(ExpectedConditions.visibilityOf(searchBtn)).click();
    }
}
