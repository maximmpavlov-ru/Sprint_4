package OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmation {
    private final WebDriver driver;
    //кнопка "Да" на экране подтверждения заказа
    private final By buttonConfirm = By.xpath(".//*[text()=\"Да\"]");

    public OrderConfirmation(WebDriver driver) {
        this.driver = driver;
    }

    //метод для подтверждения заказа
    public void clickButtonConfirm() {
        driver.findElement(buttonConfirm).click();
    }

    //метод для проверки того, что форма загрузилась
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(buttonConfirm).isDisplayed()
        ));
    }
}
