import com.sun.jna.platform.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertTrue;

public class
WebsitesUITest {
    @Test
    public void test0() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();


        String s;
        driver.get("https://www.ozon.ru/");

        s = driver.getTitle();
        System.out.println(s);
        assertEquals("OZON — интернет-магазин. Миллионы товаров по выгодным ценам", s);

        WebElement element = driver.findElement(By.cssSelector("[name='search']"));
        element.clear();
        element.sendKeys("Пылесос");

        WebElement btn = driver.findElement(By.cssSelector("[type='submit']"));
        btn.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.quit();
    }

    @Test
    // selenium тест, который будет выводить список названий видео на главной странице youtube.com
    public void task1() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alex\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        String s;
        driver.get("https://www.youtube.com/");

        List<WebElement> listElements = driver.findElementsByCssSelector("a#video-title-link");

        System.out.println("\nСписок видео:");
        for (WebElement item : listElements) {
            System.out.println(item.getText());
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.quit();
    }

    @Test
    // selenium тест, который будет скачивать все картинки-логотипы каналов на главной странице youtube.com
    public void task2() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("chrome://settings/");
        driver.executeScript("chrome.settingsPrivate.setDefaultZoom(0.5);");

        driver.get("https://www.youtube.com/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //Thread.sleep(1000);

        List<WebElement> listImages = driver.findElementsByCssSelector("*#avatar > #img");

        for (int i = 0; i < listImages.size(); i++) {

            String logoSrc = listImages.get(i).getAttribute("src");
            //Thread.sleep(2500);
            //System.out.println(logoSrc);
            System.out.println("I="+ i);
            BufferedImage bufferedImage = ImageIO.read(new URL(logoSrc));
            File outputfile = new File( "logo" + i + ".png");
            ImageIO.write(bufferedImage, "png", outputfile);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.quit();
    }

    @Test
    // selenium тест, выводящий названия всех товаров дороже 2000 рублей, на главной странице wildberries.ru
    public void task3() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        // Zooming the page out, elements are not parsed for the 100% default zoom level
        driver.get("chrome://settings/");
        driver.executeScript("chrome.settingsPrivate.setDefaultZoom(0.5);");

        driver.get("https://www.wildberries.ru/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Finding all the products and placing the whole webelements to the list
        List<WebElement> listProducts = driver.findElementsByCssSelector("a.j-open-full-product-card");

        // Finding all the corresponding prices to the other list of the same dimension
        List<WebElement> listPriceElements = driver.findElementsByCssSelector("div.price-now");

        // Creation of the blank list for the integer prices
        List<Integer> listPrices = new ArrayList<Integer>();

        // Prices parsing
        for (WebElement item : listPriceElements) {
            Integer price = 0;
            //String tmp = item.getText();
            String stringPrice = String.valueOf(item.getText().replaceAll("[^0-9]", ""));
            price =  Integer.parseInt(stringPrice);
            listPrices.add(price);
            }

        System.out.println("\nСписок товаров дороже 2000 рублей:");
        for (int i = 0; i < listProducts.size(); i++) {
                if (listPrices.get(i) > 2000) {
                System.out.println(listProducts.get(i).getText());
            }
        }

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.quit();
    }

    @Test
    // selenium тест, выводящий названия всех пылесосов дороже 5000 рублей на ozon.ru
    public void task4() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        String s;
        driver.get("https://www.ozon.ru/");
        WebElement element = driver.findElementByName("search");
        element.sendKeys("Пылесос\t");
        WebElement btn = driver.findElement(By.cssSelector("[type='submit']"));
        btn.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Same concept as in the Task3
        List<WebElement> listProducts = driver.findElementsByCssSelector("a.a2g0");
        List<WebElement> listPriceElements = driver.findElementsByCssSelector("span.c4v8");
        List<Integer> listPrices = new ArrayList<Integer>();

        for (WebElement item : listPriceElements) {
            Integer price = 0;
            //String tmp = item.getText();
            String stringPrice = String.valueOf(item.getText().replaceAll("[^0-9]", ""));
            price =  Integer.parseInt(stringPrice);
            listPrices.add(price);
        }

        System.out.println("\nСписок пылесосов дороже 5000 рублей:");
        for (int i = 0; i < listProducts.size(); i++) {
            if (listPrices.get(i) > 5000) {
                System.out.println(listProducts.get(i).getText());
            }
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.quit();
    }
}

