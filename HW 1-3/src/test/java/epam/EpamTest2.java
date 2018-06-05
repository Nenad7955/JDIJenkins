package epam;

import epam.enums.HOME_PAGE_DATA;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// TODO this code do not match with code convention !
// TODO same problem with tests count
public class EpamTest2 {

    private WebDriver driver;
    String[] TEXTS = new String[]{
            HOME_PAGE_DATA.FIRST_TEXT.value,
            HOME_PAGE_DATA.SECOND_TEXT.value,
            HOME_PAGE_DATA.THIRD_TEXT.value,
            HOME_PAGE_DATA.FOURTH_TEXT.value
    };


    @Before
    public void before() {
        // System.setProperty("webdriver.chrome.driver", c);
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        driver.navigate().to(epam.enums.HOME_PAGE_DATA.URL.value);
        EpamWebsite.initHP(this.driver);
        EpamWebsite.homePage.login(epam.enums.HOME_PAGE_DATA.ID.value, epam.enums.HOME_PAGE_DATA.PASSWORD.value);
    }

    @After
    public void after() {
        EpamWebsite.homePage.driver.close();
    }

    @Test
    public void test() {
        //checking if URL is correct
        Assert.assertEquals(EpamWebsite.homePage.driver.getCurrentUrl(), epam.enums.HOME_PAGE_DATA.URL.value);
        //checking username
        Assert.assertEquals(EpamWebsite.homePage.profilePhoto.getAttribute("innerText"), " " + epam.enums.HOME_PAGE_DATA.USERNAME.value);
        //checking title / index
        Assert.assertEquals(EpamWebsite.homePage.driver.getTitle(), epam.enums.HOME_PAGE_DATA.LINK_INDEX.value);
        //checking number of pictures
        Assert.assertEquals(EpamWebsite.homePage.images.size(), 4);
        //checking if all 4 pictures are dispayed
        for (int i = 0; i < 4; i++)
            Assert.assertTrue(EpamWebsite.homePage.images.get(i).isDisplayed());

        //checking number of texts
        Assert.assertEquals(EpamWebsite.homePage.images.size(), 4);
        //comparing all 4 texts
        for (int i = 0; i < 4; i++)
            Assert.assertEquals(EpamWebsite.homePage.texts.get(i).getText(), TEXTS[i]);


        //checking header and the weird long text
        Assert.assertEquals(EpamWebsite.homePage.mainText.getText(), epam.enums.HOME_PAGE_DATA.WEIRD.value);
        Assert.assertEquals(EpamWebsite.homePage.mainTitle.getText(), epam.enums.HOME_PAGE_DATA.FRAMEWORK.value);


    }

}