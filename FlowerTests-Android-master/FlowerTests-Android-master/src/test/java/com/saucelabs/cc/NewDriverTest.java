import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;



/**
 * Created by Emmanuel.u on 22/02/2016.
 */
public class NewDriverTest {
    @Test
    public void testG1() throws Exception {



        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
/*DesiredCapabilities capability = DesiredCapabilities.chrome();
capability.setBrowserName("chrome");*/
        capability.setPlatform(Platform.WIN8_1);
        capability.setCapability("screen-resolution","1920x1080");

        WebDriver driver = new RemoteWebDriver(new URL("http://54.93.202.15:4444/wd/hub"), capability);
        driver.manage().window().setSize(new Dimension(1920, 1080));

        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        System.out.println(sessionId);

        driver.get("http://bl-qa-en.floraqueen.net");
        System.out.println("Maximizing window");


        //WebDriver driver = new FirefoxDriver();
// buscar las items
        driver.get("http://bl-qa-en.floraqueen.net/");
        WebElement flower_grid = driver.findElement(By.className("flower-grid"));
        List<WebElement> items = flower_grid.findElements(By.tagName("li"));
        items.get(1).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // pais
        Select select = new Select(driver.findElement(By.className("countrysel")));
        select.selectByValue("ES");

        // calendario
        driver.findElement(By.className("hasDatepicker")).click();
        WebElement date_grid = driver.findElement(By.className("ui-datepicker-calendar"));
        List<WebElement> dates = date_grid.findElements(By.tagName("a"));
        dates.get(0).click();

        // continuar button
        driver.findElement(By.className("purchaseFormContinueIcon")).click();


        WebElement gifts_grid = driver.findElement(By.id("addons-wrapper"));
        List<WebElement> gifts = gifts_grid.findElements(By.tagName("li"));

////  Random gift selector
        Random random = new Random();
        int index = random.nextInt(gifts.size());
        gifts.get(index).click();

        /// continue button
        driver.findElement(By.id("submit_order")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

////    pop up click
        driver.findElement(By.xpath("html/body/div[3]/div[2]/div/div/div/div[1]/span")).click();

/// Recipient’s nombre
        WebElement fullname = driver.findElement(By.id("UserFirstNameRcv"));
        fullname.sendKeys("Flora REY");

        //* Recipient’s direccíon:
        WebElement address = driver.findElement(By.id("UserStreetRcv"));
        address.sendKeys("barrio del Corazón , barcelona ");

        // Casa numero :
        WebElement house = driver.findElement(By.id("UserHouseNumRcv"));
        house.sendKeys("53");

        //piso numero :
        WebElement piso = driver.findElement(By.id("UserAptNumRcv"));
        piso.sendKeys("2");

        // * Recipient’s Ciudad:
        WebElement city = driver.findElement(By.id("UserCityRcv"));
        city.sendKeys("Barcelona");

        // * Postal codigo:
        WebElement post = driver.findElement(By.id("UserZipRcv"));
        post.sendKeys("0820178");

        // * Movil numero:
        WebElement movil = driver.findElement(By.id("UserPhoneRcv"));
        movil.sendKeys("67973636");

        // * electronico correo:
        WebElement correo = driver.findElement(By.id("UserEmailRcv"));
        correo.sendKeys("ElRey@FloraRey.com");


        ///////// your billing section ////////


        // genéro
        Select title = new Select(driver.findElement(By.id("UserGender")));
        title.selectByValue("M");

        // * nombre
        WebElement name_bill = driver.findElement(By.id("UserFirstName"));
        name_bill.sendKeys("FloraREY");

        // * biill direccion
        WebElement bill = driver.findElement(By.id("UserStreetName"));
        bill.sendKeys("barrio del Corazón , barcelona");

        // bill numero
        WebElement bill_numero =driver.findElement(By.id("UserHouseNum"));
        bill_numero.sendKeys("55");

        // * biill ciudad
        WebElement city_bill = driver.findElement(By.id("UserCity"));
        city_bill.sendKeys("barcelona");

        // * bill Postal Codigo
        WebElement post_bill = driver.findElement(By.id("UserZip"));
        post_bill.sendKeys("08201788");

        //pais
        Select pais = new Select(driver.findElement(By.id("optCountry")));
        pais.selectByValue("ES");

        // * bill electonico correo
        WebElement email_bill = driver.findElement(By.id("UserEMail"));
        email_bill.sendKeys("ElRey@FloraRey.com");

        // * bill electonico correo
        WebElement movil_bill = driver.findElement(By.id("UserPhone"));
        movil_bill.sendKeys("67973636");

        // Paypal click
        driver.findElement(By.id("paypal_tab")).click();

        //  proceed to payment
        driver.findElement(By.id("submit-form")).click();

        driver.close();



    }
}


/*
        WebDriverWait wait = new WebDriverWait(driver, 30); //you can play with the time integer  to wait for longer than 15 seconds.`
        wait.until(ExpectedConditions.urlContains("paypal")); //if you want to wait for a particular title to show up

        ///////// paypal page ///////
        //

        driver.switchTo().frame("injectedUl");
        WebElement we = driver.findElement(By.xpath(".//*[@id='email']"));
        we.sendKeys("eduard_1362572610_per@floraqueen.com");
        we = driver.findElement(By.xpath(".//*[@id='password']"));
        we.sendKeys("12345678");
        we = driver.findElement(By.xpath(".//*[@id='btnLogin']"));
        we.click();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        driver.findElement(By.className("button")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.findElement(By.id("confirmButtonTop")).click();

        driver.close();




*/