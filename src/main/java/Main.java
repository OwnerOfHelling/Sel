import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.navigate().to("https://www.avito.ru/");

        Select choice = new Select(driver.findElement(By.xpath("//*[@id='category']")));
        choice.selectByValue("99");

        driver.findElement(By.xpath("//*[@id='search']")).sendKeys("Принтер");

        WebElement region = driver.findElement(By.xpath("//div[@data-marker='search-form/region']"));
        region.click();

        driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']")).sendKeys("Владивосток");

        WebElement suggest0 = driver.findElement(By.xpath("//li[@data-marker='suggest(0)']"));
        suggest0.click();

        WebElement showButton = driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']"));
        showButton.click();

        WebElement checkbox = driver.findElement(By.xpath("//span[@data-marker='delivery-filter/text']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        WebElement showButton2 = driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']"));
        showButton2.click();

        Select sort = new Select(driver.findElement(By.xpath("//div[2]/select")));
        sort.selectByValue("2");

        WebElement catalog = driver.findElement(By.xpath("//div[@data-marker='catalog-serp']"));

        List<WebElement> price = catalog.findElements(By.xpath("//div[@class='iva-item-body-NPl6W']"));
        for (int i = 0; i < 3; i++) {
            System.out.println(price.get(i).findElement(By.xpath("//span[@class='price-text-1HrJ_ text-text-1PdBw text-size-s-1PUdo']")).getText());
        }

    }

}
