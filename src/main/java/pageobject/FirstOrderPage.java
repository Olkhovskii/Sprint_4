package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FirstOrderPage {
    //инициализация драйвера
    private final WebDriver driver;

    //Конструктор страницы заказа, аргумент Driver
    public FirstOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //все нужные локаторы
    //Имя
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    //Фамилия
    private By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Адрес
    private By addressField  = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Станция метро
    private By metroField = By.xpath(".//input[@placeholder = '* Станция метро']");
    //Телефон
    private By phoneNumber = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //Кнопка далее
    private By buttonNext = By.xpath(".//button[text()='Далее']");

    //Методы по заполнению инпутов//

    //общий метод по заполнению инпута:
    public void fillInput(By inputName,String keys){

        driver.findElement(inputName).sendKeys(keys);
    }
    //метод по заполнению поля станции метро
    public void fillMetroField(By metro, String metroValue) {
        driver.findElement(metro).sendKeys(metroValue, Keys.ARROW_DOWN, Keys.ENTER);
    }
    //Единый метод по заполнению карточки Для кого самокат
    public void fillAllInputsAndPressNext(String name,String surname,String address, String phone,String metro){
        fillInput(nameField,name);
        fillInput(surnameField,surname);
        fillInput(addressField,address);
        fillMetroField(metroField,metro);
        fillInput(phoneNumber,phone);
        driver.findElement(buttonNext).click();
    }
}
