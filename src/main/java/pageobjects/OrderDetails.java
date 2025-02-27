package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class OrderDetails {

    private final WebDriver driver;
    //поле ввода даты начала аредны
    private final By rentalStartDate = By.xpath(".//*[contains(@class, 'Input_Responsible') and contains(@placeholder, 'Когда')]");
    //поле выбора длительности аренды
    private final By rentalPeriod = By.className("Dropdown-arrow");
    //чекбокс для черного цвета самоката
    private final By scooterColorBlack = By.id("black");
    //чекбокс для серого цвета самоката
    private final By scooterColorGrey = By.id("grey");
    //поле ввода комментария
    private final By comment = By.xpath(".//*[@placeholder=\"Комментарий для курьера\"]");
    //кнопка "Заказать"
    private final By orderButton = By.xpath(".//*[text()='Заказать' and (contains(@class, 'Button_Middle') or contains(@class, 'Button_Large'))]");

    public OrderDetails(WebDriver driver) {
        this.driver = driver;
    }

    //общим метод для заполнения полей данным
    public void inputOrderDetails(String clientRentalStartDate, String clientRentalPeriod, String clientScooterColor, String clientComment) {
        driver.findElement(rentalStartDate).sendKeys(clientRentalStartDate);
        driver.findElement(rentalPeriod).click();
        String selectedOption = ".//div[@class='Dropdown-option' and contains(text(),'" + clientRentalPeriod + "')]";
        driver.findElement(By.xpath(selectedOption)).click();
        if (Objects.equals(clientScooterColor, "чёрный жемчуг")) {
            driver.findElement(scooterColorBlack).click();
        } else if (Objects.equals(clientScooterColor, "серая безысходность")) {
            driver.findElement(scooterColorGrey).click();
        } else if (Objects.equals(clientScooterColor, "чёрный жемчуг, серая безысходность")) {
            driver.findElement(scooterColorBlack).click();
            driver.findElement(scooterColorGrey).click();
        }
        driver.findElement(comment).sendKeys(clientComment);
    }

    //метод для нажатия на кнопку "Заказать"
    public void clickButtonOrder() {
        driver.findElement(orderButton).click();
    }

    //метод для проверки того, что форма загрузилась
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(orderButton).isDisplayed()
        ));
    }

}
