package epam;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

import com.codeborne.selenide.Configuration;
import com.epam.jdi.uitests.web.selenium.driver.ScreenshotMaker;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.aspects.AllureStepsAspects;
import ru.yandex.qatools.allure.events.MakeAttachmentEvent;
import ru.yandex.qatools.allure.testng.AllureTestListener;


import static com.codeborne.selenide.Selenide.zoom;


@Listeners(epam.allure.allure.class)
@Features({"Testing"})
@Stories({"the test"})
public class EpamTest {

    static DifferentElementsPage dp;


    @BeforeTest
    public void before() {
        com.codeborne.selenide.Configuration.browser = "chrome";

        Configuration.browserSize = "1920x1080x24";
        Configuration.startMaximized = true;
        dp = new DifferentElementsPage();
    }

    //@Attachment
    @Step("Drag and Drop - 0,100")
    void do1() {
        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), -200, 0).perform();
        dp.sliders.get(0).shouldHave(text("0"));
        dp.sliders.get(1).shouldHave(text("100"));
    }

    //@Attachment
    @Step("Drag and Drop - 0,0")
    void do2() {
        actions().dragAndDropBy(dp.sliders.get(1).toWebElement(), -276, 0).perform();
        dp.sliders.get(0).shouldHave(text("0"));
        dp.sliders.get(1).shouldHave(text("0"));
    }

    //@Attachment
    @Step("Drag and Drop - 100,100")
    void do3() {
        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), 276, 0).perform();
        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), 276, 0).perform();
        dp.sliders.get(0).shouldHave(text("100"));
        dp.sliders.get(1).shouldHave(text("100"));
    }

    //@Attachment
    @Step("Drag and Drop - 30,70")
    void do4() {
        actions().dragAndDropBy(dp.sliders.get(0).toWebElement(), -149, 0).perform();
        actions().dragAndDropBy(dp.sliders.get(1).toWebElement(), -64, 0).perform();
        System.out.println(dp.sliders.get(0).getText());
        System.out.println(dp.sliders.get(1).getText());
        System.out.println("***************************");
        dp.sliders.get(0).shouldHave(text("30"));
        dp.sliders.get(1).shouldHave(text("70"));
    }

    @Test
    @Description("Something going on")
    public void test() {
        zoom(0.5);
        //logging in
        dp.logIn("epam", "1234");
        //checking username
        dp.profilePhoto.shouldHave(text(epam.enums.HOME_PAGE_DATA.USERNAME.value));

        //checking number of texts and pictures
        dp.images.shouldHave(size(4));
        dp.texts.shouldHave(size(4));

        System.out.println("LOGGED IN");

        //checking service subcategories elements
        dp.checkServiceSubcategory();

        //checking service header's elements
        dp.checkServiceHeader();

        //opening Different Elements page and checking for existence of elements and comparing values
        dp.checkDifferentElements();


        //selecting Water, Wind, Selen and Yellow
        dp.select("Water");
        dp.select("Wind");

        dp.select("Selen");

        dp.select("Yellow");

        //checking in logs
        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.logsChecked.length; i++)
            dp.logs.get(0).shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.logsChecked[i]));

        //unselecting Water and Wind
        dp.select("Water");
        dp.select("Wind");

        //checking in logs
        for (int i = 0; i < epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.logsUnchecked.length; i++)
            dp.logs.get(0).shouldHave(text(epam.enums.DIFFERENT_ELEMENTS_PAGE.ACCCAT.logsUnchecked[i]));
    }


    @Test()
    public void test2() {

        //dp.logIn("epam", "1234");
        //checking username
        //dp.profilePhoto.shouldHave(text(epam.enums.HOME_PAGE_DATA.USERNAME.value));

        dp.hdrServices.click();
        dp.datesPageButton.scrollTo();
        dp.datesPageButton.click();


        dp.sliders.get(0).scrollTo();
        dp.sliders.get(1).scrollTo();

        //0 - 100
        do1();

        System.out.println(dp.sliders.get(0).getText());
        System.out.println(dp.sliders.get(1).getText());
        System.out.println("***************************");

        //0,0
        do2();


        System.out.println(dp.sliders.get(0).getText());
        System.out.println(dp.sliders.get(1).getText());
        System.out.println("***************************");

        //100,100
        do3();

        System.out.println(dp.sliders.get(0).getText());
        System.out.println(dp.sliders.get(1).getText());
        System.out.println("***************************");

        //30,70
        do4();


        $("div.info-panel-section").scrollTo();
        System.out.println($("div.info-panel-section").getText());
    }
}