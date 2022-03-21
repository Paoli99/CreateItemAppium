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

public class BasicAppiumWhenDo {

    private AppiumDriver appiumDriver;
    String Titulo = "Titulo Nota";
    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","Pixel 4 API 30");
        capabilities.setCapability("platformVersion","11.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
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
        //con xpath
        //boton agregar
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        //poner nombre
        appiumDriver.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Title')]")).sendKeys(Titulo);
        //boton check
        appiumDriver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Save\"]")).click();
        Thread.sleep(2000);

        //verificar el titulo
        String expectedResult=Titulo;
        //String actualRestul=appiumDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.google.android.calculator:id/formula']")).getText();
        String actualResult = appiumDriver.findElement(By.xpath("//android.widget.TextView[contains(@text," +"'" +Titulo+"'"+")]")).getText();
        Assertions.assertEquals(expectedResult, actualResult, "Error");

    }
}
