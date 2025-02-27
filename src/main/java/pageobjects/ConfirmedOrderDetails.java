package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmedOrderDetails {
    private final WebDriver driver;
    //Заголовок окна с деталями подтврежденного заказа
    private final By confirmedOrderHeader = By.className("Order_ModalHeader__3FDaJ");

    public ConfirmedOrderDetails(WebDriver driver) {
        this.driver = driver;
    }
    //метод для подтверждения заказа
    public void checkOrderConfirmation() {
        driver.findElement(confirmedOrderHeader).isDisplayed();
    }

    //метод для проверки того, что форма загрузилась
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(confirmedOrderHeader).isDisplayed()
        ));
    }
}
