package epam;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage {

    public WebDriver driver;


    @FindBy(css = ".profile-photo")
    WebElement profilePhoto;

    @FindBy(css = "#Login")
    WebElement loginField;

    @FindBy(css = "#Password")
    WebElement passwordField;

    @FindBy(css = "form .btn-login")
    WebElement submit;

    @FindBy(css = ".icons-benefit")
    List<WebElement> images;

    @FindBy(css = ".benefit-txt")
    List<WebElement> texts;

    @FindBy(css = ".main-title")
    WebElement mainTitle;

    @FindBy(css = ".main-txt")
    WebElement mainText;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void login(String login, String password) {
        profilePhoto.click();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submit.click();
    }
}