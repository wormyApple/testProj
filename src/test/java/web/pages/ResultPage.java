package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.methods.DataModel;

import java.util.List;

public class ResultPage extends BasePage {
    public ResultPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = " //div[@data-name='LinkArea']")
    public List<WebElement> dataContainers;

    @FindBy(xpath = "//span[@data-mark='OfferTitle']")
    public List<WebElement> titles;

    @FindBy(xpath = "")
    public List<WebElement> addresses;

    @FindBy(xpath = "//span[@data-mark='MainPrice']")
    public List<WebElement> prices;

    @FindBy(xpath = "//div[@data-name='LinkArea']/div[last()]//p")
    public List<WebElement> description;

    public DataModel getDataOfAnObject(int index) {
        List<WebElement> elementsOfAddress = driver.findElements(By.xpath("(//div[@data-name='LinkArea']/div[@data-name='ContentRow'][1])["+(index+1)+"]/descendant::a"));
        String fullAddress = "";
        for (WebElement element : elementsOfAddress) {
            fullAddress = fullAddress + element.getText();
        }
        DataModel dataModel = new DataModel(titles.get(index).getText(), fullAddress,
                prices.get(index).getText(), description.get(index).getText());
        return dataModel;
    }

    public int countOfRows() {
        return dataContainers.size();
    }

}
