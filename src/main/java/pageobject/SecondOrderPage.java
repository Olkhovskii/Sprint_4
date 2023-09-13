package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SecondOrderPage {
    //инициализация драйвера
    WebDriver driver;
    public SecondOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //локаторы второй страницы//

    private By dataField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private By rentTimeField = By.xpath(".//input[@placeholder = '* Срок аренды']");
    private By colorBlack = By.cssSelector("#black");
    private By colorGrey = By.cssSelector("#grey");
    private By timeOfRentDropDown = By.cssSelector(".Dropdown-control");
    private By getTimeOfRentDropDownItem1 = By.xpath(".//div[text()='сутки']");
    private By getTimeOfRentDropDownItem2 = By.xpath(".//div[text()='двое суток']");
    private By orderButtonFinal = By.xpath(".//div[@class= 'Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");




    //методы для работы с локаторами второй страницы//
    //ввод даты
    public void inputData(String data){
        driver.findElement(dataField).sendKeys(data, Keys.ENTER);
    }
    //выбор количества дней
    public void chooseCountOfDays(By itemNumber){
        driver.findElement(timeOfRentDropDown).click();
        driver.findElement(itemNumber).click();
    }
    //выбор цвета
    public void chooseColor(By color){
        driver.findElement(color).click();
    }
    //клик на кнопку "заказать"
    public void confirmOrder(){
        driver.findElement(orderButtonFinal).click();
    }
    //получение текста


    //единый метод по заполнению всех полей второй страницы + клик на кнопку//
    public void fillSecondPageFields(String data){
        inputData(data);
        chooseCountOfDays(getTimeOfRentDropDownItem1);
        chooseColor(colorBlack);
        confirmOrder();
        driver.findElement(orderConfirmButton).click();
    }
}

