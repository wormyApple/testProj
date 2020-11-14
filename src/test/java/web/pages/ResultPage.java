package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.methods.DataModel;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultPage extends BasePage {
    public ResultPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    @FindBy(xpath = "//div[@data-name='SummaryHeader']/h3")
    public WebElement searchTitle;

    @FindBy(xpath = "//div[@data-name='LinkArea']")
    public List<WebElement> dataContainers;

    @FindBy(xpath = "//span[@data-mark='OfferTitle']")
    public List<WebElement> titles;

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
        return new DataModel(titles.get(index).getText(), fullAddress,
                prices.get(index).getText(), description.get(index).getText());
    }

    public int countOfRows() {
        return dataContainers.size();
    }

    public void checkTheResultOfSearch() {
        assertThat(searchTitle.getText().replaceAll("\\d","")).isEqualTo("Найдено  объявлений");
    }
}
