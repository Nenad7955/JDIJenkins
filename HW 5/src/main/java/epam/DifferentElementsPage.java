package epam;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Attachment;

//import javax.swing.*;

import static com.codeborne.selenide.CollectionCondition.size;
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

    @Step("Loggin in")
    void logIn(String ID, String password) {
        this.profilePhoto.click();
        this.login.sendKeys(ID);
        this.password.sendKeys(password);
        this.submit.click();
    }

    @Step("Checking service subcategory elements")
    void checkServiceSubcategory() {
        subServices.click();
        //checking service subcategory
        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.optionsArray.length; i++)
            subServices.shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.optionsArray[i]));
    }

    @Step("Checking service header elements")
    void checkServiceHeader() {
        hdrServices.click();
        //checking service header
        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.optionsArray.length; i++)
            hdrServices.shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.optionsArray[i]));
    }


    @Step("Checking Different Element page")
    void checkDifferentElements() {
        differentElementPageButton.click();

        checkbox.shouldHave(size(4));
        radiobox.shouldHave(size(4));
        colors.shouldHave(size(1));

        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.elementArray.length; i++)
            checkbox.get(i).shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.elementArray[i]));

        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.materialArray.length; i++)
            radiobox.get(i).shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.materialArray[i]));

        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.colorsArray.length; i++)
            colors.get(0).shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.colorsArray[i]));
    }


	@Attachment()
    @Step("Selecting value")
    String select(String value) {
        for (int i = 0; i < checkbox.size(); i++) {
            if (checkbox.get(i).getText().contains(value)) {
                checkbox.get(i).click();
                return value;
            }
        }
        for (int i = 0; i < radiobox.size(); i++) {
            if (radiobox.get(i).getText().contains(value)) {
                radiobox.get(i).click();
                return value;
            }
        }
        if (value == "Yellow") {
            yellow.click();
            return value;
        }
		return value;
        //JOptionPane.showMessageDialog(null, value + ": No such element! ");
    }

}