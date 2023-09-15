import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainPage;


//обьявление параметризированного теста
@RunWith(Parameterized.class)
public class MainPageTest  {
    private final int numberOfQuestion;
    //инициализация драйвера, объявление переменных ОР
    private WebDriver driver;
    private final String expectedResult;

    //обьявление конструктора для теста главной страницы с параметрами
    public MainPageTest(String expectedResult, int numberOfQuestion) {
        this.expectedResult = expectedResult;
        this.numberOfQuestion = numberOfQuestion;
    }
    @Before
    public void startUp(){
        // WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //создание обьектов с параметрами
    @Parameterized.Parameters
    public static Object[][] accordionItems(){
        return new Object[][]{

                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.",3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.",7}
        };
    }

    @Test
    public void testQuestionsAndAnswersMatches() {
        //экземпляр главной страницы
        MainPage objMainPage = new MainPage(driver);
        //закрытие куки
        objMainPage.closeCookie();
        //скролл до элемента
        objMainPage.scrollToTheElement();
        //клик на вопросы о важном
        objMainPage.clickOnListOfQuestions(numberOfQuestion);
        //возврат текста
        String result = objMainPage.getTextFromListOfAnswers(numberOfQuestion);
        //сравнение с правильным текстом
        Assert.assertEquals("Текст не совпал",expectedResult, result);
        //закрытие драйвера
    }
    @After
    public void teardown() {
        //закрытие браузера
        driver.quit();
    }
}

