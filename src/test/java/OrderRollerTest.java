import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.FirstOrderPage;
import pageobject.MainPage;
import pageobject.OrderModalePopUpSuccessfulOrder;
import pageobject.SecondOrderPage;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;


//обьявление второго параметризованного теста
@RunWith(Parameterized.class)
public class OrderRollerTest {
    //инициализация драйвера и обьявление всех нужных переменных//перепроверка пр
    private WebDriver driver;
    private final String nameValue;
    private final String surnameValue;
    private final String addressValue;
    private final String phoneValue;
    private final String metroValue;
    private final int orderButtonNumber;
    //обьявление конструктора с полями для инпутов, где 0 - кнопка в начале страницы, 1 - кнопка в середине страницы
    public OrderRollerTest(String nameValue, String surnameValue, String addressValue, String phoneValue, String metroValue, String data, int orderButtonNumber) {
        this.nameValue = nameValue;
        this.surnameValue = surnameValue;
        this.addressValue = addressValue;
        this.phoneValue = phoneValue;
        this.metroValue = metroValue;
        this.data = data;
        this.orderButtonNumber = orderButtonNumber;
    }
    @Before
    public void startUp(){
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--enable-javascript");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //создание обьекта с тестовыми значениями
    @Parameterized.Parameters
    public static Object [][] personValues(){
        return new Object[][]{

                {"Мария","Семенова","Москва","89111457902","Арбат","28.02.2023", 0},
                {"Мария","Петросян","Санкт-Петербург","89113457902","Китай-город","25.02.2023", 0}
        };
    }

    @Test
    public void testOrderOfRoller() {
        MainPage objMainPage = new MainPage(driver);
        //закрытие куки
        objMainPage.closeCookie();
        //кнопка заказать
        objMainPage.clickOrderButton(orderButtonNumber);
        //обьявиление экземпляра первой страницы заказа
        FirstOrderPage orderPageNameSurname = new FirstOrderPage(driver);
        //заполнение всех полей заказа
        orderPageNameSurname.fillAllInputsAndPressNext(nameValue,surnameValue,addressValue,phoneValue,metroValue);
        //обьявление экземпляра второй страницы заказа

        OrderModalePopUpSuccessfulOrder objSuccessfulOrder = new OrderModalePopUpSuccessfulOrder(driver);
        String textPopUp = objSuccessfulOrder.receivePopUpWithSuccessfulOrder();
        MatcherAssert.assertThat("Нет поп-ап окна с сообщением об успешном оформлении заказа", textPopUp, startsWith("Заказ оформлен"));
    }

    @After
    public void teardown() {
        //закрытие браузера
        driver.quit();
    }
}
