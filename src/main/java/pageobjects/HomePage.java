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
    //раздел FAQ
    private final By sectionFAQ = By.xpath("//*[contains (@class,'Home_FAQ')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод, в котором мы соглашаем со сбором кук
    public void agreeWithCookieGathering() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    //общий метод для нажатия кнопки "Заказать" из параметров
    public void clickButtonOrderProvidedFromParameters(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath(xpath)).click();
    }

    //метод для прокрутки страницы до раздела "Вопросы о важном"
    public void scrollToFAQSection() {
        WebElement element = driver.findElement(sectionFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод ожидания того, что страница успела прогрузиться
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(sectionFAQ).isDisplayed()
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
