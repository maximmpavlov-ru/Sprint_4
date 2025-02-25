package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    //кнопка "Заказать" в шапке страницы
    private final By orderButtonOnTheTop = By.className("Button_Button__ra12g");
    //кнопка "Заказать" в середине страницы
    private final By orderButtonInTheMiddle = By.cssSelector(".Button_Middle__1CSJM");
    //раздел FAQ
    private final By sectionFAQ = By.className("Home_FAQ__3uVm4");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для клика по верхней кнопке "Заказать"
    public void clickOrderButtonOnTheTop() {
        driver.findElement(orderButtonOnTheTop).click();
    }

    //Метод, в котором мы соглашаем со сбором кук
    public void agreeWithCookieGathering() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    //метод для клика по нижней кнопке "Заказать"
    public void clickOrderButtonInTheMiddle() {
        agreeWithCookieGathering();
        WebElement element = driver.findElement(orderButtonInTheMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonInTheMiddle).click();
    }

    //метод для прокрутки страницы до раздела "Вопросы о важном"
    public void scrollToFAQSection() {
        WebElement element = driver.findElement(sectionFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //метод для прокрутки страницы до нижней кнопки "Заказать"
    public void scrollToOrderButtonInTheMiddle() {
        WebElement element = driver.findElement(orderButtonInTheMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод ожидания того, что страница успела прогрузиться
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(orderButtonOnTheTop).isDisplayed()
        ));
    }

    //Метод для поиска и клика по вопросу в разделе FAQ
    public void findAndClickOnQuestion(String ID) {
        driver.findElement(By.id(ID)).click();
    }


    //Ждем что скрытая секция отображается и сравниваем текст ответа с ожидаемым
    public void checkTexts(String ID, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(By.id(ID)).isDisplayed()));
        Assert.assertEquals("Текст ответов не совпал", driver.findElement(By.id(ID)).getText(), text);
    }


}
