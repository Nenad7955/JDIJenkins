package epam;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;

import static com.codeborne.selenide.Condition.text;

public class DifferentElementsPage {

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div/div[4]/select/option[4]")
    SelenideElement yellow;

    @FindBy(css = ".profile-photo")
    SelenideElement profilePhoto;

    @FindBy(css = "#Login")
    SelenideElement login;

    @FindBy(css = "#Password")
    SelenideElement password;

    @FindBy(css = "form .btn-login")
    SelenideElement submit;

    @FindBy(css = ".icons-benefit")
    ElementsCollection images;

    @FindBy(css = ".benefit-txt")
    ElementsCollection texts;

    @FindBy(css = "li.sub-menu")
    SelenideElement subServices;

    @FindBy(css = "li.dropdown")
    SelenideElement hdrServices;


    @FindBy(css = ".label-checkbox")
    ElementsCollection checkbox;

    @FindBy(css = ".label-radio")
    ElementsCollection radiobox;

    @FindBy(css = "div.colors")
    ElementsCollection colors;

    @FindBy(css = "ul.panel-body-list.logs")
    ElementsCollection logs;

    @FindBy(css = "uui-button")
    ElementsCollection buttons;

    @FindBy(css = ".dropdown-menu | [href=\"page8.htm\"]")
    SelenideElement differentElementPageButton;

    @FindBy(css = ".ui-slider-handle")
    ElementsCollection sliders;

    @FindBy(css = ".dropdown-menu | [href=\"page4.htm\"]")
    SelenideElement datesPageButton;

    public DifferentElementsPage() {
        Selenide.page(this);
        Selenide.open("https://jdi-framework.github.io/tests/index.htm");
    }

    void logIn(String ID, String password) {
        this.profilePhoto.click();
        this.login.sendKeys(ID);
        this.password.sendKeys(password);
        this.submit.click();
    }

    void select(String value) {
        for (int i = 0; i < checkbox.size(); i++) {
            if (checkbox.get(i).getText().contains(value)) {
                checkbox.get(i).click();
                return;
            }
        }
        for (int i = 0; i < radiobox.size(); i++) {
            if (radiobox.get(i).getText().contains(value)) {
                radiobox.get(i).click();
                return;
            }
        }
        if (value == "Yellow") {
            yellow.click();
            return;
        }
        JOptionPane.showMessageDialog(null, value + ": No such element! ");

    }

}