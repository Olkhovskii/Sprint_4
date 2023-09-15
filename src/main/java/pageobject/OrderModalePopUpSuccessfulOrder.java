package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderModalePopUpSuccessfulOrder {

    private final WebDriver driver;
    private By orderSuccessModal = By.className("Order_ModalHeader__3FDaJ");
    public OrderModalePopUpSuccessfulOrder(WebDriver driver) {
        this.driver = driver;
    }
    public String  receivePopUpWithSuccessfulOrder(){
        return driver.findElement(orderSuccessModal).getText();
    }
}
