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
    private final String clientXpath;

    public OrderPageTests(String clientName, String clientSurname, String clientAddress, String clientMetroStation, String clientPhone, String clientRentalStartDate, String clientRentalPeriod, String clientScooterColor, String clientComment, String clientXpath) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
        this.clientMetroStation = clientMetroStation;
        this.clientPhone = clientPhone;
        this.clientRentalStartDate = clientRentalStartDate;
        this.clientRentalPeriod = clientRentalPeriod;
        this.clientScooterColor = clientScooterColor;
        this.clientComment = clientComment;
        this.clientXpath = clientXpath;
    }

    @Parameterized.Parameters
    public static Object[][] setOrderDetails() {
        return new Object[][]{
                {"Андрей", "Васильев", "Улица Большая Лубянка, 1 ст1", "Лубянка", "89136432599", "01.03.2025", "трое суток", "серая безысходность", "", ".//*[contains(@class,'Button_Button') and not (contains (@class,'Middle')) and not (contains (@class,'Large'))and contains(text(), 'Заказать')]"},
                {"Иван", "Кузнецов", "Фурманный переулок, 9к12", "Беговая", "89136432523", "02.03.2025", "сутки", "чёрный жемчуг", "Вдумчивый комментарий", ".//*[(contains (@class,'Middle') or contains (@class,'Large')) and contains(text(), 'Заказать')]"},
                {"Алёша", "Попович", "Мосфильмовская улица, 1 ст1", "Студенческая", "89131112599", "25.02.2025", "двое суток", "чёрный жемчуг, серая безысходность", "Сказочные чаевые", ".//*[(contains (@class,'Middle') or contains (@class,'Large')) and contains(text(), 'Заказать')]"},
                {"Михаил", "Орлов", "Ленина 2", "Новокосино", "89131112779", "25.03.2025", "пятеро суток", "серая безысходность", "Какой-то ненужный комментарий", ".//*[contains(@class,'Button_Button') and not (contains (@class,'Middle')) and not (contains (@class,'Large'))and contains(text(), 'Заказать')]"},
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
    public void orderTests() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.waitForPageLoad();
        objHomePage.agreeWithCookieGathering();
        objHomePage.clickButtonOrderProvidedFromParameters(clientXpath);

        //Заполняем данные на экране "Для кого самокат"
        OrderContacts objOrderContacts = new OrderContacts(driver);
        objOrderContacts.waitForPageLoad();
        objOrderContacts.inputOrderContactDetails(clientName, clientSurname, clientAddress, clientMetroStation, clientPhone);
        objOrderContacts.clickButtonNext();

        //Заполняем данные на экране "Про аренду"
        OrderDetails objOrderDetails = new OrderDetails(driver);
        objOrderDetails.waitForPageLoad();
        objOrderDetails.inputOrderDetails(clientRentalStartDate, clientRentalPeriod, clientScooterColor, clientComment);
        objOrderDetails.clickButtonOrder();

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
