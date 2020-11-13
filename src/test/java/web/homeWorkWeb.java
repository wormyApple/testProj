package web;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class homeWorkWeb {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("https://www.mvideo.ru");


        List<String> locators = Arrays.asList(
                "//span[contains(text(),'Телевизоры')]",
                "//ul[@id='js-category-accordion']//div[contains(text(),'Телевизоры')]",
                "//div[@class='product-tile-add-to-basket-btn btn font-icon icon-trolley-cart ']"
        );
        for (String locator : locators) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
        }
        int count = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='c-cart__order']"))).size();
        assertThat(count).isEqualTo(1);

        driver.close();
    }
}