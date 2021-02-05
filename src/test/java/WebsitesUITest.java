import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
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
    public void task2() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alex\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        String s;
        driver.get("https://www.youtube.com/");

        List<WebElement> listImages = driver.findElementsByCssSelector("*#avatar");

        for (WebElement item : listImages) {
            BufferedImage bufferedImage = ImageIO.read(item.getScreenshotAs(OutputType.FILE));
            File outputfile = new File("saved.png");
            ImageIO.write(bufferedImage, "png", outputfile);
            //System.out.println(item.getText());
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.quit();
    }

    @Test
    // selenium тест, выводящий названия всех товаров дороже 2000 рублей, на главной странице wildberries.ru
    public void task3() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        //String s;
        //System.out.println(driver.manage().window().getSize());
        //Dimension dm = new Dimension(1920,1080);
        //driver.manage().window().setSize(dm);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920,1080");

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        driver.get("https://www.wildberries.ru/");

        List<WebElement> listProducts = driver.findElementsByCssSelector("a.j-open-full-product-card");
        List<WebElement> listPriceElements = driver.findElementsByCssSelector("div.price-now");
        List<Integer> listPrices = new ArrayList<Integer>();

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

        List<WebElement> listPrices = driver.findElementsByCssSelector("a.a2g0");


        System.out.println("\nСписок пылесосов дороже 5000 рублей:");
        //for (WebElement item : listPrices) {
           // if (parseInt(item.toString()) > 5000) {
                //WebElement product = driver.findElementsByCssSelector("");
                //System.out.println(product.getText());
            //}
        //}

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.quit();
    }
}

