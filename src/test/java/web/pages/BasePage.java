package web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//span[@data-name='FiltersSearchButton']/*[contains(text(),'Найти')]")
    public WebElement searchBtn;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void ScrollToElement(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public void selectAnElementInDropDown(String option, WebElement btn, boolean hideDropDown) throws InterruptedException {
        btn.click();
        WebElement dropDown = wait.until(ExpectedConditions.visibilityOf(btn.findElement(By.xpath("//div[contains(@class,'dropdown')]"))));
        WebElement element = dropDown.findElement(By.xpath("//ul[contains(@class,'list')]//*[contains(text(),'" + option + "')]"));
        ScrollToElement(element);
        element.click();
        if (hideDropDown)
            btn.click();
    }

    public void sendPriceInDropDown(String priceMin,String priceMax, WebElement btn) throws InterruptedException {
        btn.click();
        WebElement dropDown = wait.until(ExpectedConditions.visibilityOf(btn.findElement(By.xpath("//div[contains(@class,'dropdown')]"))));
        dropDown.findElement(By.xpath("//input[contains(@placeholder,'от')]")).sendKeys(priceMin);
        dropDown.findElement(By.xpath("//input[contains(@placeholder,'до')]")).sendKeys(priceMax);
        btn.click();
    }

    public void sendValueInDropDown(String value, WebElement btn) throws InterruptedException {
        WebElement input = btn.findElement(By.xpath("//input"));
        input.sendKeys(value);
        Thread.sleep(3000);
        input.findElement(By.xpath("//input")).sendKeys(Keys.RETURN) ;
    }
//    public void SwitchPage(String nameOfPage) {
//        switch (nameOfPage) {
//            case "Купить":
//            case "Снять": {
//                break;
//            }
//            case "Посуточно": {
//                break;
//            }
//            case "Оценить": {
//                break;
//            }
//            case "Ипотека": {
//                break;
//            }
//        }
//    }
}
