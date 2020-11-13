package mobile;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class test {

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

        //click allow
        driver.findElement(By.id("android:id/button2")).click();
        sleep(1000);
        //choose a city
        driver.findElement(By.id("cities_radiobutton")).click();

        // click to every menu item in the cycle
        List<String> tabBar = Arrays.asList("navigation_catalog", "navigation_favorites", "navigation_profile", "navigation_cart", "navigation_main");
        for (String element : tabBar){
            sleep(1000);
            driver.findElement(By.id(element)).click();
        }

    }
}
