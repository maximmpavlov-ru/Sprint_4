package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderContacts {

    private final WebDriver driver;
    //поле ввода имени
    private final By name = By.xpath(".//*[@placeholder=\"* Имя\"]");
    //поле ввода фамилии
    private final By surname = By.xpath(".//*[@placeholder=\"* Фамилия\"]");
    //поле ввода адреса
    private final By deliveryAddress = By.xpath(".//*[@placeholder=\"* Адрес: куда привезти заказ\"]");
    //поле выбора станции метро
    private final By metroStationInput = By.xpath(".//*[@placeholder=\"* Станция метро\"]");
    //поле ввода контактного телефона
    private final By contactPhone = By.xpath(".//*[@placeholder=\"* Телефон: на него позвонит курьер\"]");
    //кнопка "Далее"
    private final By buttonNext = By.xpath(".//*[text()='Далее']");

    public OrderContacts(WebDriver driver) {
        this.driver = driver;
    }

    //общий метод для заполнения контактных данных заказа
    public void inputOrderContactDetails(String clientName, String clientSurname, String clientAddress, String clientMetroStation, String clientPhone) {
        driver.findElement(name).sendKeys(clientName);
        driver.findElement(surname).sendKeys(clientSurname);
        driver.findElement(deliveryAddress).sendKeys(clientAddress);
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationInput).sendKeys(clientMetroStation);
        driver.findElement(By.xpath(".//*[@class='select-search__select']")).click();
        driver.findElement(contactPhone).sendKeys(clientPhone);
    }

    //метод для нажатия кнопки "Далее"
    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    //метод для проверки того, что форма загрузилась
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(buttonNext).isDisplayed()
        ));
    }
}
