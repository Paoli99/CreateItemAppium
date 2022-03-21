package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicAppium {

    private AppiumDriver appiumDriver;

    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","Pixel 4 API 30");
        capabilities.setCapability("platformVersion","11.0");
        capabilities.setCapability("appPackage","com.google.android.calculator");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
        capabilities.setCapability("platformName","Android");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @AfterEach
    public void closeApplication(){
        appiumDriver.quit();
    }

    @Test
    public void verifyCalculator() throws InterruptedException {
        //con ids
        //click 2: com.google.android.calculator:id/digit_2
        appiumDriver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
        //click +: com.google.android.calculator:id/op_add
        appiumDriver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
        //click 5: com.google.android.calculator:id/digit_5
        appiumDriver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
        //click = : com.google.android.calculator:id/eq
        appiumDriver.findElement(By.id("com.google.android.calculator:id/eq")).click();

        Thread.sleep(2000);

        //verificar que la suma 7: com.google.android.calculator:id/result_final
        String expectedResult="7";
        String actualRestul=appiumDriver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();

        Assertions.assertEquals(expectedResult, actualRestul, "Error");

    }
}
