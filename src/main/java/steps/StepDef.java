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

    public class AvitoDefs {
        protected WebDriver driver;

        @Before
        public void setUp() {
            driver = new ChromeDriver();
        }

        @Пусть("открыт ресурс авито")
        public void открыт_ресурс_авито() {
            driver.navigate().to("https://www.avito.ru/");
        }

        @И("в выпадающем списке категорий выбрана {word}")
        public void вВыпадающемСпискеКатегорийВыбранаОргтехника() {
            Select choice = new Select(driver.findElement(By.xpath("//*[@id='category']")));
            choice.selectByValue("99");
        }

        @И("в поле поиска введено значение {word}")
        public void вПолеПоискаВведеноЗначениеПринтер() {
            driver.findElement(By.xpath("//*[@id='search']")).sendKeys("Принтер");
        }

        @Тогда("кликнуть по выпадающему списку региона")
        public void кликнутьПоВыпадающемуСпискуРегиона() {
            WebElement region = driver.findElement(By.xpath("//div[@data-marker='search-form/region']"));
            region.click();
        }

        @Тогда("в поле регион введено значение {word}")
        public void вПолеРегионВведеноЗначениеВладивосток() {
            driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']")).sendKeys("Владивосток");
        }

        @И("нажата кнопка показать объявления")
        public void нажатаКнопкаПоказатьОбъявления() {
            WebElement suggest0 = driver.findElement(By.xpath("//li[@data-marker='suggest(0)']"));
            suggest0.click();
        }

        @Тогда("открылась страница результаты по запросу принтер")
        public void открыласьСтраницаРезультатыПоЗапросуПринтер() {
            driver.get("https://www.avito.ru/vladivostok/orgtehnika_i_rashodniki?q=%D0%BF%D1%80%D0%B8%D0%BD%D1%82%D0%B5%D1%80");
            if (driver.getTitle() != null && driver.getTitle().contains("принтер - Купить оргтехнику и расходники в Владивостоке ...")) {
            } else {
                System.out.println("Открыта не та страница !");
            }
        }

        @И("активирован чекбокс только с фотографией")
        public void активированЧекбоксТолькоСФотографией() {
            WebElement checkbox = driver.findElement(By.xpath("//input[@data-marker='search-form/with-images']"));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        @И("в выпадающем списке сортировка выбрано значение {word}")
        public void вВыпадающемСпискеСортировкаВыбраноЗначениеДороже() {
            Select sort = new Select(driver.findElement(By.xpath("//div[2]/select")));
            sort.selectByValue("2");
        }

        @И("в консоль выведено значение названия и цены 3 первых товаров")
        public void вКонсольВыведеноЗначениеНазванияИЦены3ПервыхТоваров() {
            WebElement catalog = driver.findElement(By.xpath("//div[@data-marker='catalog-serp']"));
            List<WebElement> price = catalog.findElements(By.xpath("//div[@class='iva-item-body-NPl6W']"));
            for (int i = 0; i < 3; i++) {
                System.out.println(price.get(i).findElement(By.xpath("//span[@class='price-text-1HrJ_ text-text-1PdBw text-size-s-1PUdo']")).getText());
            }
        }

        @After
        public void tearDown() {
            driver.close();
        }

    }
}