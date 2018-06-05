package epam;

import org.junit.*;
import org.openqa.selenium.By;


public class EpamTest {
    @Before
    public void before() {
        EpamWebsite.init(epam.enums.HOME_PAGE_DATA.URL.value);
    }

    @Test
    public void test() {
        //check if correct URL
        Assert.assertEquals(EpamWebsite.driver.getCurrentUrl(), epam.enums.HOME_PAGE_DATA.URL.value);
        //check if correct index
        Assert.assertEquals(EpamWebsite.driver.getTitle(), epam.enums.HOME_PAGE_DATA.LINK_INDEX.value);
        //logging in
        EpamWebsite.logIn(epam.enums.HOME_PAGE_DATA.ID.value, epam.enums.HOME_PAGE_DATA.PASSWORD.value);//using xpath
        //EpamWebsite.LogIn2(epam.enums.HOME_PAGE_DATA.ID.value,epam.enums.HOME_PAGE_DATA.password.value);//using css


        //checking user after logging in
        Assert.assertEquals(EpamWebsite.driver.findElements(By.xpath("//li/a/div/span")).size(), 1);
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.USERNAME.value, EpamWebsite.driver.findElement(By.xpath("//li/a/div/span")).getText());


        //checking number of pictures and texts
        Assert.assertEquals(EpamWebsite.driver.findElements(By.xpath("//div[@class='col-sm-3']")).size(), 4);
        Assert.assertEquals(EpamWebsite.driver.findElements(By.xpath("//span[@class='benefit-txt']")).size(), 4);

        //checking if all 4 texts match
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.FIRST_TEXT.value, EpamWebsite.driver.findElement(By.xpath("//span[text()='To include good practices']")).getText());
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.SECOND_TEXT.value, EpamWebsite.driver.findElement(By.xpath("//span[text()='To be flexible and']")).getText());
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.THIRD_TEXT.value, EpamWebsite.driver.findElement(By.xpath("//span[text()='To be multiplatform ']")).getText());
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.FOURTH_TEXT.value, EpamWebsite.driver.findElement(By.xpath("//span[text()='Already have good base']")).getText());

        //checking for header text and the long weird one
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.FRAMEWORK.value, EpamWebsite.driver.findElement(By.xpath("//h3")).getText());
        Assert.assertEquals(epam.enums.HOME_PAGE_DATA.WEIRD.value, EpamWebsite.driver.findElement(By.xpath("//p[@class='main-txt text-center']")).getText());
    }

    @After
    public void CloseBrowser() { // <- code convention !
        EpamWebsite.driver.quit();
    }
}