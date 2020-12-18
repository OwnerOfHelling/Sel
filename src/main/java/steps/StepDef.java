package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class StepDef {

    protected WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @Пусть("открыт ресурс авито")
    public void открыт_ресурс_авито() {
        driver.navigate().to("https://www.avito.ru/");
    }

    @И("в выпадающем списке категорий выбрана оргтехника")
    public void вВыпадающемСпискеКатегорийВыбранаОргтехника() {
        Select choice = new Select(driver.findElement(By.id("category")));
        choice.selectByValue("99");
    }

    @И("в поле поиска введено значение {word}")
    public void вПолеПоискаВведеноЗначениеПринтер(String name) {
        driver.findElement(By.xpath("//*[@id='search']")).sendKeys(name);
    }

    @Тогда("кликнуть по выпадающему списку региона")
    public void кликнутьПоВыпадающемуСпискуРегиона() {
        WebElement region = driver.findElement(By.xpath("//div[@data-marker='search-form/region']"));
        region.click();
    }

    @Тогда("в поле регион введено значение {word}")
    public void вПолеРегионВведеноЗначениеВладивосток(String city) {
        driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']")).sendKeys(city);
    }

    @И("нажата кнопка показать объявления")
    public void нажатаКнопкаПоказатьОбъявления() {
        WebElement suggest0 = driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']"));
        suggest0.click();
    }

    @Тогда("открылась страница результаты по запросу {word}")
    public void открыласьСтраницаРезультатыПоЗапросуПринтер(String text) {
        WebElement searc = driver.findElement(By.xpath("//*[@id='search']"));
        if (searc != null && searc.equals(text)) {
            System.out.println("Открыта не та страница !");
            driver.close();
        }
    }

    @И("активирован чекбокс только с фотографией")
    public void активированЧекбоксТолькоСФотографией() {
        WebElement checkbox = driver.findElement(By.xpath("//label[2]/span"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @И("в выпадающем списке сортировка выбрано значение {word}")
    public void вВыпадающемСпискеСортировкаВыбраноЗначениеДороже(String price) {
        Select sort = new Select(driver.findElement(By.xpath("//div[2]/select")));
        sort.selectByVisibleText(price);
    }

    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void вКонсольВыведеноЗначениеНазванияИЦены3ПервыхТоваров(int num) {
        WebElement catalog = driver.findElement(By.xpath("//div[@data-marker='catalog-serp']"));
        List<WebElement> price = catalog.findElements(By.xpath("//div[@class='iva-item-body-NPl6W']"));
        for (int i = 0; i < num; i++) {
            System.out.println(price.get(i).getText());
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
