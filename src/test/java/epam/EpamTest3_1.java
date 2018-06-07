package epam;


import com.codeborne.selenide.Selenide;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;


public class EpamTest3_1 {

    static DifferentElementsPage dp;

    @Before
    public void before() {
        com.codeborne.selenide.Configuration.browser = "chrome";
        dp = new DifferentElementsPage();
    }

    @Test
    public void test() {

        dp.logIn("epam", "1234");
        //checking username
        dp.profilePhoto.shouldHave(text(epam.enums.HOME_PAGE_DATA.USERNAME.value));

        dp.hdrServices.click();
        dp.datesPageButton.click();

        //0 - 100

        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), -200, 0).perform();

        dp.sliders.get(0).shouldHave(text("0"));
        dp.sliders.get(1).shouldHave(text("100"));

        Selenide.sleep(3000);

        //0,0

        actions().dragAndDropBy(dp.sliders.get(1).toWebElement(), -276, 0).perform();

        dp.sliders.get(0).shouldHave(text("0"));
        dp.sliders.get(1).shouldHave(text("0"));

        Selenide.sleep(3000);

        //100,100
        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), 276, 0).perform();
        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), 276, 0).perform();

        dp.sliders.get(0).shouldHave(text("100"));
        dp.sliders.get(1).shouldHave(text("100"));

        Selenide.sleep(3000);

        //30,70


        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), -195, 0).perform();
        actions().dragAndDropBy(dp.sliders.get(1).toWebElement(), -85, 0).perform();

        dp.sliders.get(0).shouldHave(text("30"));
        dp.sliders.get(1).shouldHave(text("70"));

        Selenide.sleep(3000);

        System.out.println($("div.info-panel-section").getText());

        /* Old Method... i still like it more! more then PO
        $(".profile-photo").click();
        $("#Login").sendKeys(epam.enums.HOME_PAGE_DATA.ID.value);
        $("#Password").sendKeys((epam.enums.HOME_PAGE_DATA.PASSWORD.value));
        $(".btn-login").click();
        $("div.profile-photo").shouldHave(text(epam.enums.HOME_PAGE_DATA.USERNAME.value));
        $("li.sub-menu").click();
        //clicking on Dates(through xpath) through left menu
        $("ul.sub").$x("//*[@id='mCSB_1_container']/ul/li[3]/ul/li[2]/a/p/span").click();
        //0,100
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(0).toWebElement(), -200, 0).perform();
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(1).toWebElement(), -1, 0).perform();
        $$(By.cssSelector(".ui-slider-handle")).get(0).shouldHave(text("0"));
        $$(By.cssSelector(".ui-slider-handle")).get(1).shouldHave(text("100"));
        //0,0
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(0).toWebElement(), -1, 0).perform();
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(1).toWebElement(), -276, 0).perform();
        $$(By.cssSelector(".ui-slider-handle")).get(0).shouldHave(text("0"));
        $$(By.cssSelector(".ui-slider-handle")).get(1).shouldHave(text("0"));
        //100,100
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(0).toWebElement(), 275, 0).perform();
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(0).toWebElement(), 275, 0).perform();
        $$(By.cssSelector(".ui-slider-handle")).get(0).shouldHave(text("100"));
        $$(By.cssSelector(".ui-slider-handle")).get(1).shouldHave(text("100"));
        //30,70
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(1).toWebElement(), -196, 0).perform();
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(0).toWebElement(), 0, 0).perform();
        actions().dragAndDropBy($$(By.cssSelector(".ui-slider-handle")).get(1).toWebElement(), -85, 0).perform();
        $$(By.cssSelector(".ui-slider-handle")).get(0).shouldHave(text("30"));
        $$(By.cssSelector(".ui-slider-handle")).get(1).shouldHave(text("70"));
        System.out.println($("div.info-panel-section").getText());
        System.out.println("--------------------");*/


    }
}