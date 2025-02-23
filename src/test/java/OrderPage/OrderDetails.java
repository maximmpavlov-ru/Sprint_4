package OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class OrderDetails {

    private final WebDriver driver;
    //поле ввода даты начала аредны
    private final By rentalStartDate = By.xpath(".//*[contains(@class, 'Input_Responsible__1jDKN') and contains(@placeholder, 'Когда')]");
    //поле выбора длительности аренды
    private final By rentalPeriod = By.className("Dropdown-arrow");
    //чекбокс для черного цвета самоката
    private final By scooterColorBlack = By.id("black");
    //чекбокс для серого цвета самоката
    private final By scooterColorGrey = By.id("grey");
    //поле ввода комментария
    private final By comment = By.xpath(".//*[@placeholder=\"Комментарий для курьера\"]");
    //кнопка "Заказать"
    private final By orderButton = By.xpath(".//*[text()='Заказать' and contains(@class, 'Button_Middle__1CSJM')]");

    public OrderDetails(WebDriver driver) {
        this.driver = driver;
    }

    //метод для выбора даты начала аренды
    public void inputRentalStartDate(String clientRentalStartDate) {
        driver.findElement(rentalStartDate).sendKeys(clientRentalStartDate);
    }

    //метод для выбора продолжительности срока аренды
    public void inputRentalPeriod(String clientRentalPeriod) {
        driver.findElement(rentalPeriod).click();
        String selectedOption = ".//div[@class='Dropdown-option' and contains(text(),'" + clientRentalPeriod + "')]";
        driver.findElement(By.xpath(selectedOption)).click();
    }

    //метод для ввода комментария
    public void inputComment(String clientComment) {
        driver.findElement(comment).sendKeys(clientComment);
    }

    //метод для выбора цвета самоката
    public void inputScooterColor(String clientScooterColor) {
        if (Objects.equals(clientScooterColor, "чёрный жемчуг")) {
            driver.findElement(scooterColorBlack).click();
        } else if (Objects.equals(clientScooterColor, "серая безысходность")) {
            driver.findElement(scooterColorGrey).click();
        } else if (Objects.equals(clientScooterColor, "чёрный жемчуг, серая безысходность")) {
            driver.findElement(scooterColorBlack).click();
            driver.findElement(scooterColorGrey).click();
        }
    }

    //метод для нажатия на кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //метод для проверки того, что форма загрузилась
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(orderButton).isDisplayed()
        ));
    }

}
