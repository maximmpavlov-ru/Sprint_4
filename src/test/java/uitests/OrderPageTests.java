package uitests;

import org.junit.Before;
import pageobjects.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Parameterized.class)
public class OrderPageTests {
    private final String clientName;
    private final String clientSurname;
    private final String clientAddress;
    private final String clientMetroStation;
    private final String clientPhone;
    private final String clientRentalStartDate;
    private final String clientRentalPeriod;
    private final String clientScooterColor;
    private final String clientComment;

    public OrderPageTests(String clientName, String clientSurname, String clientAddress, String clientMetroStation, String clientPhone, String clientRentalStartDate, String clientRentalPeriod, String clientScooterColor, String clientComment) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
        this.clientMetroStation = clientMetroStation;
        this.clientPhone = clientPhone;
        this.clientRentalStartDate = clientRentalStartDate;
        this.clientRentalPeriod = clientRentalPeriod;
        this.clientScooterColor = clientScooterColor;
        this.clientComment = clientComment;
    }

    @Parameterized.Parameters
    public static Object[][] setOrderDetails() {
        return new Object[][]{
                {"Андрей", "Васильев", "Улица Большая Лубянка, 1 ст1", "Лубянка", "89136432599", "01.03.2025", "трое суток", "серая безысходность", ""},
                {"Иван", "Кузнецов", "Фурманный переулок, 9/12", "Беговая", "89136432523", "02.03.2025", "сутки", "чёрный жемчуг", "Вдумчивый комментарий"},
                //Набор данных с Иваном Кузнецовым выше падает по причине наличия символа "/" в адресе, видимо это один из багов, о котором говорилось в задании.
                {"Алёша", "Попович", "Мосфильмовская улица, 1 ст1", "Студенческая", "89131112599", "25.02.2025", "двое суток", "чёрный жемчуг, серая безысходность", "Сказочные чаевые"},
        };
    }

    private WebDriver driver;

    @Before
    public void runPreconditions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderTestWithButtonOnTheTop() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.waitForPageLoad();
        objHomePage.clickOrderButtonOnTheTop();

        //Заполняем данные на экране "Для кого самокат"
        OrderContacts objOrderContacts = new OrderContacts(driver);
        objOrderContacts.waitForPageLoad();
        objOrderContacts.inputOrderContactDetails(clientName, clientSurname, clientAddress, clientMetroStation, clientPhone);
        objOrderContacts.clickButtonNext();

        //Заполняем данные на экране "Про аренду"
        OrderDetails objOrderDetails = new OrderDetails(driver);
        objOrderDetails.waitForPageLoad();
        objOrderDetails.inputOrderDetails(clientRentalStartDate, clientRentalPeriod, clientScooterColor, clientComment);
        objOrderDetails.clickOrderButton();

        //Подтверждаем заказ
        OrderConfirmation objOrderConfirmation = new OrderConfirmation(driver);
        objOrderConfirmation.waitForPageLoad();
        objOrderConfirmation.clickButtonConfirm();

        //Проверяем то, что заказ получен
        ConfirmedOrderDetails objConfirmedOrderDetails = new ConfirmedOrderDetails(driver);
        objConfirmedOrderDetails.waitForPageLoad();
        objConfirmedOrderDetails.checkOrderConfirmation();
    }

    @Test
    public void orderTestWithButtonInTheMiddle() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.waitForPageLoad();
        objHomePage.scrollToOrderButtonInTheMiddle();
        objHomePage.clickOrderButtonInTheMiddle();

        //Заполняем данные на экране "Для кого самокат"
        OrderContacts objOrderContacts = new OrderContacts(driver);
        objOrderContacts.waitForPageLoad();
        objOrderContacts.inputOrderContactDetails(clientName, clientSurname, clientAddress, clientMetroStation, clientPhone);
        objOrderContacts.clickButtonNext();

        //Заполняем данные на экране "Про аренду"
        OrderDetails objOrderDetails = new OrderDetails(driver);
        objOrderDetails.waitForPageLoad();
        objOrderDetails.inputOrderDetails(clientRentalStartDate, clientRentalPeriod, clientScooterColor, clientComment);
        objOrderDetails.clickOrderButton();

        //Подтверждаем заказ
        OrderConfirmation objOrderConfirmation = new OrderConfirmation(driver);
        objOrderConfirmation.waitForPageLoad();
        objOrderConfirmation.clickButtonConfirm();

        //Проверяем то, что заказ получен
        ConfirmedOrderDetails objConfirmedOrderDetails = new ConfirmedOrderDetails(driver);
        objConfirmedOrderDetails.waitForPageLoad();
        objConfirmedOrderDetails.checkOrderConfirmation();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
