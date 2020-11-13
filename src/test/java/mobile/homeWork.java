package mobile;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;


public class homeWork {
    private static void doSwipeToUp(AndroidDriver driver) throws InterruptedException {
        TouchAction actions = new TouchAction((MobileDriver) driver);

        Dimension size = driver.manage().window().getSize();
        int endy = (int) (size.height * 0.7);
        int starty = (int) (size.height * 0.3);
        int startx = size.width / 2;
        sleep(1000);
        actions.press(PointOption.point(startx, starty)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(startx, endy)).release().perform();

    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // set capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); //platformName
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0"); //platformVersion

        cap.setCapability("appPackage", "ru.filit.mvideo.b2c");
        cap.setCapability("appActivity", "ru.filit.mvideo.b2c.ui.main.view.MainActivity");

        //initialize a driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        //initialize wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // list of ids to click to: accept button, chose a city button, navigate to catalog button and search field
        List<String> buttons = Arrays.asList("android:id/button2", "cities_radiobutton", "navigation_catalog", "catalog_search_container");
        for (String button : buttons) {
            //use class WebDriverWait for waiting until element to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(button))).click();
        }
        // send search field value
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_src_text"))).sendKeys("телевизор");
        //press enter for sending keys
        driver.pressKeyCode(66);
         //the same list of ids as in 59 line ( in this case navigate to a product card, click a button add to cart)
        List<String> buttons2 = Arrays.asList("listing_material_image", "product_add_to_cart");
        for (String button : buttons2) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(button))).click();
        }
        //swipe down for hiding a block with accessories
        doSwipeToUp(driver);
        // navigate to the cart
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navigation_cart"))).click();

        //check that cart isn't empty
        boolean cartIsEmpty = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("cart_item_image"))).isEmpty();
        assertThat(cartIsEmpty).isEqualTo(false);
    }
}
