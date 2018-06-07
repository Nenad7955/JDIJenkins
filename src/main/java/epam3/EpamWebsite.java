package epam3;

import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import epam.sections.LoginForm;
import epam3.pages.HomePage;
import epam3.entities.User;

import ru.yandex.qatools.allure.annotations.Step;


@JSite("https://epam.github.io/JDI/")
public class EpamWebsite extends WebSite {

    public static HomePage homePage;
    public static epam3.pages.MetalsAndColors metalsAndColors;
    public static LoginForm loginForm;

    @JFindBy(css = ".profile-photo")
    public static Label profilePhoto;

    @Step("Logging In")
    public static void login() {
        profilePhoto.click();
        loginForm.loginAs(new User());
    }


}
