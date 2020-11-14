package web.methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.BuyPage;
import web.pages.ByTheDayPage;
import web.pages.RentPage;
import web.pages.ResultPage;

import java.util.List;

public class PagesSwitcher {

    public void SwitchPageAndSetFilters(List<String> parameters, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        BuyPage buyPage = new BuyPage(driver, wait);
        RentPage rentPage = new RentPage(driver, wait);
        ByTheDayPage byTheDayPage = new ByTheDayPage(driver, wait);
        ResultPage resultPage = new ResultPage(driver, wait);
        buyPage.switchTab(parameters.get(0));
        switch (parameters.get(0)) {
            case "Купить": {
                buyPage.selectParametersInBuyPage(parameters);
                break;
            }
            case "Снять": {
                rentPage.selectParametersInRentPage(parameters);
                break;
            }
            case "Посуточно": {
                byTheDayPage.selectParametersInByTheDayPage(parameters);
                break;
            }
        }
        resultPage.checkTheResultOfSearch();

    }
}
